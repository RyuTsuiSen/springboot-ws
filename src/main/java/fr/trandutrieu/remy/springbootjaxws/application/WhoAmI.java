package fr.trandutrieu.remy.springbootjaxws.application;

import java.util.ResourceBundle;


public class WhoAmI {
    
	static {
		ResourceBundle resource = ResourceBundle.getBundle("application-whoami");
		version = resource.getString("version");
	}
	
	private WhoAmI() {
		
	}
	
	public static String version;

}