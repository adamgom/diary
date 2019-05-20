package actions;

import main.Message;
import static main.Commend.GET;

public class GetBeanAction extends Action {

	@Override
	public void run() {
//		new Message("bean_zwrot", GET.getAction(), repository.find((Long)params.get("get")));
		repository.find((Long)params.get("get"));
		System.out.println(repository.find((Long)params.get("get")));
	}
}
