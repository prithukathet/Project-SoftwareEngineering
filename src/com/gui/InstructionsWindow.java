package com.gui;

import javax.swing.*;
import java.awt.*;

public class InstructionsWindow {

    public InstructionsWindow() {
        // Create a new frame for the instructions
        JFrame frame = new JFrame("Instructions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Create a JTextPane and set its content type to HTML
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(
                "<html>"
                        + "<head>"
                        + "<style>"
                        + "body { color: white; font-family: serif; width: 550px; }" // Set a width for wrapping
                        + "</style>"
                        + "</head>"
                        + "<body>"
                        + "<h2>Introduction</h2>"
                        + "<p>"
                        + "Welcome to the Art Dealer Game, a thrilling card game where you compete against the computer, known as the \"Art Dealer,\" in a battle to guess the pattern the dealer is buying. Your mission is to guess their pattern by selecting four cards from a deck of 52 cards."
                        + "</p>"
                        + "<br>"
                        + "<p>"
                        + "You will have 3 choices to choose 4 cards. If one of the 4 cards chosen is purchased by the dealer, it will show you in a popup box. If all 4 cards are purchased by the dealer, you will be given the option to guess the pattern. If you guess the pattern correctly, you win the game. If you do not guess the pattern correctly, you will have the option to continue guessing until your 3 turns run out."
                        + "</p>"
                        + "<br>"
                        + "<h3>Gameplay Instructions:</h3>"
                        + "<ol>"
                        + "<li>"
                        + "<strong>Selection Process:</strong>"
                        + "<ul>"
                        + "<li>You will pick four cards, one by one.</li>"
                        + "<li>Each time you make a selection, a checkmark will appear over your card and you will see it in the output area on the bottom.</li>"
                        + "<li>If you try to pick a card that has already been selected, the checkmark will disappear and the card will be deselected.</li>"
                        + "<li>Once you choose 4 cards, you will be prompted if you are sure about these 4 cards. You can choose yes and continue or no to reselect.</li>"
                        + "</ul>"
                        + "</li>"
                        + "<li>"
                        + "<strong>After 4 Card Selections:</strong>"
                        + "<ul>"
                        + "<li>After you pick four cards, the Art Dealer will either purchase cards based on a random pattern, or tell you no cards were purchased.</li>"
                        + "<li>If a card is purchased, it will tell you which card was purchased.</li>"
                        + "<li>You will have 3 chances to guess the dealer's pattern.</li>"
                        + "<li>If you guess the dealer's pattern early, you have a choice to choose the pattern; be careful to ensure you are 100% right.</li>"
                        + "</ul>"
                        + "</li>"
                        + "<li>"
                        + "<strong>Winning the Game:</strong>"
                        + "<ul>"
                        + "<li>Select the right pattern identified by the Art Dealer.</li>"
                        + "<li>If you guess 4 cards correctly, but guess the pattern incorrectly, you lose!</li>"
                        + "<li>You can return to the menu at any time.</li>"
                        + "</ul>"
                        + "</li>"
                        + "</ol>"
                        + "<p>Enjoy playing \"The Art Dealer\" game!</p>"
                        + "</body>"
                        + "</html>"
        );

        // Make the text pane non-editable
        textPane.setEditable(false);

        // Set the background color of the text pane
        textPane.setBackground(Color.BLACK);

        // Create a scroll pane to hold the text pane
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create the button
        JButton backToMenu = new JButton("Back To Menu");
        backToMenu.setBackground(Color.BLACK); // Set background color to black
        backToMenu.setForeground(Color.GREEN); // Set text color to green
        backToMenu.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference

        // Add the button to the instruction panel (far right) with event listener
        backToMenu.addActionListener(e -> {
            frame.dispose(); // Close this window
            GradeChooserGUI.main(new String[0]); // Re-open the GradeChooserGUI
        });

        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(backToMenu, BorderLayout.SOUTH);

        // Pack the frame to auto-size based on components
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Set a minimum size for the frame (optional)
        frame.setMinimumSize(new Dimension(600, 400));

        // Make the frame visible
        frame.setVisible(true);
    }
}
