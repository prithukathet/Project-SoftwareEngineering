package com.options;

public class Pattern6_8 {

	public boolean flush(String[] cards) {
		String suit = cards[0].split(" ")[2]; // Get the suit of the first card
		for (String card : cards) {
			if (!card.contains(suit)) {
				return false; // If any card doesn't match the suit, return false
			}
		}
		return true; // All cards are of the same suit
	}

	public boolean onePair(String[] cards) {
		int[] ranks = new int[13]; // 0-12 represent A-K
		for (String card : cards) {
			String rank = card.split(" ")[0];
			int index = getRankIndex(rank);
			if (index != -1) {
				ranks[index]++;
			}
		}
		// Check for at least one pair
		for (int count : ranks) {
			if (count == 2)
				return true;
		}
		return false;
	}

	private int getRankIndex(String rank) {
		switch (rank) {
			case "2":
				return 0;
			case "3":
				return 1;
			case "4":
				return 2;
			case "5":
				return 3;
			case "6":
				return 4;
			case "7":
				return 5;
			case "8":
				return 6;
			case "9":
				return 7;
			case "10":
				return 8;
			case "Jack":
				return 9;
			case "Queen":
				return 10;
			case "King":
				return 11;
			case "Ace":
				return 12;
			default:
				return -1; // Invalid rank
		}
	}
}