package com.svi.advancedjavatraining.object;

import com.google.gson.annotations.SerializedName;

public class Province {
	@SerializedName(value = "name")
	private String name;

	@SerializedName(value = "region")
	private String region;

	@SerializedName(value = "key")
	private String key;

	public Province(String name, String region, String key) {
		super();
		this.name = name;
		this.region = region;
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public String getRegion() {
		return region;
	}

	public String getKey() {
		return key;
	}

}
