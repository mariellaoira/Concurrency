package com.svi.advancedjavatraining.object;

import com.google.gson.annotations.SerializedName;

public class City {
	@SerializedName(value = "name")
	private String name;

	@SerializedName(value = "province")
	private String province;

	@SerializedName(value = "city")
	private boolean city;

	public City(String name, String province, boolean city) {
		this.name = name;
		this.province = province;
		this.city = city;
	}

	public City(String name, String province) {
		this.name = name;
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public String getProvince() {
		return province;
	}

	public boolean isCity() {
		return city;
	}
}
