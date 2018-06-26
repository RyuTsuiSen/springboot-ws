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
import org.w3c.dom.Node;

import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit.Level;

public class InitializeContextHandler implements SOAPHandler<SOAPMessageContextImpl> {

	public boolean handleMessage(SOAPMessageContextImpl mc) {
		Boolean outboundProperty = (Boolean) mc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (!outboundProperty.booleanValue()) {
			boolean headersChecked = checkHeaders();
			
			if (!headersChecked) {
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
					
					Audit.trace(Level.WARNING, "IN | SERVICE CONTEXT HEADER | Context Header initialized failed");
				} catch (SOAPException e) {
					Audit.trace(Level.ERROR, "IN | SERVICE CONTEXT HEADER ERROR", e);
				}
				
				return false;
			}
			else {
				Audit.trace(Level.DEBUG, "IN | SERVICE AUTHORIZATION | Context Header OK ");
			}
		
		} 
		return true;
		
		
	}

	private boolean checkHeaders() {
		return true;
	}

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	public boolean handleFault(SOAPMessageContextImpl mc) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
}