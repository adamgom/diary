package actions;

public class RemoveAction extends Action {

	@Override
	public void run() {
		repository.remove((Long)params.get("remove"));
	}
}
