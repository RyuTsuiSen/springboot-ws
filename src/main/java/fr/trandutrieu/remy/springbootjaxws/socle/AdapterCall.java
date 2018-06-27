package fr.trandutrieu.remy.springbootjaxws.socle;

public abstract class AdapterCall {

	public ExternalCallResponse execute(ExternalCallRequest request) {
		ExternalCall external = new ExternalCall(null);
		return external.execute();
	}
	
}
