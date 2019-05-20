package gui.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import main.Commend;
import main.Configuration;
import main.Engine;
import main.Message;
import main.Engine.ActionParameter;
import main.exeptions.UnknownCommendExeption;

public class ViewController {
	private long[] fileList;
	@FXML private Button buttonRemove, buttonRefresh;
	@FXML private ListView<String> listView;
	@FXML private TextArea textArea;
	@FXML private Label titleLabel;
	private long selectedItem;
	private Message message;
	
	public ViewController(MainController mainController) {
		this.fileList = null;
		message = null;
		selectedItem = 0;
	}
	
	@FXML
	public void initialize() {
		this.buttonRemove.addEventHandler(ActionEvent.ACTION, event -> removeEntry());
		this.buttonRefresh.addEventHandler(ActionEvent.ACTION, e -> refresh());
		this.listView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectItem());
		refresh();
	}
	
	private void removeEntry() {
		if (selectedItem == 0) {
			titleLabel.setText("nie wybrano wiadomoœci");
		} else {
			try {
				Engine.getInstance().exec(Commend.REMOVE).runAction(new ActionParameter("remove", selectedItem));
			} catch (UnknownCommendExeption e) {
				e.printStackTrace();
			}
			refresh();
			textArea.clear();
			titleLabel.setText("wpis usuniêto");
			selectedItem = 0;
		}
	}
	
	protected void refresh() {
		this.listView.getItems().clear();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Configuration.dFormatLong);
		fileList = (long[]) Commend.GETALL.getAction().getParams().get("file");
		for (int i = 0 ; i < fileList.length ; i++) {
			this.listView.getItems().add(dateFormat.format(new Date(fileList[i])));
		}
	}
	
	private void selectItem() {
		if (listView.getItems().isEmpty()) {
			titleLabel.setText("brak wpisów");
		} else {
			selectedItem = fileList[this.listView.getSelectionModel().getSelectedIndex()];	
			try {
				Engine.getInstance().exec(Commend.GET).runAction(new ActionParameter("get", selectedItem));
			} catch (UnknownCommendExeption e) {
				e.printStackTrace();
			}
			titleLabel.setText(message.getBean().getTitle());
			textArea.setText(message.getBean().getText());
		}
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
}
