package fr.trandutrieu.remy.springbootjaxws.socle.webservice;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import fr.trandutrieu.remy.springbootjaxws.socle.AdapterServlet;

@SpringBootApplication
public abstract class ApplicationAbstract {
    
	@Autowired
    protected Bus bus;
    
	
    @Bean
    public ServletRegistrationBean myServletRegistration () {
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new AdapterServlet());
        srb.setUrlMappings(Arrays.asList("/adapters.stream"));
        return srb;
    }
}
