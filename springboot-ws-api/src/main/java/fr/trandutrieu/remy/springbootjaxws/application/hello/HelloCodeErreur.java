package fr.trandutrieu.remy.springbootjaxws.application.hello;

import fr.trandutrieu.remy.socle.exceptions.CodeErreurItf;

public enum HelloCodeErreur implements CodeErreurItf<HelloCodeErreur, HelloLabelErreur>{
	CONTRAT_NON_TROUVE(HelloLabelErreur.BSN_000);
	
	private HelloLabelErreur errorCode;
	
	HelloCodeErreur(HelloLabelErreur errorCode){
		this.errorCode = errorCode;
	}
	
	@Override
	public HelloLabelErreur getLabelErreur() {
		return errorCode;
	}
}
