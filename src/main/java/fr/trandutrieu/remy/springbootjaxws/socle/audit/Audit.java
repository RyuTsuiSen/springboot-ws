package fr.trandutrieu.remy.springbootjaxws.socle.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.trandutrieu.remy.springbootjaxws.socle.context.ContextManager;

public class Audit {
	private static final Logger LOG = LoggerFactory.getLogger(Audit.class);
	
	public static void trace(Level level,  String message, Throwable e, String... param) {
		
		StringBuilder fullMessage = new StringBuilder();
		fullMessage.append(ContextManager.get().getConversationID()).append("-").append(ContextManager.get().getCaller()).append("-").append(ContextManager.get().getRequestedService())
		.append("?").append(ContextManager.get().getRequestedOperation()).append("v").append(ContextManager.get().getVersionService()).append("-").append(message);
		if (e != null) {
			fullMessage.append(" ").append(e.toString());
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
	
	public enum Level{
		DEBUG,
		INFO,
		WARNING,
		ERROR;
	}

	public static void trace(Level level, String message, String... params) {
		trace(level, message, null, params);
		
	}
}
