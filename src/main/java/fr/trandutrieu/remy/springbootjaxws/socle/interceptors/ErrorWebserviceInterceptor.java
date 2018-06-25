package fr.trandutrieu.remy.springbootjaxws.socle.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class ErrorWebserviceInterceptor extends AbstractSoapInterceptor {
    private boolean handleMessageCalled;

    public ErrorWebserviceInterceptor() {
    	super(Phase.SETUP);
        System.out.println("Creating Instance");
      }
    
    public void _handleMessage(Message message) throws Fault {
        handleMessageCalled = true;
        Fault ex = (Fault) message.getContent(Exception.class);
        throw new Fault(ex);
    }

    protected boolean handleMessageCalled() {
        return handleMessageCalled;
    }

	public void handleMessage(SoapMessage arg0) throws Fault {
        handleMessageCalled = true;
        Exception ex = arg0.getContent(Exception.class);
        if (ex instanceof RuntimeException) {
        	Fault fault = (Fault)ex;
        	System.out.println(fault);
        }
	}


}