package com.gui;

import com.artdealergame.ArtDealerGame;
import com.artdealergame.ArtDealerGame3_5;
import com.artdealergame.ArtDealerGame6_8;
import com.artdealergame.ArtDealerGameK_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ArtDealerGameGUI extends JFrame {
    private JTextArea cardInputArea;
    private JLabel resultLabel;
    private JButton guessButton;
    private JComboBox<String> levelSelection;
    private ArtDealerGame game;

    public ArtDealerGameGUI() {
        setTitle("Art Dealer Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instructions label
        JLabel instructionsLabel = new JLabel("<html>Select a grade level and enter 4 cards.<br>"
                + "Use the format 'Rank of Suit', separated by commas.<br>"
                + "Example: '2 of Hearts, 3 of Diamonds, 5 of Clubs, 7 of Spades'.<br>"
                + "Guess the pattern the art dealer is buying!</html>");

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

        // Initialize the game to a default level
        game = new ArtDealerGameK_2();  // Default to "K-2"

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
            if (game == null) {
                resultLabel.setText("Please select a level first.");
                return;
            }
            String[] cards = cardInputArea.getText().trim().split("\\s*,\\s*"); // Handle spaces
            if (cards.length != 4) {
                resultLabel.setText("Please enter exactly 4 cards.");
                return;
            }
            // Debug: Print the dealer's pattern
            System.out.println("Dealer's pattern: " + game.getDealerPattern());

            // Debug: Print the cards entered
            System.out.println("Cards entered: " + Arrays.toString(cards));

            boolean result = game.isDealerBuying(cards);
            System.out.println("Dealer buying? " + result);

            resultLabel.setText(result ? "Correct!" : "Try Again!");
        }
    }



    public static void main(String[] args) {
        new ArtDealerGameGUI();
    }
}
