package com.options;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.artdealergame.Card;
import com.artdealergame.CardSuit;
import com.artdealergame.CardValue;

public interface Pattern35 extends PatternBase {

    public static ArrayList<Card> singleDigitPrimes(ArrayList<Card> cards) {
        // Define the set of single-digit prime numbers
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        Set<CardValue> singleDigitPrimes = EnumSet.of(CardValue.TWO, CardValue.THREE, CardValue.FIVE, CardValue.SEVEN, CardValue.JACK, CardValue.KING);
        for (Card card : cards) {
            if (singleDigitPrimes.contains(card.getValue())) {
                matchingCards.add(card);
            }
        }

        return matchingCards;
    }

    public static ArrayList<Card> checkAceAndBlackjack(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.ACE) {
                matchingCards.add(card);
            } else if (card.getValue() == CardValue.JACK
                    && (card.getSuit() == CardSuit.CLUBS || card.getSuit() == CardSuit.SPADES)) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> findCombinationsToNine(ArrayList<Card> cards) {
        Set<Card> matchingCards = new HashSet<>(); // Use a Set to avoid duplicates
        int n = cards.size();
        // Check all combinations using bit masking
        for (int i = 1; i < (1 << n); i++) { // from 1 to 2^n - 1
            int sum = 0;
            List<Card> combination = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if the j-th bit in i is set
                if ((i & (1 << j)) != 0) {
                    sum += cards.get(j).getValue().toInt();
                    combination.add(cards.get(j));
                }
            }
            if (sum == 9) {
                matchingCards.addAll(combination);
            }
        }

        return new ArrayList<>(matchingCards);
    }
}
