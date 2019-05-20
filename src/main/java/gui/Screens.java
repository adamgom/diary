package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public enum Screens {
	INITIAL("PaneInitial"),
	NEWENTRY("NewEntryScene"),
	OPTION("OptionScene"),
	VIEW("ViewScene");

	private FXMLLoader fxmlLoader;
	private Pane pane;
	
	private Screens(String FXMLFile) {
		this.fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/" + FXMLFile + ".fxml"));
	}
	
	public FXMLLoader getFXMLLoader() {
		return this.fxmlLoader;
	};
	
	public void loadFXML() {
		try {
			this.pane = this.fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	public Pane getPane() {
		return pane;
	}
}
