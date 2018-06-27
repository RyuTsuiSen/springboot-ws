package fr.trandutrieu.remy.springbootjaxws.socle.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Audit {
	private static final Logger LOG = LoggerFactory.getLogger(Audit.class);
	
	public static void trace(Level level, String title,  String message, Throwable e) {
		
		StringBuilder fullMessage = new StringBuilder();
		fullMessage.append(title).append(" | ").append(message);
		if (e != null){
			fullMessage.append(" | ").append(getStackTrace(e));
		}
		switch (level) {
		case DEBUG:
			LOG.debug(fullMessage.toString());
			break;
		case INFO:
			LOG.info(fullMessage.toString());
			break;
		case WARNING:
			LOG.warn(fullMessage.toString());
			break;
		case ERROR:
			LOG.error(fullMessage.toString());
			break;
		default:
			break;
		}
	}
	
	/**
	 * Mï¿½thode que permet de mettre ï¿½ plat la stack trace dans une chaine de caractï¿½re.
	 * 
	 * @param throwable
	 * 
	 * @return
	 */
	private static String getStackTrace(final Throwable throwable) {
		StringBuilder stack = new StringBuilder("");
		if (throwable != null){
			stack.append(throwable.toString()).append(" ");
			if (throwable.getStackTrace() != null && throwable.getStackTrace().length != 0){
				int stackDepth = 5; 
				for(int i=0 ; i<stackDepth ; i++){
					String className = throwable.getStackTrace()[i].getClassName();
					String fileName = throwable.getStackTrace()[i].getFileName();
					String methodName = throwable.getStackTrace()[i].getMethodName();
					Integer line = throwable.getStackTrace()[i].getLineNumber();

					stack.append(" at " + className + "." + methodName + "(" + fileName + ":" + line + ")");
				}
			}
		}

		return stack.toString();
	}
	
	public enum Level{
		DEBUG,
		INFO,
		WARNING,
		ERROR;
	}

	public static void trace(Level level, String title, String message) {
		trace(level, title, message, null);
	}
}
