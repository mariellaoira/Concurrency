package com.svi.advancedjavatraining.object;

public class PopulationData {
	private String province;
	private String city;
	private double population;

	public PopulationData(String province, String city, double population) {
		super();
		this.province = province;
		this.city = city;
		this.population = population;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public double getPopulation() {
		return population;
	}

}
