package com.artdealergame;

import java.util.ArrayList;
import javax.swing.JLabel;

public class AttemptObserver implements GameObserver {
    private JLabel label;

    public AttemptObserver(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(int numberOfAttempts) {
        updateGuessStatusLabel(numberOfAttempts);
    }

    @Override
    public void update(ArrayList<Card> selectedCards) {
        // Do nothing
    }

    private void updateGuessStatusLabel(int numberOfAttempts) {
        this.label.setText("<html>Attempts: " + numberOfAttempts + "<br>Remaining: "
                + (Constants.MAX_GUESSES - numberOfAttempts) + "</html>");
        this.label.revalidate(); // Revalidate to ensure the label is updated
        this.label.repaint(); // Repaint to reflect the changes
    }
}
