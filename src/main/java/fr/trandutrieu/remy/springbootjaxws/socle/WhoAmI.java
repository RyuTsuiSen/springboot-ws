package fr.trandutrieu.remy.springbootjaxws.socle;

import java.util.ResourceBundle;


public class WhoAmI {
    
	static {
		ResourceBundle resource = ResourceBundle.getBundle("application-whoami");
		version = resource.getString("version");
		versionSocle = resource.getString("versionSocle");
	}
	
	private WhoAmI() {
		
	}
	
	public static String version;
	public static String versionSocle;

}