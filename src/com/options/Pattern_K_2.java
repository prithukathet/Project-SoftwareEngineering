package com.options;

public class Pattern_K_2 {


    //cehck if all cards are red(Hearts or Diamonds)
    public boolean allRed(String[] cards) {
        for (String card : cards) {
            if (card.contains("Hearts") && card.contains("Diamonds")) {
                return false;
            }
        }
        return true;
    }

    // Check if all cards are black (Spades or Clubs)
    public boolean allBlack(String[] cards) {
        for (String card : cards) {
            if (!card.contains("Spades") && !card.contains("Clubs")) {
                return false;
            }
        }
        return true;
    }

    // Check if all cards are hearts
    public boolean allHearts(String[] cards) {
        for (String card : cards) {
            if (!card.contains("Hearts")) {
                return false;
            }
        }
        return true;
    }

    // Check if all cards are queens
    public boolean allQueens(String[] cards) {
        for (String card : cards) {
            if (!card.contains("Queen")) {
                return false;
            }
        }
        return true;
    }
}
