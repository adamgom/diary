package main;

import static main.Commend.QUIT;
import static main.Commend.ADD;
import static main.Commend.REMOVE;
import static main.Commend.GETALL;
import static main.Commend.GET;
import java.util.Calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import main.exeptions.UnknownCommendExeption;

public class Engine {
	private static Engine instance;
		
	private Engine() {
	}
	
	public static Engine getInstance() {
		if (instance == null) {
			return instance = new Engine();
		} else {
			return instance;
		}
	}

//	public Message exec(String cmd) throws UnknownCommendExeption {
//		return exec(Commend.getCommendByText(cmd));
//	}
	
	public Message exec(Commend cmd) throws UnknownCommendExeption {
		switch (cmd) {
		case QUIT:
			return new Message("Wyjœcie", QUIT.getAction());
		case ADD:
			return new Message("Dodanie", ADD.getAction());
		case REMOVE:
			return new Message("Usuwanie", REMOVE.getAction());
		case GETALL:
			return new Message("pobranie ca³oœci", GETALL.getAction());
		case GET:
			return new Message("pobranie jednego wpisu", GET.getAction());
		default:
			return new Message("dafault message - declared in Engine");
		}
	}
	
	public static class ActionParameter {
		private String name;
		private Object value;
		
		public ActionParameter(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}
	}

	public void initClock(Label label) {
		Timeline clock = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> label.setText(Configuration.dateLong.format(Calendar.getInstance().getTime()))));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
	}

//	public static class CommendParameter {
//	private String name;
//	private Object value;
//	
//	public CommendParameter(String name, Object value) {
//		this.name = name;
//		this.value = value;
//	}
//	public String getName() {
//		return name;
//	}
//	public Object getValue() {
//		return value;
//	}
//}
}
