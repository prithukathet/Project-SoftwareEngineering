package com.artdealergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ArtDealerGameBase {
    protected String dealerPattern;
    protected String[] patterns;
    protected javax.swing.JLabel guessStatusLabel;
    Card[] cards = new Card[52];
    ArrayList<Card> selectedCards = new ArrayList<Card>();
    private int numberOfGuesses = 0;

    private List<GameObserver> observers = new ArrayList<>();

    public ArtDealerGameBase(String[] patterns) {
        this.patterns = patterns;

        for (int i = 0; i < CardSuit.values().length; i++) {
            for (int j = 0; j < CardValue.values().length; j++) {
                cards[i * 13 + j] = new Card(CardSuit.values()[i], CardValue.values()[j]);
            }
        }

        resetGame();
    }

    public void resetGame() {
        clearSelectedCards();
        Random random = new Random();
        dealerPattern = patterns[random.nextInt(patterns.length)];
        System.out.println("DEBUG: Dealer's pattern: " + dealerPattern);
    }

    public void overridePattern(String pattern) {
        this.dealerPattern = pattern;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    private void notifySelectedCardsObservers() {
        for (GameObserver observer : observers) {
            observer.update(selectedCards);
        }
    }

    private void notifyAttemptObservers() {
        for (GameObserver observer : observers) {
            observer.update(numberOfGuesses);
        }
    }

    public int getNumberOfGuessesRemaining() {
        return Constants.MAX_GUESSES - numberOfGuesses;
    }

    public boolean cardSelected(Card card) {
        if (card.isSelected()) {
            // If the card is selected, deselect itand remove it from the selected cards
            // list
            card.deselect();
            selectedCards.remove(card);
        } else if (selectedCards.size() < Constants.MAX_SELECTED_CARDS) {
            // If the card is not selected, select it and add it to the selected cards list
            // if the selected cards list has less than 4 cards
            card.select();
            selectedCards.add(card);
        } else {
            return false;
        }

        notifySelectedCardsObservers();

        return true;
    }

    public abstract ArrayList<Card> isDealerBuying(ArrayList<Card> card);

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

    public Card[] getCards() {
        return cards;
    }

    public void clearSelectedCards() {
        for (Card card : selectedCards) {
            card.deselect();
        }

        selectedCards.clear();
        notifySelectedCardsObservers();
    }

    public ArrayList<Card> getSelectedCards() {
        return selectedCards;
    }

    public ArrayList<Card> guessAttempted() {
        ArrayList<Card> matchedCards = new ArrayList<Card>();
        if (numberOfGuesses < Constants.MAX_GUESSES) {
            numberOfGuesses++;
            notifyAttemptObservers();

            matchedCards = isDealerBuying(selectedCards);
        }

        return matchedCards;

    }
}