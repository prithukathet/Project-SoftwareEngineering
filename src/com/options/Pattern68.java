package com.options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.artdealergame.Card;
import com.artdealergame.CardValue;
import com.artdealergame.CardSuit;

public interface Pattern68 extends Pattern35 {

	public static ArrayList<Card> onePair(ArrayList<Card> cards) {
		// Map to count occurrences of each card value
		Map<CardValue, Integer> valueCount = new HashMap<>();

		// Count occurrences of each card value
		for (Card card : cards) {
			CardValue value = card.getValue();
			valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
		}

		// Find the card value that appears at least twice
		for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
			if (entry.getValue() >= 2) {
				// Collect exactly two cards with the matching value
				ArrayList<Card> matchingCards = new ArrayList<>();
				int count = 0;
				for (Card card : cards) {
					if (card.getValue() == entry.getKey()) {
						matchingCards.add(card);
						count++;
						if (count == 2) {
							break;
						}
					}
				}
				return matchingCards;
			}
		}

		// Return an empty list if no pair is found
		return new ArrayList<>();
	}

	public static ArrayList<Card> threeOfAKind(ArrayList<Card> cards) {
		// Map to count occurrences of each card value
		Map<CardValue, Integer> valueCount = new HashMap<>();

		// Count occurrences of each card value
		for (Card card : cards) {
			CardValue value = card.getValue();
			valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
		}

		// Find the card value that appears at least three times
		for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
			if (entry.getValue() >= 3) {
				// Collect exactly three cards with the matching value
				ArrayList<Card> matchingCards = new ArrayList<>();
				int count = 0;
				for (Card card : cards) {
					if (card.getValue() == entry.getKey()) {
						matchingCards.add(card);
						count++;
						if (count == 3) {
							break;
						}
					}
				}
				return matchingCards;
			}
		}

		// Return an empty list if no "three of a kind" is found
		return new ArrayList<>();
	}

    public static ArrayList<Card> straightFlush(ArrayList<Card> cards) {
        // Map to count occurrences of each card value by suit
        Map<CardSuit, Map<CardValue, Integer>> suitValueCount = new HashMap<>();

        // Count occurrences of each card value by suit
        for (Card card : cards) {
            CardSuit suit = card.getSuit();
            CardValue value = card.getValue();
            suitValueCount.putIfAbsent(suit, new HashMap<>());
            Map<CardValue, Integer> valueCount = suitValueCount.get(suit);
            valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
        }

        // Check each suit for a straight flush
        for (Map.Entry<CardSuit, Map<CardValue, Integer>> suitEntry : suitValueCount.entrySet()) {
            CardSuit suit = suitEntry.getKey();
            Map<CardValue, Integer> valueCount = suitEntry.getValue();

            // List to store the card values
            ArrayList<CardValue> cardValues = new ArrayList<>(valueCount.keySet());
            // Sort the card values
            Collections.sort(cardValues, (a, b) -> Integer.compare(a.toInt(), b.toInt()));

            // Check for a straight of four consecutive card values
            for (int i = 0; i <= cardValues.size() - 4; i++) {
                if (cardValues.get(i + 1).toInt() == cardValues.get(i).toInt() + 1 &&
                    cardValues.get(i + 2).toInt() == cardValues.get(i).toInt() + 2 &&
                    cardValues.get(i + 3).toInt() == cardValues.get(i).toInt() + 3) {

                    // Collect the cards that form the straight flush
                    ArrayList<Card> straightFlushCards = new ArrayList<>();
                    for (Card card : cards) {
                        if (card.getSuit() == suit &&
                            (card.getValue() == cardValues.get(i) ||
                             card.getValue() == cardValues.get(i + 1) ||
                             card.getValue() == cardValues.get(i + 2) ||
                             card.getValue() == cardValues.get(i + 3))) {
                            straightFlushCards.add(card);
                        }
                    }
                    return straightFlushCards;
                }
            }
        }

        // Return an empty list if no straight flush is found
        return new ArrayList<>();
    }

    public static ArrayList<Card> straight(ArrayList<Card> cards) {
        // Map to count occurrences of each card value
        Map<CardValue, Integer> valueCount = new HashMap<>();

        // Count occurrences of each card value
        for (Card card : cards) {
            CardValue value = card.getValue();
            valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
        }

        // List to store the card values
        ArrayList<CardValue> cardValues = new ArrayList<>(valueCount.keySet());
        // Sort the card values
        Collections.sort(cardValues, (a, b) -> Integer.compare(a.toInt(), b.toInt()));

        // Check for a straight of four consecutive card values
        for (int i = 0; i <= cardValues.size() - 4; i++) {
            if (cardValues.get(i + 1).toInt() == cardValues.get(i).toInt() + 1 &&
                cardValues.get(i + 2).toInt() == cardValues.get(i).toInt() + 2 &&
                cardValues.get(i + 3).toInt() == cardValues.get(i).toInt() + 3) {
                
                // Collect the cards that form the straight
                ArrayList<Card> straightCards = new ArrayList<>();
                Set<CardSuit> suits = new HashSet<>();
                for (Card card : cards) {
                    if (card.getValue() == cardValues.get(i) ||
                        card.getValue() == cardValues.get(i + 1) ||
                        card.getValue() == cardValues.get(i + 2) ||
                        card.getValue() == cardValues.get(i + 3)) {
                        straightCards.add(card);
                        suits.add(card.getSuit());
                    }
                }
                
                // Ensure the cards are not all of the same suit
                if (suits.size() > 1) {
                    return straightCards;
                }
            }
        }

        // Return an empty list if no straight is found
        return new ArrayList<>();
    }
}