package fr.trandutrieu.remy.springbootjaxws.socle.exceptions;

import javax.xml.ws.WebFault;

@WebFault
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
        super("Code=5156 Label=UneExceptionChecked");
    }

	public BusinessException(String string) {
		super(string);
	}
}
