package test.fr.trandutrieu.remy.springbootjaxws.application;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.trandutrieu.remy.springbootjaxws.application.hello.Hello;
import fr.trandutrieu.remy.springbootjaxws.socle.handlers.AuditInOutHandler;

@Configuration
public class ApplicationSystemTestConfig {  
    
    @Bean
    public Hello setHelloService() {       
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
    	factory.setServiceClass(Hello.class); 
    	factory.setAddress("http://localhost:8090/Service/Hello"); 
    	Hello client = (Hello) factory.create(); 
    	return client;
    }
    
    @Bean
    public AuditInOutHandler getAuditInOut() {
    	return new AuditInOutHandler();
    }
}