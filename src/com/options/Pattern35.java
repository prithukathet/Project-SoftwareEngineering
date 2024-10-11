package com.options;

import java.util.Arrays;
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

    public static boolean sumToNine(String[] cards) {
        int sum = 0;
        for (String card : cards) {
            String rank = card.split(" ")[0]; // Get the rank (e.g., "2", "Queen", etc.)
            switch (rank) {
            case "2":
                sum += 2;
                break;
            case "3":
                sum += 3;
                break;
            case "4":
                sum += 4;
                break;
            case "5":
                sum += 5;
                break;
            case "6":
                sum += 6;
                break;
            case "7":
                sum += 7;
                break;
            case "8":
                sum += 8;
                break;
            case "9":
                sum += 9;
                break;
            case "10":
                sum += 10;
                break;
            case "Jack":
                sum += 11;
                break; // You can adjust values as per your game's rules
            case "Queen":
                sum += 12;
                break;
            case "King":
                sum += 13;
                break;
            default:
                break;
            }
        }
        return sum == 9;
    }
}
