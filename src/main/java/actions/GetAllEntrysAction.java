package actions;

import java.util.Map;

import main.Engine.ActionParameter;

public class GetAllEntrysAction extends Action {

	@Override
	public void run() {
		setParams(new ActionParameter("file", repository.getFilenameList()));
	}
	
	@Override
	public Map<String, Object> getParams() {
		run();
		return super.getParams();
	};
}
