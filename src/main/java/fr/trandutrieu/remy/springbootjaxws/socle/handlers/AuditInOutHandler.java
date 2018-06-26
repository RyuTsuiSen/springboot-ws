package fr.trandutrieu.remy.springbootjaxws.socle.handlers;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;

import org.apache.cxf.jaxws.handler.soap.SOAPMessageContextImpl;

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
			bean.setConversationID(UUID.randomUUID().toString());
			bean.setStart(Instant.now());
			ContextManager.set(bean);
			Audit.trace(Level.INFO, "IN | SERVICE");
		}

		return true;
		
		
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
}