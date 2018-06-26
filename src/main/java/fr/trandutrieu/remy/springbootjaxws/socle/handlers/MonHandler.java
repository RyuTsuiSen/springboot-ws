package fr.trandutrieu.remy.springbootjaxws.socle.handlers;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;

import org.apache.cxf.jaxws.handler.soap.SOAPMessageContextImpl;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import fr.trandutrieu.remy.springbootjaxws.socle.exceptions.BusinessException;

public class MonHandler implements SOAPHandler<SOAPMessageContextImpl> {
	private static final Logger LOG = LoggerFactory.getLogger(MonHandler.class);

	public boolean handleMessage(SOAPMessageContextImpl mc) {
		Boolean outboundProperty = (Boolean) mc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty.booleanValue()) {
			LOG.info("La requete sort");
		} else {
			LOG.info("La requete entre");
		}

		return true;
	}

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	public boolean handleFault(SOAPMessageContextImpl mc) {
		try {
			SOAPBody soapBody = mc.getMessage().getSOAPBody();
			SOAPFault fault = soapBody.getFault();
			Node soapFaultCode = fault.getFirstChild();
			Node faultCode = soapFaultCode.getFirstChild();

			Node soapFaultString = soapFaultCode.getNextSibling();
			Node faultString = soapFaultString.getFirstChild();

			Message wrappedMessage = mc.getWrappedMessage();
			Exception content = wrappedMessage.getContent(Exception.class);
			Throwable cause = content.getCause();

			if (cause instanceof BusinessException) {
				faultCode.setNodeValue("EIP_0001");
				faultString.setNodeValue(cause.getMessage());
			}
			else if (cause instanceof Throwable) {
				faultCode.setNodeValue("EIP_0002");
				faultString.setNodeValue(cause.getMessage());
			}
		} catch (SOAPException e) {
			LOG.error("La requete sort en erreur");
			e.printStackTrace();
		}


		LOG.error("La requete sort en erreur");
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
}