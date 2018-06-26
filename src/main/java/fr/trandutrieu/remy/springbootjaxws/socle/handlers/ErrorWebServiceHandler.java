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
import org.w3c.dom.Node;

import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit.Level;
import fr.trandutrieu.remy.springbootjaxws.socle.exceptions.BusinessException;

public class ErrorWebServiceHandler implements SOAPHandler<SOAPMessageContextImpl> {

	public boolean handleMessage(SOAPMessageContextImpl mc) {
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
				faultCode.setNodeValue(Error.ERROR_BUSINESS.getErrorCode().name());
				faultString.setNodeValue(cause.getMessage());
			}
			else if (cause instanceof Throwable) {
				faultCode.setNodeValue(Error.ERROR_SERVER.getErrorCode().name());
				faultString.setNodeValue(cause.getMessage());
			}
			Audit.trace(Level.ERROR, "OUT | SERVICE | ERROR HANDLER | Override soap fault", cause);
		} catch (SOAPException e) {
			Audit.trace(Level.ERROR, "OUT | SERVICE | ERROR HANDLER ", e);
		}

		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
}