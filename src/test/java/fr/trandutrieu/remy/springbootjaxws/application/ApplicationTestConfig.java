package fr.trandutrieu.remy.springbootjaxws.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import fr.trandutrieu.remy.springbootjaxws.application.hello.Hello;
import fr.trandutrieu.remy.springbootjaxws.application.hello.HelloPortImpl;
import fr.trandutrieu.remy.springbootjaxws.application.hello.call.IT568;
import fr.trandutrieu.remy.springbootjaxws.application.hello.call.IT569;

@Configuration
public class ApplicationTestConfig {

    @Bean
    public Hello helloService() {
        return new HelloPortImpl();
    }
    
	@Bean
	@Primary
	public IT568 getIt568() {
		return new IT568();
	}
	
	@Bean
	@Primary	
	public IT569 getIt569() {
		return new IT569();
	}
}