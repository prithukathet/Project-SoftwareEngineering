package com.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Grade35Window {
    // Array to store selected cards
    private String[] selectedCards = new String[4];
    private int selectedCount = 0; // Count of selected cards
    private JTextArea outputArea; // Declare outputArea as a class member

    public Grade35Window() {
        // Create the 3-5 Main window frame
        JFrame G35Frame = new JFrame("Grade 3-5 - Card Values");
        G35Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        G35Frame.setSize(1600, 700); // Increased height to accommodate output area
        G35Frame.setLocationRelativeTo(null); // Center the window
        G35Frame.setResizable(false); // Do not allow resizing
        ImageIcon image = new ImageIcon(getClass().getResource("/com/resources/artdealer.jpg"));
        G35Frame.setIconImage(image.getImage());

        // Instruction Panel North
        JPanel instructionPanel = new JPanel(new BorderLayout());
        instructionPanel.setBackground(Color.BLACK); // Set background color to black
        JLabel instructionLabel = new JLabel(
                "Art Dealer wants you to choose 4 paintings below to guess the pattern he is buying");
        instructionLabel.setForeground(Color.GREEN); // Set text color to green
        instructionLabel.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference
        instructionPanel.add(instructionLabel); // Add label to instruction panel

        // Create the button
        JButton chooseLevelButton = new JButton("Choose a Different Level");
        chooseLevelButton.setBackground(Color.BLACK); // Set background color to black
        chooseLevelButton.setForeground(Color.GREEN); // Set text color to green
        chooseLevelButton.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference

        // Add the button to the instruction panel (far right) with event listener
        instructionPanel.add(chooseLevelButton, BorderLayout.EAST);
        chooseLevelButton.addActionListener(e -> {
            System.out.println("Choose Level Button Clicked"); // Debugging line
            G35Frame.dispose(); // Close the current window
            new GradeChooserGUI(); // Open the GradeChooserGUI
        });

        // Main Panel with grid layout
        JPanel mainPanel = new JPanel(new GridLayout(4, 13, 10, 10)); // 4 rows, 13 columns
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Set a neon green border with thickness 2

        // Bottom Panel
        JPanel bottomPanel = new JPanel(); // Panel for output messages
        bottomPanel.setPreferredSize(new Dimension(0, 120)); // Set height of the bottom panel to 100 pixels
        bottomPanel.setBackground(Color.BLACK);

        // Card suits and values
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "Ace", "King", "Queen", "Jack", "10", "9", "8", "7", "6", "5", "4", "3", "2" };

        // Icons for selected states
        ImageIcon checkMarkIcon = new ImageIcon(getClass().getResource("/com/resources/checkMarkIcon.png"));

        // Desired size for card images
        int cardWidth = 85; // Adjust this value as needed
        int cardHeight = 120; // Adjust this value as needed

        // Resize the checkmark icon to match the card dimensions
        Image resizedCheckMarkImage = checkMarkIcon.getImage().getScaledInstance(cardWidth, cardHeight,
                Image.SCALE_SMOOTH);
        checkMarkIcon = new ImageIcon(resizedCheckMarkImage);

        // Add buttons for each card image
        for (String suit : suits) {
            for (String value : values) {
                // Construct the card file name
                String cardFileName = value.toLowerCase() + "_of_" + suit.toLowerCase() + ".png";

                // Load the image from the resources
                ImageIcon cardIcon = new ImageIcon(
                        getClass().getResource("/com/resources/playingCards/" + cardFileName));

                // Resize the image
                Image resizedImage = resizeImage(cardIcon.getImage(), cardWidth, cardHeight);
                cardIcon = new ImageIcon(resizedImage);

                // Create a JLayeredPane for stacking card and checkmark
                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setPreferredSize(new Dimension(cardWidth, cardHeight));

                // Create and add a JLabel for the card image
                JLabel cardLabel = new JLabel(cardIcon);
                cardLabel.setBounds(0, 0, cardWidth, cardHeight);
                layeredPane.add(cardLabel, Integer.valueOf(0)); // Bottom layer for card image

                // Create and add a JLabel for the checkmark icon
                JLabel checkMarkLabel = new JLabel(checkMarkIcon);
                checkMarkLabel.setBounds(0, 0, cardWidth, cardHeight);
                checkMarkLabel.setVisible(false); // Initially hidden
                layeredPane.add(checkMarkLabel, Integer.valueOf(1)); // Top layer for checkmark over card

                // Add action listener for button clicks
                layeredPane.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        handleCardSelection(layeredPane, checkMarkLabel, value, suit);
                    }
                });

                mainPanel.add(layeredPane); // Add layered pane to the grid
            }
        }

        // Create a JTextArea for output messages
        outputArea = new JTextArea(3, 55); // Create outputArea
        outputArea.setEditable(false); // Make it non-editable
        outputArea.setBackground(Color.BLACK); // Set background color black
        outputArea.setForeground(Color.GREEN); // Set text color green
        outputArea.setFont(new Font("Comic Sans", Font.PLAIN, 14)); // Set font preference
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Neon green border
        outputArea.setLineWrap(true); // Enable line to wrap

        // Add the JTextArea to the bottom panel
        bottomPanel.add(outputArea); // Add output area to bottom panel

        // Add panels to the frame
        G35Frame.add(instructionPanel, BorderLayout.NORTH); // Add instruction panel at the top
        G35Frame.add(mainPanel, BorderLayout.CENTER); // Add main panel in the center
        G35Frame.add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel at the bottom

        // Makes the frame visible
        G35Frame.setVisible(true); // Make the 3-5 window visible
    }

    // Method to resize the image
    private Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
        Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return resizedImage;
    }

    // Handle card selection
    private void handleCardSelection(JLayeredPane layeredPane, JLabel checkMarkLabel, String value, String suit) {
        if (checkMarkLabel.isVisible()) {
            // Deselecting the card
            selectedCount--; // Decrease selected count upon unselection
            selectedCards[findCardIndex(value, suit)] = null; // Deselect the card
            checkMarkLabel.setVisible(false); // Hide checkmark
            updateOutputArea(); // Update the output area at the bottom
        } else {
            // If the button is not selected
            if (selectedCount < 4) { // Check if less than 4 cards are selected
                selectedCards[selectedCount] = value + " of " + suit; // Format selected card to display correctly
                selectedCount++; // Increase count
                checkMarkLabel.setVisible(true); // Show checkmark
                updateOutputArea(); // Update the output area at the bottom
                checkSelectedCards(); // Check if the maximum number of cards is selected
            } else {
                JOptionPane.showMessageDialog(null, "You can only select 4 cards!"); // Error message
            }
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

    // Method to update the output area with selected cards
    private void updateOutputArea() {
        StringBuilder output = new StringBuilder("Selected Cards:\n");
        for (String card : selectedCards) {
            if (card != null) {
                output.append(card).append(", ");
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
                System.out.println("User confirmed their selection. Proceeding...");
                // You can call the logic to check for matches or the pattern here
            } else {
                // User chose "No", allow them to continue selecting/changing cards
                System.out.println("User wants to change their selection.");
            }
        }
    }
}
