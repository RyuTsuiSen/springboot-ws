package fr.trandutrieu.remy.springbootjaxws.application.bonjour;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import fr.trandutrieu.remy.socle.inout.BusinessRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class BonjourRequest extends BusinessRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public BonjourRequest() {
		super();
	}
	
	@XmlElement(required=true, nillable=false)
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
