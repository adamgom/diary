package main;

import actions.Action;
import actions.QuitAction;
import actions.AddAction;
import actions.RemoveAction;
import actions.GetAllEntrysAction;
import actions.GetBeanAction;

public enum Commend {
	START("start"),
	QUIT("quit", new QuitAction()),
	ADD("add", new AddAction()),
	REMOVE("remove", new RemoveAction()),
	GETALL("get all", new GetAllEntrysAction()),
	GET("get_item", new GetBeanAction());

	private String cmd;
	private Action action;
	
	private Commend(String cmd, Action action) {
		this.cmd = cmd;
		this.action = action;
	}
	
	private Commend(String cmd) {
		this(cmd, null);
	}

	public String getCmd() {
		return cmd;
	}

	public Action getAction() {
		return action;
	}
	
//	public static Commend getCommendByText(String cmd) throws UnknownCommendExeption {
//		for (Commend item : Commend.values()) {
//		 	if (item.getCmd().equals(cmd.toLowerCase())) {
//		 		return item;
//		 	}
//		}
//		throw new UnknownCommendExeption(cmd);
//	}
}
