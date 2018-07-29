package fr.trandutrieu.remy.springbootjaxws.application.hello;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import fr.trandutrieu.remy.socle.exceptions.BusinessException;
import fr.trandutrieu.remy.springbootjaxws.application.Application;
import fr.trandutrieu.remy.springbootjaxws.application.ApplicationSystemTestConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class, ApplicationSystemTestConfig.class} ,webEnvironment=WebEnvironment.DEFINED_PORT)
public class HelloPortImplSystemTest {
    
	@Autowired
	public BindingProvider port;
	
    @Test
    public void sayHello() throws BusinessException { 
		Hello helloService = (Hello) port;
    	
    	if (port instanceof Hello) {
    		Map<String, List<String>> headers = new HashMap<String, List<String>>();
    		
    		headers.put("username", Collections.singletonList("MEGP2R"));
    		headers.put("correlationId", Collections.singletonList("12345678"));
    		port.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);
    		
    		System.out.println(helloService.sayHello("name").getReponse());
    	}
    }
    
}