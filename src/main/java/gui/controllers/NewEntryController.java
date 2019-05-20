package gui.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.Commend;
import main.Configuration;
import main.Engine;
import main.Engine.ActionParameter;
import main.exeptions.UnknownCommendExeption;

public class NewEntryController {
	private Scene newEntryScene;
	private SimpleDateFormat dFormat;
	private ViewController viewController;
	
	@FXML private Button buttonAdd, buttonCancel;
	@FXML private Label labelDate;
	@FXML private TextField textFieldTitle;
	@FXML private TextArea textAreaContent;
	
	public NewEntryController(ViewController viewController) {
		this.dFormat = new SimpleDateFormat(Configuration.dFormatLong);
		this.viewController = viewController;
	}
	
	@FXML 
	public void initialize() {
		this.buttonCancel.addEventHandler(ActionEvent.ACTION, event -> closeNewEntryScene());
		this.labelDate.setText(dFormat.format(Calendar.getInstance().getTime()));
		this.buttonAdd.addEventHandler(ActionEvent.ACTION, event -> addNewEntry());
		this.buttonAdd.setDisable(true);
		this.textFieldTitle.textProperty().addListener((Observable, oldValue, newValue) -> this.buttonAdd.setDisable(newValue.isEmpty()));
		Engine.getInstance().initClock(this.labelDate);
//		this.textFieldTitle.setText("title from text field");
//		this.textAreaContent.setText("content from text area");
	}
	
	public void setSceneToController(Scene newEntryScene) {
		this.newEntryScene = newEntryScene;
	}
	
	private void closeNewEntryScene() {
		this.newEntryScene.getWindow().hide();
	}
	
	private void addNewEntry() {
		try {
			Engine.getInstance().exec(Commend.ADD).runAction(
				new ActionParameter("date", labelDate.getText()),
				new ActionParameter("title", textFieldTitle.getText()),
				new ActionParameter("content", textAreaContent.getText())
			);
		} catch (UnknownCommendExeption e) {
			e.printStackTrace();
		}
		closeNewEntryScene();
		viewController.refresh();
	}
}
