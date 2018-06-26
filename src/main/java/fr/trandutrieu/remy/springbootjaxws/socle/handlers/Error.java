package fr.trandutrieu.remy.springbootjaxws.socle.handlers;

enum Error {
	HEADERS_INVALID(ErrorCode.ERR_000),
	ERROR_SERVER(ErrorCode.ERR_001),
	ERROR_BUSINESS(ErrorCode.ERR_002),
	AUTHORIZATION_ERROR(ErrorCode.ERR_003),
	AUTHENTICATION_ERROR(ErrorCode.ERR_004);
	
	private ErrorCode errorCode;
	
	Error(ErrorCode errorCode){
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	enum ErrorCode{
		ERR_000("Headers incorrectes"),
		ERR_001("Erreur server"),
		ERR_002("Erreur business"),
		ERR_003("Authorization error"),
		ERR_004("Authentication error");
		private String label;
		ErrorCode(String label){
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
		
		
	}
}
