package fr.trandutrieu.remy.springbootjaxws.socle.webservice;

public class BusinessResponse {

	private String reponse;
	
	private String code;
	private String label;
	
	public BusinessResponse() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	
}
