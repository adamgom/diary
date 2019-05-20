package gui;

import java.io.IOException;
import gui.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Configuration;

public class Gui {
	private Stage primaryStage;
	private Scene scene;
	
	private FXMLLoader mainSceneFXML;
	private BorderPane mainBorderPane;
	private MainController mainController;
	
	public Gui(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.mainSceneFXML = new FXMLLoader(this.getClass().getResource(Configuration.mainFXMLResource));
		declareControllers();
		startPrimaryStage();
	}
	
	private void declareControllers() throws IOException {
		this.mainController = new MainController();
		this.mainSceneFXML.setController(mainController);
		this.mainBorderPane = mainSceneFXML.load();
	}
	
	private void startPrimaryStage() throws Exception {
		this.scene = new Scene(this.mainBorderPane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pamiêtnik");
		primaryStage.show();
	}
}
