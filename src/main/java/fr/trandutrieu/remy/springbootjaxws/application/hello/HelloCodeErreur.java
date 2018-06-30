package fr.trandutrieu.remy.springbootjaxws.application.hello;

import fr.trandutrieu.remy.springbootjaxws.application.hello.HelloCodeErreur.LabelErreur;
import fr.trandutrieu.remy.springbootjaxws.socle.exceptions.CodeErreurItf;

public enum HelloCodeErreur implements CodeErreurItf<HelloCodeErreur, LabelErreur>{
	CONTRAT_NON_TROUVE(LabelErreur.BSN_000);
	
	private LabelErreur errorCode;
	
	HelloCodeErreur(LabelErreur errorCode){
		this.errorCode = errorCode;
	}
	
	@Override
	public LabelErreur getLabelErreur() {
		return errorCode;
	}

	public enum LabelErreur{
		BSN_000("Contrat non trouve");
		private String label;
		LabelErreur(String label){
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}

	@Override
	public HelloCodeErreur get() {
		return this;
	}
}
