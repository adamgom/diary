package gui.controllers;

import java.io.IOException;
import gui.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Commend;
import main.Configuration;
import main.Engine;
import main.exeptions.UnknownCommendExeption;
import net.bytebuddy.asm.Advice.This;

public class MainController {
	private boolean isLoged;
	private boolean isOption;
	private ViewController viewController;
	private NewEntryController newEntryController;
	
	@FXML private Button buttonLogin, buttonOption, buttonExit, buttonNewEntry;
	@FXML private Label labelDate, labelUser;
	@FXML private StackPane centralStackPane;
	
	public MainController() {
		this.isOption = false;
		this.isLoged = false;
		this.viewController = new ViewController(this);
		this.newEntryController = new NewEntryController(viewController);
	}
	
	@FXML 
	public void initialize() throws IOException {
		this.buttonLogin.addEventHandler(ActionEvent.ACTION, event -> login());
		this.buttonExit.addEventHandler(ActionEvent.ACTION, event -> exit());
		this.buttonNewEntry.addEventHandler(ActionEvent.ACTION, event -> newWindowOpen());
		this.buttonOption.addEventHandler(ActionEvent.ACTION, event -> option());
		
		this.buttonNewEntry.setDisable(true);
		Engine.getInstance().initClock(this.labelDate);
		
		Screens.VIEW.getFXMLLoader().setController(viewController);

		Screens.INITIAL.loadFXML();
		Screens.OPTION.loadFXML();
		Screens.VIEW.loadFXML();
		
		setPane(Screens.INITIAL.getPane());
		login();
	}

	private void option() {
		if (isOption) {
			if (isLoged) {
				setPane(Screens.VIEW.getPane());
			} else {
				setPane(Screens.INITIAL.getPane());
			}
		} else {
			setPane(Screens.OPTION.getPane());
		}
		this.isOption = !isOption;
	}
	
	private void setPane(Pane pane) {
		this.centralStackPane.getChildren().clear();
		this.centralStackPane.getChildren().add(pane);
	}
	
	private void exit() {
		try {
			Engine.getInstance().exec(Commend.QUIT).runAction();
		} catch (UnknownCommendExeption e) {
			e.printStackTrace();
		}
	}
	
	private void login() {
		if (!isLoged) {
			this.labelUser.setText("Zalogowano");
			this.buttonLogin.setText("Wyloguj");
			this.buttonNewEntry.setDisable(false);
			setPane(Screens.VIEW.getPane());
		} else {
			this.labelUser.setText("Proszê siê zalogowaæ");
			this.buttonLogin.setText("Zaloguj");
			this.buttonNewEntry.setDisable(true);
			setPane(Screens.INITIAL.getPane());
		}
		this.isLoged = !isLoged;
		isOption = false;
	}
	
	private void newWindowOpen(){
		Stage newEntryStage = new Stage();
		FXMLLoader anchorPaneFXML = new FXMLLoader(this.getClass().getResource(Configuration.newEntryFXMLResource));
		AnchorPane anchorPane = new AnchorPane();
		
		anchorPaneFXML.setController(this.newEntryController);
		try {anchorPane = anchorPaneFXML.load();} catch (IOException e) {}
		
		Scene newEntryScene = new Scene(anchorPane, 600, 400);
		
		newEntryController.setSceneToController(newEntryScene);
		newEntryStage.initStyle(StageStyle.UNDECORATED);
//		newEntryStage.initModality(Modality.APPLICATION_MODAL);
		newEntryStage.setScene(newEntryScene);
		newEntryStage.show();
	}
}
