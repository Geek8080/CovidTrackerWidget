package org.geek8080.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ConfigurationService {

	private final File SETTINGS_FILE = new File("settings.json");
	private Gson gson = new GsonBuilder().create();

	public ConfigModel getConfiguration() throws IOException {
		if (!SETTINGS_FILE.exists()){
			createSettingsFile();
		}
		return getConfigurationFromFile();
	}

	private ConfigModel getConfigurationFromFile() throws IOException {
		try(Reader reader = new FileReader(SETTINGS_FILE)){
			return gson.fromJson(reader, ConfigModel.class);
		}
	}

	private void createSettingsFile() throws IOException {
		System.out.println("Creating JSON file");
		ConfigModel configModel = new ConfigModel();
		try(Writer writer = new FileWriter(SETTINGS_FILE, false)){
			gson.toJson(configModel, writer);
		}
	}
}
