package test.fr.trandutrieu.remy.springbootjaxws.application.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.trandutrieu.remy.springbootjaxws.application.hello.Hello;
import test.fr.trandutrieu.remy.springbootjaxws.application.ApplicationTestConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
public class HelloPortImplTest {
	
    @Autowired
    private Hello helloService;
    
    @Test
    public void sayHello() {
        // Given
    	System.out.println(helloService.sayHello("name").getReponse());
    }
    
    
}