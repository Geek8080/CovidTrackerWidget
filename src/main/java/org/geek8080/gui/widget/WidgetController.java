package org.geek8080.gui.widget;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;
import org.geek8080.config.ConfigModel;
import org.geek8080.config.ConfigurationService;
import org.geek8080.datafetch.DataProviderService;
import org.geek8080.datafetch.model.CountryData;
import org.geek8080.datafetch.model.CovidDataModel;
import org.geek8080.datafetch.model.GlobalData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WidgetController implements Initializable {

	private ScheduledExecutorService executorService;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Text globalReportText;

	@FXML
	private Text countryCodeText;

	@FXML
	private Text countryReportText;

	private ConfigModel configuration;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			configuration = new ConfigurationService().getConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initializeScheduler();
		initializeContextMenu();
		countryCodeText.setText(configuration.getCountryCode());
	}

	private void initializeScheduler() {
		executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(this::loadData, 0, configuration.getRefreshIntervalInSeconds(), TimeUnit.SECONDS);
	}

	private void loadData() {
		DataProviderService dataProviderService = new DataProviderService();
		CovidDataModel dataModel = dataProviderService.getData(configuration.getCountryName());

		Platform.runLater(() -> {
			GlobalData globalData = dataModel.getGlobalData();
			CountryData countryData = dataModel.getCountryData();

			globalReportText.setText(getFormattedData(globalData.getCases(), globalData.getRecovered(), globalData.getDeaths()));
			countryReportText.setText(getFormattedData(countryData.getCases(), countryData.getRecovered(), countryData.getDeaths()));

			readjustStaged(globalReportText.getScene().getWindow());
		});
	}

	private String getFormattedData(long totalCases, long recoveredCases, long deaths){
		return String.format("Cases: %-10d | Recovered: %-9d | Deaths: %-9d", totalCases, recoveredCases, deaths);
	}

	private void readjustStaged(Window stage){
		stage.sizeToScene();

		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		stage.setX(visualBounds.getMaxX() - 25 - globalReportText.getScene().getWidth());
		stage.setY(visualBounds.getMinY() + 25);

	}

	private void initializeContextMenu(){
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(e -> {
			System.exit(0);
		});

		MenuItem refreshItem = new MenuItem("Refresh");
		refreshItem.setOnAction(e -> {
			executorService.schedule(this::loadData, 0, TimeUnit.SECONDS);
		});

		final ContextMenu contextMenu = new ContextMenu(exitItem, refreshItem);
		rootPane.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			if (e.isSecondaryButtonDown()){
				contextMenu.show(rootPane, e.getScreenX(), e.getScreenY());
			}else {
				contextMenu.hide();
			}
		});
	}
}
