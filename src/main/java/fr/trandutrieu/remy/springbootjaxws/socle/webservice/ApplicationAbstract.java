package fr.trandutrieu.remy.springbootjaxws.socle.webservice;

import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public abstract class ApplicationAbstract {
    
	@Autowired
    protected Bus bus;
    
}
