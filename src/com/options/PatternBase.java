package com.options;

import com.artdealergame.CardSuit;
import com.artdealergame.Card;
import com.artdealergame.CardValue;

public interface PatternBase {

    // Specific methods to check all types
    public static boolean allRed(Card card) {
        return card.getSuit() == CardSuit.HEARTS || card.getSuit() == CardSuit.DIAMONDS;
    }

    public static boolean allBlack(Card card) {
        return card.getSuit() == CardSuit.SPADES || card.getSuit() == CardSuit.CLUBS;
    }

    public static boolean allAces(Card card) {
        return card.getValue() == CardValue.ACE;
    }

    public static boolean allKings(Card card) {
        return card.getValue() == CardValue.KING;
    }

    public static boolean allQueens(Card card) {
        return card.getValue() == CardValue.QUEEN;
    }

    public static boolean allJacks(Card card) {
        return card.getValue() == CardValue.JACK;
    }

    public static boolean allTens(Card card) {
        return card.getValue() == CardValue.TEN;
    }

    public static boolean allNines(Card card) {
        return card.getValue() == CardValue.NINE;
    }

    public static boolean allEights(Card card) {
        return card.getValue() == CardValue.EIGHT;
    }

    public static boolean allSevens(Card card) {
        return card.getValue() == CardValue.SEVEN;
    }

    public static boolean allSixes(Card card) {
        return card.getValue() == CardValue.SIX;
    }

    public static boolean allFives(Card card) {
        return card.getValue() == CardValue.FIVE;
    }

    public static boolean allFours(Card card) {
        return card.getValue() == CardValue.FOUR;
    }

    public static boolean allThrees(Card card) {
        return card.getValue() == CardValue.THREE;
    }

    public static boolean allTwos(Card card) {
        return card.getValue() == CardValue.TWO;
    }

    public static boolean allHearts(Card card) {
        return card.getSuit() == CardSuit.HEARTS;
    }

    public static boolean allDiamonds(Card card) {
        return card.getSuit() == CardSuit.DIAMONDS;
    }

    public static boolean allSpades(Card card) {
        return card.getSuit() == CardSuit.SPADES;
    }

    public static boolean allClubs(Card card) {
        return card.getSuit() == CardSuit.CLUBS;
    }
}
