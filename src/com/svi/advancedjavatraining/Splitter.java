package com.svi.advancedjavatraining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.svi.advancedjavatraining.object.CityInfo;

public class Splitter {

	public static void main(String[] args) throws IOException {
		File src = new File("Input/ph.json");

		try (BufferedReader br = new BufferedReader(new FileReader(src))) {

			StringBuilder sb = new StringBuilder();
			String st;
			while ((st = br.readLine()) != null) {
				sb.append(st);
			}
			Type listType = new TypeToken<ArrayList<CityInfo>>() {}.getType();
			List<CityInfo> cityInfos = new Gson().fromJson(sb.toString(), listType);
			for (CityInfo cityInfo : cityInfos) {
				File provincialFolder = new File("Input" + File.separator + cityInfo.getAdmin_name());
				if(!provincialFolder.exists()) {
					provincialFolder.mkdirs();
				}
				String cityName = cityInfo.getCity();
				if(cityName.startsWith("City of ")) {
					cityName = cityName.replace("City of ", "");
				} else if (cityName.endsWith(" City")) {
					cityName = cityName.replace(" City", "");
				}
				try(FileWriter myWriter = new FileWriter(provincialFolder + File.separator + cityName + ".json")) {
					
				      myWriter.write(new Gson().toJson(cityInfo));
				      myWriter.close();
				      System.out.println("Successfully wrote to the file.");
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
			}
		}

	}

}
