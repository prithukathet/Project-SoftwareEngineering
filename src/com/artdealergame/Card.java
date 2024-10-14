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
    private String fullCardName;

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

        setFullCardName();
    }

    public String getFullCardName() {
        return fullCardName;
    }

    // If you also want the full card name (e.g., 'jack of clubs'), add this:
    private void setFullCardName() {
        String valueName;

        // Check if the card is a number card 2-10 or a face card
        switch (value) {
        case TWO:
            valueName = "2";
            break;
        case THREE:
            valueName = "3";
            break;
        case FOUR:
            valueName = "4";
            break;
        case FIVE:
            valueName = "5";
            break;
        case SIX:
            valueName = "6";
            break;
        case SEVEN:
            valueName = "7";
            break;
        case EIGHT:
            valueName = "8";
            break;
        case NINE:
            valueName = "9";
            break;
        case TEN:
            valueName = "10";
            break;
        case JACK:
            valueName = "Jack";
            break;
        case QUEEN:
            valueName = "Queen";
            break;
        case KING:
            valueName = "King";
            break;
        case ACE:
            valueName = "Ace";
            break;
        default:
            valueName = value.name().charAt(0) + value.name().substring(1).toLowerCase();
            break;
        }

        // Format the full card name as "<value> of <suit>"
        String suitName = suit.name().charAt(0) + suit.name().substring(1).toLowerCase();
        fullCardName = valueName + " of " + suitName;
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
