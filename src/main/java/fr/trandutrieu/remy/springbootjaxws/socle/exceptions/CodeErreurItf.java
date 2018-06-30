package fr.trandutrieu.remy.springbootjaxws.socle.exceptions;

import java.io.Serializable;

public interface CodeErreurItf<E extends Enum<E>, L extends Enum<L>> extends Serializable {
	public E get();
	
	public L getLabelErreur();
}
