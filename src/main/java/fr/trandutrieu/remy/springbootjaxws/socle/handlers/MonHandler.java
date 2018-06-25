package fr.trandutrieu.remy.springbootjaxws.socle.handlers;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonHandler implements SOAPHandler<SOAPMessageContext> {
	private static final Logger LOG = LoggerFactory.getLogger(MonHandler.class);

    public boolean handleMessage(SOAPMessageContext mc) {
    	Boolean outboundProperty = (Boolean)
    	         mc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

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

    public void close(MessageContext mc) {
    }

    public boolean handleFault(SOAPMessageContext mc) {
        return true;
    }
}