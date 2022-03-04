package com.svi.advancedjavatraining.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.svi.advancedjavatraining.object.CityInfo;

public class JSONFileReader {
	private String province;
	private String city;
	
	public JSONFileReader(String province, String city) {
		super();
		this.province = province;
		this.city = city;
	}
	
	public CityInfo getData() throws IOException {
		Gson gson = new Gson();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File src = new File("Input" + File.separator + province + File.separator + city + ".json");
		if(src.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(src))) {

			StringBuilder sb = new StringBuilder();
			String st;
			while ((st = br.readLine()) != null) {
				sb.append(st);
			}
			return gson.fromJson(sb.toString(), CityInfo.class);
		}
		} else {
			return null;
		}
		
	}

}
