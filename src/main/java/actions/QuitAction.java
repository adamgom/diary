package actions;

import java.util.Map;

public class QuitAction extends Action {

	@Override
	public void run() {
		System.exit(0); // parametr exit(int): 0 - wychodzimy i wszytko w porz¹dku, 1 - koñczymhy dzia³aniem, problemem by³ wyj¹tek , -1 - koñczymy aplikacjê w zwi¹zku z powaznym b³êdem
	}
}
