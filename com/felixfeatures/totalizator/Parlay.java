package com.felixfeatures.totalizator;

public class Parlay {

	private Horse horse;
	private int sum;

	public Horse getHorse() {
		return horse;
	}

	public int getSum() {
		return sum;
	}

	public Parlay(Horse horse, int sum) {
		this.horse = horse;
		this.sum = sum;
	}

}
