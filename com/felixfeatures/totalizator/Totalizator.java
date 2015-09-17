package com.felixfeatures.totalizator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Totalizator {

	private static List<User> users = new ArrayList<User>();
	private static List<Horse> horses = new ArrayList<Horse>();
	private static User currentUser;

	public static void main(String[] args) {

		// Add users
		addUser("Donald McDonald", "donald", "000", 0);
		addUser("Kenzie McKenzie", "kenzie", "000", 0);
		addUser("Hara O'Hara", "hara", "000", 0);
		addUser("Connor O'Connor", "connor", "000", 0);

		addUser("Admin McAdmin", "admin", "000", 1);

		// Add horses
		Horse horse1 = new Horse("Betty");
		horse1.setCoefficient(117);
		horses.add(horse1);
		Horse horse2 = new Horse("Pegasus");
		horse2.setCoefficient(203);
		horses.add(horse2);
		Horse horse3 = new Horse("Ulisses");
		horse3.setCoefficient(257);
		horses.add(horse3);
		Horse horse4 = new Horse("Gorgona");
		horse4.setCoefficient(557);
		horses.add(horse4);

		// Users make bets
		((Client) users.get(0)).addParlay(horse1, 100);
		((Client) users.get(1)).addParlay(horse2, 200);
		((Client) users.get(2)).addParlay(horse3, 250);
		((Client) users.get(3)).addParlay(horse1, 500);

		// Race ends
		generateResults();

		// Horse which wins the race
		System.out.println("Horse won: " + horses.get(0).getName());

		// Calculate prizes
		List<String> results = calculateMoney();

		// Display prizes
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
	}

	/**
	 * Adds user to the list of users
	 * 
	 * @param name
	 *            user's name
	 * @param login
	 *            user's login
	 * @param password
	 *            user's password
	 * @param type
	 *            0 for client, 1 for administrator
	 */
	public static void addUser(String name, String login, String password,
			int type) {
		if (type == 0) {
			users.add(new Client(name, login, password));
		} else if (type == 1) {
			users.add(new Admin(name, login, password));
		} else {
			// TODO error
		}
	}

	/**
	 * Find user is users list by his login and password
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	public User findUser(String login, String password) {
		User user;
		for (int i = 0; i < users.size(); i++) {
			user = users.get(i);
			if (user.getLogin().equals(login)
					&& user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public void save() {

	}

	public void load() {

	}

	/**
	 * Imitate race and generate results
	 */
	public static void generateResults() {
		Collections.shuffle(horses);
	}

	/**
	 * Clear all bets from all users
	 */
	public void clearAllParlays() {
		for (User user : users) {
			if (user instanceof Client) {
				((Client) user).clearParlays();
			}
		}
	}

	/**
	 * Calculate prize money for clients who made bets
	 * 
	 * @return list of String "/Client's name/ and his prize"
	 */
	public static List<String> calculateMoney() {
		// Array of prizes
		int[] prizes = new int[users.size()];
		// List of results
		List<String> result = new ArrayList<String>();
		// Horse, which has win the race
		Horse winHorse = horses.get(0); // First horse is the winner

		// Iterating through users.
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			// Get user parlays, if user is client
			if (user instanceof Client) {
				List<Parlay> userParlays = ((Client) user).getParlays();
				// Iterating through client's parlays
				for (Parlay parlay : userParlays) {
					// If client's parlay was made on the winHorse, increase his
					// prize in prizes array
					if (parlay.getHorse().equals(winHorse)) {
						prizes[i] += parlay.getSum()
								* winHorse.getCoefficient();
					}
				}
			}
		}

		// Add String "/Client's name/ and his prize" to result list
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i) instanceof Client) {
				result.add(users.get(i).getName() + " wins $" + prizes[i]
						/ 100.0);
			}
		}

		return result;
	}

}
