package com.svi.advancedjavatraining.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.svi.advancedjavatraining.config.Config;
import com.svi.advancedjavatraining.constants.ConfigNames;
import com.svi.advancedjavatraining.object.City;
import com.svi.advancedjavatraining.object.Province;
import com.svi.advancedjavatraining.object.Region;

public class WebLoader {
	private URL urlPath;
	private Gson gson = new Gson();
	
	public List<Region> getRegions() throws IOException {
		urlPath = new URL(Config.getConfig(ConfigNames.PROTOCOL), Config.getConfig(ConfigNames.BASEURL),
				Config.getConfig(ConfigNames.REGIONPATH));
		Connection conn = Jsoup.connect(urlPath.toExternalForm()).maxBodySize(Integer.MAX_VALUE);
		System.out.println(conn.request().url());
		
		String jsonResponse = conn.ignoreContentType(true).execute().body();
		
		Type listType = new TypeToken<ArrayList<Region>>(){}.getType();
		List<Region> regions = gson.fromJson(jsonResponse, listType);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regions;
	}
	
	public List<Province> getProvinces() throws IOException {
		urlPath = new URL(Config.getConfig(ConfigNames.PROTOCOL), Config.getConfig(ConfigNames.BASEURL),
				Config.getConfig(ConfigNames.PROVINCEPATH));
		Connection conn = Jsoup.connect(urlPath.toExternalForm()).maxBodySize(Integer.MAX_VALUE);
		System.out.println(conn.request().url());
		
		String jsonResponse = conn.ignoreContentType(true).execute().body();
		
		Type listType = new TypeToken<ArrayList<Province>>(){}.getType();
		List<Province> provinces = gson.fromJson(jsonResponse, listType);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provinces;
	}
	
	public List<City> getCities() throws IOException {
		urlPath = new URL(Config.getConfig(ConfigNames.PROTOCOL), Config.getConfig(ConfigNames.BASEURL),
				Config.getConfig(ConfigNames.CITYPATH));
		Connection conn = Jsoup.connect(urlPath.toExternalForm()).maxBodySize(Integer.MAX_VALUE);
		System.out.println(conn.request().url());
		
		String jsonResponse = conn.ignoreContentType(true).execute().body();
		
		Type listType = new TypeToken<ArrayList<City>>(){}.getType();
		List<City> cities = gson.fromJson(jsonResponse, listType);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}
	
}
