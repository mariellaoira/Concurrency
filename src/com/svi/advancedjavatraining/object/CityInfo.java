package com.svi.advancedjavatraining.object;

import com.google.gson.annotations.SerializedName;

public class CityInfo {
	@SerializedName(value = "city")
	private String city;
	@SerializedName(value = "lat")
	private String latitude;
	@SerializedName(value = "lng")
	private String longitude;
	@SerializedName(value = "country")
	private String country;
	@SerializedName(value = "iso2")
	private String iso2;
	@SerializedName(value = "admin_name")
	private String admin_name;
	@SerializedName(value = "capital")
	private String capital;
	@SerializedName(value = "population")
	private String population;
	@SerializedName(value = "population_proper")
	private String population_proper;

	public String getCity() {
		return city;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getCountry() {
		return country;
	}

	public String getIso2() {
		return iso2;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public String getCapital() {
		return capital;
	}

	public float getPopulation() {
		if(population.isBlank()) {
			return 0;
		} else {
			return Float.parseFloat(population);
		}
	}

	public float getPopulation_proper() {
		if(population_proper.isBlank()) {
			return 0;
		} else {
			return Float.parseFloat(population_proper);
		}
	}

}
