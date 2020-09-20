package org.geek8080;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.geek8080.datafetch.DataProviderService;
import org.geek8080.datafetch.model.CovidDataModel;

public class Launch extends Application {

	private double xOffset;
	private double yOffset;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.setOpacity(0);
		primaryStage.show();

		Stage secondaryStage = new Stage();
		secondaryStage.initStyle(StageStyle.UNDECORATED);
		secondaryStage.initOwner(primaryStage);
		
		Parent root = FXMLLoader.load(getClass().getResource("/org/geek8080/gui/widget/Widget.fxml"));
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.show();

		// Right top alignment
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		secondaryStage.setX(visualBounds.getMaxX() - 25 - scene.getWidth());
		secondaryStage.setY(visualBounds.getMinY() + 25);

		// Add drag Support
		scene.setOnMousePressed(event -> {
			xOffset = secondaryStage.getX() - event.getScreenX();
			yOffset = secondaryStage.getY() - event.getScreenY();
		});
		scene.setOnMouseDragged(event -> {
			secondaryStage.setX(event.getScreenX() + xOffset);
			secondaryStage.setY(event.getScreenY() + yOffset);
		});
	}
}
