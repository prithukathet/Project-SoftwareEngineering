package com.options;

public class PatternK2 {

	// Constants for suits and ranks
	private static final String HEARTS = "Hearts";
	private static final String DIAMONDS = "Diamonds";
	private static final String SPADES = "Spades";
	private static final String CLUBS = "Clubs";

	private static final String QUEEN = "Queen";
	private static final String KING = "King";
	private static final String ACE = "Ace";
	private static final String JACK = "Jack";
	private static final String TEN = "10";
	private static final String NINE = "9";
	private static final String EIGHT = "8";
	private static final String SEVEN = "7";
	private static final String SIX = "6";
	private static final String FIVE = "5";
	private static final String FOUR = "4";
	private static final String THREE = "3";
	private static final String TWO = "2";

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
        return card.contains("Hearts") || card.contains("DIAMONDS");
	}

	public static boolean allBlack(String card) {
        return card.contains("Clubs") || card.contains("SPADES");
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
		return card.contains("Ten");
	}

	public static boolean allNines(String card) {
		return card.contains("Nine");
	}

	public static boolean allEights(String card) {
		return acard.contains("Eight");
	}

	public static boolean allSevens(String card) {
		return card.contains("Seven");
	}

	public static boolean allSixes(String card) {
		return card.contains("Six");
	}

	public static boolean allFives(String card) {
		return card.contains("Five");
	}

	public static boolean allFours(String card) {
		return card.contains("Four");
	}

	public static boolean allThrees(String card) {
		return card.contains("Three");
	}

	public static boolean allTwos(String card) {
		return card.contains("Two");
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
