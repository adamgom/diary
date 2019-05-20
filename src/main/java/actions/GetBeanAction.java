package actions;

import main.Message;
import main.Commend;
import main.Engine;

public class GetBeanAction extends Action {

	@Override
	public void run() {
		Engine.getInstance().getViewController().setMessage(new Message("bean_zwrot", Commend.GET.getAction(), repository.find((Long)params.get("get"))));
//		repository.find((Long)params.get("get"));
//		System.out.println(repository.find((Long)params.get("get")).getTitle());
	}
}
