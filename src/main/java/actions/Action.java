package actions;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import main.Configuration;
import main.Engine.ActionParameter;
import repository.DBRepository;
import repository.FileRepository;

public abstract class Action {
//	protected DBRepository repository;
	protected FileRepository repository;
	protected int repoType;
	protected Map<String, Object> params;
	SimpleDateFormat sDateFormat;
	
	public Action() {
		this.sDateFormat = new SimpleDateFormat(Configuration.dFormatLong);
		this.repoType = Configuration.repoType;
		switch (repoType) {
			case 1:
//				repository = new DBRepository();
				break;
			case 2:
				repository = new FileRepository();
				break;
			default:
				break;
		}
		params = new HashMap<>();
	}
	
	public void setParams(ActionParameter...params) {
		for (ActionParameter item : params) {
			this.params.put(item.getName(), item.getValue());
		}
	}
	
	public Map<String, Object> getParams() {
		return params;
	};
	
	public abstract void run();
}
