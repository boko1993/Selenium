package com.noorteck.qa.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * The Environment class represents a collection of environment settings that
 * can be loaded from a JSON file.
 */
public class Environment {
	/** A mapping of environment settings for each region. */
	private Map<String, Map<String, String>> environmentList;

	/** The currently selected environment settings. */
	private Map<String, String> environment;

	/**
	 * Creates a new Environment object and loads environment settings from a JSON
	 * file.
	 */
	public Environment() {
		try {
			JsonObject jsonObject = JsonParser.parseReader(new FileReader(Constants.ENV_FILE_PATH)).getAsJsonObject();
			Gson gson = new Gson();
			environmentList = gson.fromJson(jsonObject, Map.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Selects the environment settings for the specified region.
	 * 
	 * @param region The name of the region to select environment settings for.
	 */
	public void setEnvironment(String region) {

		environment = environmentList.get(region);
	}

	/**
	 * Gets the value of a specific environment setting.
	 * 
	 * @param key The name of the environment setting to retrieve.
	 * @return The value of the specified environment setting.
	 */
	public String getEnvData(String key) {
		return environment.get(key);
	}
}
