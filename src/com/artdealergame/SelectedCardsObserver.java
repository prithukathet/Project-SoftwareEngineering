package com.artdealergame;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class SelectedCardsObserver implements GameObserver {
    private JTextArea outputArea;

    public SelectedCardsObserver(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    @Override
    public void update(int numberOfAttempts) {
        // Do nothing
    }

    @Override
    public void update(ArrayList<Card> selectedCards) {
        updateOutputArea(selectedCards);
    }

    private void updateOutputArea(ArrayList<Card> selectedCards) {
        StringBuilder output = new StringBuilder("Selected Cards:\n");
        for (Card card : selectedCards) {
            output.append(card.getFullCardName()).append(", ");
        }

        // Remove the last comma and space if there are any selected cards
        if (!selectedCards.isEmpty()) {
            output.setLength(output.length() - 2);
        }

        outputArea.setText(output.toString()); // Update the output area
    }
}
