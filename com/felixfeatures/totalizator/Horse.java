package com.felixfeatures.totalizator;

public class Horse {

	private String name;
	// Default coefficient = 1
	private int coefficient = 100;

	public Horse(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

}
