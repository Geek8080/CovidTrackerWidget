package org.geek8080;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.geek8080.datafetch.DataProviderService;
import org.geek8080.datafetch.model.CovidDataModel;

public class Launch extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/org/geek8080/gui/widget/Widget.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		CovidDataModel dataModel = new DataProviderService().getData("India");
		System.out.println(dataModel);
	}
}
