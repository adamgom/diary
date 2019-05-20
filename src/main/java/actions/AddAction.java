package actions;

import java.text.ParseException;
import java.util.Map;

public class AddAction extends Action {

	@Override
	public void run() {
		try {
			repository.save(
				sDateFormat.parse((String) params.get("date")).getTime(),
				this.params.get("title").toString(),
				this.params.get("content").toString()
			);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
