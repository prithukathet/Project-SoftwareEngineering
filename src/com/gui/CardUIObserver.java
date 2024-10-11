package com.gui;

import javax.swing.JLabel;

import com.artdealergame.CardObserver;
import com.artdealergame.Card;

public class CardUIObserver implements CardObserver {
    private JLabel checkMarkLabel;

    public CardUIObserver(JLabel checkMarkLabel) {
        this.checkMarkLabel = checkMarkLabel;
    }

    @Override
    public void update(Card card) {
        checkMarkLabel.setVisible(card.isSelected());
    }
}
