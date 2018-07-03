package test.fr.trandutrieu.remy.springbootjaxws.application.hello;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.trandutrieu.remy.springbootjaxws.application.Application;
import fr.trandutrieu.remy.springbootjaxws.application.hello.Hello;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class,webEnvironment=WebEnvironment.DEFINED_PORT)
public class HelloPortImplSystemTest {
    
    @Bean
    public Hello getHello() {
        JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
        jaxWsProxyFactory.setServiceClass(Hello.class);	
        jaxWsProxyFactory.setAddress("http://localhost:8080/Service/Hello");
        return (Hello) jaxWsProxyFactory.create();
    }
	
	
    @Test
    public void sayHello() { 
    	Hello hello = getHello();
    	if (hello instanceof BindingProvider)
    		// TODO ADD HEADER
    	System.out.println(hello.sayHello("name").getReponse());
    }
    
}