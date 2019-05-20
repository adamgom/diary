package gui.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import main.Commend;
import main.Configuration;
import main.Engine;
import main.Engine.ActionParameter;
import main.exeptions.UnknownCommendExeption;

public class ViewController {
	private long[] fileList;
	@FXML private Button buttonRemove, buttonRefresh;
	@FXML private ListView<String> listView;
	@FXML private TextArea textArea;
	private long selectedItem; 
	
	public ViewController(MainController mainController) {
		this.fileList = null;
	}
	
	@FXML
	public void initialize() {
		this.buttonRemove.addEventHandler(ActionEvent.ACTION, event -> removeEntry());
		this.buttonRefresh.addEventHandler(ActionEvent.ACTION, e -> refresh());
		this.listView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectItem());
		refresh();
	}
	
	private void removeEntry() {
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
		this.selectedItem = this.fileList[this.listView.getSelectionModel().getSelectedIndex()];
		try {
			Engine.getInstance().exec(Commend.GET).runAction(
				new ActionParameter("get", selectedItem)
			);
		} catch (UnknownCommendExeption e) {
			e.printStackTrace();
		}
		System.out.println(this.selectedItem);
	}
}


