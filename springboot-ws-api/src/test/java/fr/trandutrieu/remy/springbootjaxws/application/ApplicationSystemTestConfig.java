package fr.trandutrieu.remy.springbootjaxws.application;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.trandutrieu.remy.springbootjaxws.application.hello.Hello;

@Configuration
public class ApplicationSystemTestConfig {  
    
    @Bean
    public BindingProvider setHelloService() {       
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
    	factory.setServiceClass(Hello.class); 
    	factory.setAddress("http://localhost:8080/ws/Hello"); 
    	BindingProvider client = (BindingProvider) factory.create(); 
    	return client;
    }
}