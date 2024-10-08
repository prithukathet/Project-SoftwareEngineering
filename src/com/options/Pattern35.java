package com.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public interface Pattern35 extends PatternBase {

    public static boolean allSingleDigitPrimes(String[] cards) {
        List<String> primes = Arrays.asList("2 of Hearts", "2 of Diamonds", "2 of Spades", "2 of Clubs", "3 of Hearts",
                "3 of Diamonds", "3 of Spades", "3 of Clubs", "5 of Hearts", "5 of Diamonds", "5 of Spades",
                "5 of Clubs", "7 of Hearts", "7 of Diamonds", "7 of Spades", "7 of Clubs");
        for (String card : cards) {
            if (!primes.contains(card)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAceAndBlackjack(String[] cards) {
        boolean hasAce = false;
        boolean hasBlackjack = false;
        for (String card : cards) {
            if (card.equals("Ace of Hearts") || card.equals("Ace of Diamonds") || card.equals("Ace of Clubs")
                    || card.equals("Ace of Spades")) {
                hasAce = true;
            } else if (card.equals("Jack of Clubs") || card.equals("Jack of Spades")) {
                hasBlackjack = true;
            }
        }
        return hasAce && hasBlackjack;
    }

    public static List<String> findCombinationsToNine(String[] cards) {
            HashMap<String, Integer> cardValues = new HashMap<>();
            // Define card values
            cardValues.put("Ace", 1);
            cardValues.put("Two", 2);
            cardValues.put("Three", 3);
            cardValues.put("Four", 4);
            cardValues.put("Five", 5);
            cardValues.put("Six", 6);
            cardValues.put("Seven", 7);
            cardValues.put("Eight", 8);
            cardValues.put("Nine", 9);
            cardValues.put("Ten", 10);
            cardValues.put("Jack", 11);
            cardValues.put("Queen", 12);
            cardValues.put("King", 13);

            List<String> combinations = new ArrayList<>();
            
            // Check all pairs of cards
            for (int i = 0; i < cards.length; i++) {
                for (int j = i + 1; j < cards.length; j++) {
                    int value1 = cardValues.get(cards[i].split(" ")[0]);
                    int value2 = cardValues.get(cards[j].split(" ")[0]);
                    
                    if (value1 + value2 == 9) {
                        combinations.add(cards[i] + " and " + cards[j]);
                    }
                }
            }
            return combinations;
        }
}
