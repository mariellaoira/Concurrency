package com.svi.advancedjavatraining.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.svi.advancedjavatraining.constants.ConfigNames;

public class Config {
	private static String configFilePath = "config.properties";
	private static OrderedProperties config;
	static {
		File configurationFile = new File(configFilePath);

		try {
			config = new OrderedProperties();
			config.load(new FileInputStream(configurationFile));
		} catch (FileNotFoundException e) {
			config = new OrderedProperties();
			config.put(ConfigNames.PROTOCOL.name(), "https");
			config.put(ConfigNames.BASEURL.name(), "raw.githubusercontent.com");
			config.put(ConfigNames.REGIONPATH.name(), "/darklight721/philippines/master/regions.json");
			config.put(ConfigNames.PROVINCEPATH.name(), "/darklight721/philippines/master/provinces.json");
			config.put(ConfigNames.CITYPATH.name(), "/darklight721/philippines/master/cities.json");
			FileWriter writer;
			try {
				writer = new FileWriter(configurationFile);
				config.store(writer, "SVI Default");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getConfig(ConfigNames configNames) {
		return String.valueOf(config.get(configNames.name()));
	}
}
