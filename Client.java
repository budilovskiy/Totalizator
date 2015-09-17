package com.felixfeatures.totalizator;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

	private List<Parlay> parlays = new ArrayList<Parlay>();

	public Client(String name, String login, String password) {
		super(name, login, password);
	}

	public void addParlay(Horse horse, int sum) {
		parlays.add(new Parlay(horse, sum));
	}

	public void clearParlays() {
		parlays.clear();
	}

	public List<Parlay> getParlays() {
		return parlays;
	}

}
