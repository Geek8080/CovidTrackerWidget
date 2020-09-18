package org.geek8080.gui.widget;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class WidgetController implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Text globalReportText;

	@FXML
	private Text countryCodeText;

	@FXML
	private Text countryReportText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
