package com.svi.advancedjavatraining.object;

import com.google.gson.annotations.SerializedName;

public class Region {
	@SerializedName(value = "name")
	private String shortName;

	@SerializedName(value = "long")
	private String longName;

	@SerializedName(value = "key")
	private String key;

	public Region(String shortName, String longName, String key) {
		this.shortName = shortName;
		this.longName = longName;
		this.key = key;
	}

	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return longName;
	}

	public String getKey() {
		return key;
	}

}
