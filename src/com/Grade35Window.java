package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grade35Window {
    // Array to store selected cards
    private String[] selectedCards = new String[4];
    private int selectedCount = 0; // Count of selected cards
    private JTextArea outputArea; // Declare outputArea as a class member

    public Grade35Window() {
        // Create the K-2 window frame
        JFrame G35Frame = new JFrame("Grade 3-5 - Card Values");

        G35Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        G35Frame.setSize(1300, 500); // Increased height to accommodate output area
        G35Frame.setLocationRelativeTo(null); // Center the window
        G35Frame.setResizable(false); // Do not allow people resize this screen

        ImageIcon image = new ImageIcon(getClass().getResource("/com/resources/artdealer.jpg"));
        G35Frame.setIconImage(image.getImage());

        // Create a main panel with a grid layout for headers and buttons
        JPanel mainPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 4 rows, 4 columns
        JPanel topPanel = new JPanel(); // Panel for the "Choose 4 Cards" text
        JPanel bottomPanel = new JPanel(); // Panel for output messages

        // Label for instruction
        JLabel instructionLabel = new JLabel("Choose 4 Cards To Guess The Art Dealers Pattern", JLabel.CENTER);
        instructionLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        topPanel.add(instructionLabel); // Add instruction label to top panel

        // Card suits and values
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "Ace", "King", "Queen", "Jack", "10", "9", "8", "7", "6", "5", "4", "3", "2" };

        // Icons for selected and unselected states
        ImageIcon checkMarkIcon = new ImageIcon(getClass().getResource("/com/resources/checkMarkIcon.png")); // allows
                                                                                                             // me to
                                                                                                             // pull the
                                                                                                             // image
                                                                                                             // when
                                                                                                             // card is
                                                                                                             // selected
        ImageIcon emptyIcon = new ImageIcon("emptyIcon.png");

        // Scale icons to fit button size
        checkMarkIcon = new ImageIcon(checkMarkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        emptyIcon = new ImageIcon(emptyIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        // Add headers and buttons to the main panel
        for (int i = 0; i < suits.length; i++) {
            // Create and add header label
            JLabel header = new JLabel(suits[i], JLabel.CENTER);
            header.setFont(new Font("Comic Sans", Font.BOLD, 16));
            mainPanel.add(header); // Add header to the grid

            // Add buttons for card values next to the header
            for (String value : values) {
                JButton button = new JButton(value); // declare button
                button.setPreferredSize(new Dimension(50, 30)); // set image size
                button.setFocusable(false); // when button is selected, it removes the outline around the text
                button.setBackground(Color.black); // set button background to black
                button.setForeground(Color.GREEN); // set button text color to green
                button.setFont(new Font("Comic Sans", Font.PLAIN, 12)); // Smaller font for smaller buttons
                button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Neon green border
                button.setIcon(emptyIcon); // Set the default icon to empty

                // Add action listener for button clicks
                final String suit = suits[i]; // Use a final local variable for the suit
                button.addActionListener(new CardButtonActionListener(button, value, suit, checkMarkIcon, emptyIcon));

                mainPanel.add(button); // Add button to the grid
            }
        }

        // Create a JTextArea for output messages
        outputArea = new JTextArea(3, 40); // create outputArea
        outputArea.setEditable(false); // make it non-editable so user sees what cards are selected
        outputArea.setBackground(Color.BLACK); // sets background color black
        outputArea.setForeground(Color.GREEN); // sets background color green
        outputArea.setFont(new Font("Comic Sans", Font.PLAIN, 14)); // sets preference of font
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Neon green border
        outputArea.setLineWrap(true); // enables line to wrap

        // Add panels to the frame
        G35Frame.add(topPanel, BorderLayout.NORTH); // Add top panel at the top
        G35Frame.add(mainPanel, BorderLayout.CENTER); // Add main panel in the center
        bottomPanel.add(outputArea); // Add output area to bottom panel
        G35Frame.add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel at the bottom

        // makes the frame visable
        G35Frame.setVisible(true); // Make the 3-5 window visible
    }

    // Inner class for handling button actions upon them being clicked or unclick
    private class CardButtonActionListener implements ActionListener {
        private final JButton button;
        private final String value;
        private final String suit;
        private final ImageIcon checkMarkIcon;
        private final ImageIcon emptyIcon;

        public CardButtonActionListener(JButton button, String value, String suit, ImageIcon checkMarkIcon,
                ImageIcon emptyIcon) {
            this.button = button;
            this.value = value;
            this.suit = suit;
            this.checkMarkIcon = checkMarkIcon;
            this.emptyIcon = emptyIcon;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getIcon() == emptyIcon) { // if the button is not selected
                if (selectedCount < 4) { // check if less than 4 cards are selected
                    selectedCards[selectedCount] = value + " of " + suit; // format selected card to dislay correctly
                    selectedCount++; // increase count to ensure we do not go over 4 cards selected
                    button.setIcon(checkMarkIcon); // set the button icon to the checkmark upon click
                    System.out.println("Selected Card: " + selectedCards[selectedCount - 1]); // print selected card to
                                                                                              // box at the bottom
                    updateOutputArea(); // update the output area at the bottom
                    checkSelectedCards(); // check if the maximum number of cards is selected
                } else {
                    JOptionPane.showMessageDialog(null, "You can only select 4 cards!"); // error message pops if more
                                                                                         // than 4 selected
                }
            } else { // if the button is already selected
                selectedCount--; // decrease selected count upon unselection
                selectedCards[findCardIndex(value, suit)] = null; // deselects the card
                button.setIcon(emptyIcon); // reset the button icon to empty
                updateOutputArea(); // update the output area at the bottom
            }
        }

        // Method to find the index of the deselected card
        private int findCardIndex(String value, String suit) {
            for (int i = 0; i < selectedCards.length; i++) {
                if (selectedCards[i] != null && selectedCards[i].equals(value + " of " + suit)) {
                    return i;
                }
            }
            return -1; // Return -1 if not found
        }
    }

    // Method to update the output area with selected cards
    private void updateOutputArea() {
        StringBuilder output = new StringBuilder("Selected Cards:\n");
        for (String card : selectedCards) {
            if (card != null) {
                output.append(card).append("\n");
            }
        }
        outputArea.setText(output.toString()); // Set text of output area
    }

    // Method to check if 4 cards are selected and confirm with the user
    private void checkSelectedCards() {
        if (selectedCount == 4) {
            // Build a message to display the selected cards
            StringBuilder selectedMessage = new StringBuilder("Are you sure these are the four cards?\n");
            for (String card : selectedCards) {
                if (card != null) {
                    selectedMessage.append(card).append("\n");
                }
            }

            // Display a confirmation dialog asking if the user is sure
            int response = JOptionPane.showConfirmDialog(null, selectedMessage.toString(), "Confirm Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Handle the user's response
            if (response == JOptionPane.YES_OPTION) {
                // User confirmed their selection, proceed with the game logic
                // TO DO: Add logic to check matches or allow guesses
                System.out.println("User confirmed their selection. Proceeding...");
                // You can call the logic to check for matches or the pattern here
            } else {
                // User chose "No", allow them to continue selecting/changing cards
                System.out.println("User wants to change their selection.");
                // You could clear the selected cards if necessary or allow changes
            }
        }
    }

}
