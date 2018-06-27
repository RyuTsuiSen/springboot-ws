package fr.trandutrieu.remy.springbootjaxws.socle.handlers;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.cxf.jaxws.handler.soap.SOAPMessageContextImpl;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;

import fr.trandutrieu.remy.springbootjaxws.socle.WhoAmI;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit.Level;
import fr.trandutrieu.remy.springbootjaxws.socle.context.ContextBean;
import fr.trandutrieu.remy.springbootjaxws.socle.context.ContextManager;

public class AuditInOutHandler implements SOAPHandler<SOAPMessageContextImpl> {

	
	public boolean handleMessage(SOAPMessageContextImpl mc) {
		Boolean outboundProperty = (Boolean) mc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outboundProperty.booleanValue()) {
			Duration duration = Duration.between(ContextManager.get().getStart(), Instant.now());
			Audit.trace(Level.INFO, "OUT | SERVICE execTime = " + duration.toMillis() + "ms");
			ContextManager.remove();
		}
		else {
			ContextBean bean = new ContextBean();
			ContextManager.set(bean);
			bean.setConversationID(UUID.randomUUID().toString());
			bean.setStart(Instant.now());
			QName requestedServiceName =  (QName)mc.get(SOAPMessageContext.WSDL_SERVICE);
			QName requestedOperationName =  (QName)mc.get(SOAPMessageContext.WSDL_OPERATION);
			String requestedService =  (requestedServiceName != null && requestedServiceName.getNamespaceURI() != null) ? (requestedServiceName.getNamespaceURI() + requestedServiceName.getLocalPart()) : null;
			String requestedOperation =  (requestedOperationName != null) ? requestedOperationName.getLocalPart() : null;
			bean.setRequestedOperation(requestedOperation);
			bean.setRequestedService(requestedService);
			bean.setVersionService(WhoAmI.version);
			
			String username = getUsername(mc);
			if (StringUtils.isEmpty(username)) {
				buildSoapFault(mc);
				return false;
			}
			bean.setCaller(username);
			
			initMDC();
			
			Audit.trace(Level.INFO, "IN | SERVICE");
		}

		return true;
		
	}

	private void initMDC() {
		MDC.put("conversationId", ContextManager.get().getConversationID());
		MDC.put("serviceName", ContextManager.get().getRequestedService());
		MDC.put("operationName", ContextManager.get().getRequestedOperation());
		MDC.put("version", ContextManager.get().getVersionService());
		MDC.put("consumerName", ContextManager.get().getCaller());
	}

	private void buildSoapFault(final SOAPMessageContextImpl mc) {
		try {
			SOAPBody soapBody = mc.getMessage().getSOAPBody();
			soapBody.removeContents();
			soapBody.addFault();
			SOAPFault fault = soapBody.getFault();
			Node soapFaultCode = fault.getFirstChild();
			Node faultCode = soapFaultCode.getFirstChild();

			Node soapFaultString = soapFaultCode.getNextSibling();
			Node faultString = soapFaultString.getFirstChild();
			
			faultCode.setNodeValue(Error.HEADERS_INVALID.getErrorCode().name());
			faultString.setNodeValue(Error.HEADERS_INVALID.getErrorCode().getLabel());
			
			Audit.trace(Level.WARNING, "IN | SERVICE | HEADER UNEXPECTED");
		} catch (SOAPException e) {
			Audit.trace(Level.ERROR, "IN | SERVICE | HEADER ERROR", e);
		}
	}

	private String getUsername(SOAPMessageContextImpl mc) {
		final Map<String, List<String>> http_headers = (Map<String, List<String>>) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
		String username = http_headers.containsKey("username") ? http_headers.get("username").get(0) : null;
		if (username == null) {
			String authorization = http_headers.containsKey("Authorization") ? http_headers.get("Authorization").get(0) : null;
			username = decode(authorization);
		}
		return username;
	}

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	public boolean handleFault(SOAPMessageContextImpl mc) {
		Duration duration = Duration.between(ContextManager.get().getStart(), Instant.now());
		Audit.trace(Level.ERROR, "OUT | SERVICE execTime = " + duration.toMillis() + "ms");
		ContextManager.remove();
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
	
	private static String decode(final String encoded) {
		if(encoded == null) {
			return null;
		}
        final byte[] decodedBytes = Base64.getDecoder().decode(encoded.getBytes());
        final String pair = new String(decodedBytes);
        final String[] userDetails = pair.split(":", 2);
        return userDetails[0];
    }
}