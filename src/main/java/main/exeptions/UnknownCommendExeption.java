package main.exeptions;

@SuppressWarnings("serial")
public class UnknownCommendExeption extends Exception {
	public UnknownCommendExeption(String cmd) {
		super("Nieznana komenda " + cmd);
	}
}
