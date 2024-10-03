package com.gui;

import com.artdealergame.ArtDealerGame3_5;
import com.artdealergame.ArtDealerGame6_8;
import com.artdealergame.ArtDealerGameK_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtDealerGameGUI extends JFrame {
    private JTextArea cardInputArea;
    private JLabel resultLabel;
    private JButton guessButton;
    private JComboBox<String> levelSelection;
    private Object game;

    public ArtDealerGameGUI() {
        setTitle("Art Dealer Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instructions label
        JLabel instructionsLabel = new JLabel("<html>Select a grade level and enter 4 cards.<br> Guess the pattern the art dealer is buying!</html>");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Level selection dropdown
        String[] levels = {"K-2", "3-5", "6-8"};
        levelSelection = new JComboBox<>(levels);
        levelSelection.addActionListener(new LevelSelectionListener());

        // Text area for card input
        cardInputArea = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(cardInputArea);

        // Guess button
        guessButton = new JButton("Submit Guess");
        guessButton.addActionListener(new GuessButtonListener());

        // Result label
        resultLabel = new JLabel("Good luck!", SwingConstants.CENTER);

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(instructionsLabel, BorderLayout.NORTH);
        panel.add(levelSelection, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);
        panel.add(guessButton, BorderLayout.EAST);
        panel.add(resultLabel, BorderLayout.WEST);

        add(panel);
        setVisible(true);
    }

    private class LevelSelectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedLevel = (String) levelSelection.getSelectedItem();
            switch (selectedLevel) {
                case "K-2":
                    game = new ArtDealerGameK_2();
                    break;
                case "3-5":
                    game = new ArtDealerGame3_5();
                    break;
                case "6-8":
                    game = new ArtDealerGame6_8();
                    break;
            }
        }
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] cards = cardInputArea.getText().split(","); // Assume cards are separated by commas
            boolean result = ((ArtDealerGameK_2) game).isDealerBuying(cards);
            resultLabel.setText(result ? "Correct!" : "Try Again!");
        }
    }


    public static void main(String[] args) {
        new ArtDealerGameGUI();
    }
}
