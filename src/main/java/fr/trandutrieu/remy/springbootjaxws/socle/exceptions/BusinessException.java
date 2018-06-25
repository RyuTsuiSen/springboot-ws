package fr.trandutrieu.remy.springbootjaxws.socle.exceptions;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
        super("Business exception throwing...");
    }
}
