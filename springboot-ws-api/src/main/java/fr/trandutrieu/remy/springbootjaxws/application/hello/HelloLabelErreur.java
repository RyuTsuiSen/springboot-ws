package fr.trandutrieu.remy.springbootjaxws.application.hello;

import fr.trandutrieu.remy.socle.exceptions.LabelErreurItf;

public enum HelloLabelErreur implements LabelErreurItf{
	BSN_000("Contrat non trouve");
	private String label;
	
	HelloLabelErreur(String label){
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
}