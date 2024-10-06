package com.options;

public class PatternK2 {

	// Check if all cards match a given type (suit or rank)
	public static boolean allCardsOfType(String[] cards, String type) {
		for (String card : cards) {
			if (!card.contains(type)) {
				return false; // Found a card that does not match the specified type
			}
		}
		return true; // All cards match the specified type
	}

	// Specific methods to check all types
	public static boolean allRed(String card) {
        return card.contains("Hearts") || card.contains("Diamonds");
	}

	public static boolean allBlack(String card) {
        return card.contains("Clubs") || card.contains("Spades");
	}

	public static boolean allAces(String card) {
        return card.contains("Ace");
	}

	public static boolean allKings(String card) {
		return card.contains("King");
	}

	public static boolean allQueens(String card) {
		return card.contains("Queen");
	}
	public static boolean allJacks(String card) {
		return card.contains("Jack");
	}

	public static boolean allTens(String card) {
		return card.contains("Ten") || card.contains("10");
	}

	public static boolean allNines(String card) {
		return card.contains("Nine") || card.contains("9");
	}

	public static boolean allEights(String card) {
		return card.contains("Eight") || card.contains("8");
	}

	public static boolean allSevens(String card) {
		return card.contains("Seven") || card.contains("7");
	}

	public static boolean allSixes(String card) {
		return card.contains("Six") || card.contains("6");
	}

	public static boolean allFives(String card) {
		return card.contains("Five") || card.contains("5");
	}

	public static boolean allFours(String card) {
		return card.contains("Four") || card.contains("4");
	}

	public static boolean allThrees(String card) {
		return card.contains("Three") || card.contains("3");
	}

	public static boolean allTwos(String card) {
		return card.contains("Two") || card.contains("2");
	}

	public static boolean allHearts(String card) {
		return card.contains("Hearts");
	}

	public static boolean allDiamonds(String card) {
		return card.contains("Diamonds");
	}

	public static boolean allSpades(String card) {
		return card.contains("Spades");
	}

	public static boolean allClubs(String card) {
		return card.contains("Clubs");
	}
}
