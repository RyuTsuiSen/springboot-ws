package fr.trandutrieu.remy.springbootjaxws.socle.context;

import java.time.Instant;

public class ContextBean {
	private String conversationID;
	private Instant start;

	public String getConversationID() {
		return conversationID;
	}

	public void setConversationID(String conversationID) {
		this.conversationID = conversationID;
	}

	public Instant getStart() {
		return start;
	}

	public void setStart(Instant start) {
		this.start = start;
	}
	
	
}
