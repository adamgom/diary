package main;

import actions.Action;
import main.Engine.ActionParameter;

public class Message {
	private String message;
	private Action action;
	private Bean bean;
	
	public Message(String message, Action action, Bean bean) {
		this.message = message;
		this.action = action;
		this.bean = bean;
	}
	
	public Message(String message, Action action) {
		this(message, action, null);
	}
	
	public Message(String message) {
		this(message, null);
	}

	public String getMessage() {
		return message;
	}

	public Bean getBean() {
		return bean;
	}
	
	public Action getAction() {
		return action;
	}

	public void runAction(ActionParameter...params) {
		if(action != null) {
			action.setParams(params);
		}
		action.run();
	}
}
