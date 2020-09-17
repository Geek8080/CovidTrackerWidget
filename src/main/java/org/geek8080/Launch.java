package org.geek8080;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.geek8080.datafetch.DataProviderService;
import org.geek8080.datafetch.model.CovidDataModel;

public class Launch extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new StackPane(new Label("Hello JavaFX 14")), 200, 200);
		primaryStage.setScene(scene);
		primaryStage.show();

		CovidDataModel dataModel = new DataProviderService().getData("India");
		System.out.println(dataModel);
	}
}
