package actions;

import java.util.Map;

public class QuitAction extends Action {

	@Override
	public void run() {
		System.exit(0); // parametr exit(int): 0 - wychodzimy i wszytko w porz�dku, 1 - ko�czymhy dzia�aniem, problemem by� wyj�tek , -1 - ko�czymy aplikacj� w zwi�zku z powaznym b��dem
	}
}
