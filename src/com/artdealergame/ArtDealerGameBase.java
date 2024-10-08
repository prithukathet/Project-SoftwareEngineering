package com.artdealergame;

import java.util.Random;

public abstract class ArtDealerGameBase {
    protected String dealerPattern;
    protected String[] patterns;
    protected javax.swing.JLabel guessStatusLabel; // Add this line

    public ArtDealerGameBase(String[] patterns) {
        this.patterns = patterns;
        resetGame();
    }

    public void resetGame() {
        Random random = new Random();
        dealerPattern = patterns[random.nextInt(patterns.length)];
        System.out.println("DEBUG: Dealer's pattern: " + dealerPattern);
    }

    public boolean checkCardMatch(String card) {
        return isDealerBuying(card);
    }

    public abstract boolean isDealerBuying(String card);

    // Method to check if the user's guess matches the dealer's pattern
    public boolean checkPatternGuess(String pattern) {
        return dealerPattern.equals(pattern);
    }

    // Method to get the current dealer's pattern
    public String getDealerPattern() {
        return dealerPattern;
    }

    public String[] getPatterns() {
        return patterns;
    } 
}