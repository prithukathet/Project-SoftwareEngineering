package com.artdealergame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Card {
    private CardSuit suit;
    private CardValue value;
    private CardColor color;
    private boolean selected = false;

    private List<CardObserver> observers = new ArrayList<>();

    ImageIcon image;

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
        if (suit == CardSuit.HEARTS || suit == CardSuit.DIAMONDS) {
            this.color = CardColor.RED;
        } else {
            this.color = CardColor.BLACK;
        }

        // Construct the card file name
        String cardFileName = value.name().toLowerCase() + "_of_" + suit.name().toLowerCase() + ".png";

        // Load the image from the resources
        image = new ImageIcon(getClass().getResource("/resources/playingCards/" + cardFileName));

        // Resize the image
        image = new ImageIcon(
                image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT, Image.SCALE_SMOOTH));
    }

    public void addObserver(CardObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CardObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CardObserver observer : observers) {
            observer.update(this);
        }
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }

    public void selected() {
        this.selected = !this.selected;
        notifyObservers();
    }

    public void select() {
        this.selected = true;
        notifyObservers();
    }

    public void deselect() {
        this.selected = false;
        notifyObservers();
    }

    public boolean isSelected() {
        return selected;
    }

    public ImageIcon getImage() {
        return image;
    }
}
