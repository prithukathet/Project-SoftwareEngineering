package com.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

import com.artdealergame.ArtDealerGame35;

public class Grade35Window {
    protected static final String True = null;
    // Array to store selected cards
    private String[] selectedCards = new String[4];
    private int selectedCount = 0; // Count of selected cards
    private JTextArea outputArea; // Declare outputArea as a class member
    private int attemptCount = 0; // Count of attempts made by the user
    private JComboBox<String> patternGuessComboBox; // Add this declaration
    private JPanel mainPanel; // Declare mainPanel as a class member
    private ImageIcon checkMarkIcon; // Declare checkMarkIcon as a class member
    private ArtDealerGame35 game; // Declare game as a class member
    private JLabel guessStatusLabel; // Declare the JLabel to display guess status

    public Grade35Window() {
        // Initialize the game instance
        game = new ArtDealerGame35();
        // Create the 3-5 Main window frame
        JFrame G35Frame = new JFrame("Grade 3-5 - Card Values");
        G35Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        G35Frame.setSize(1600, 700); // Increased height to accommodate output area
        G35Frame.setLocationRelativeTo(null); // Center the window
        G35Frame.setResizable(false); // Do not allow resizing
        ImageIcon image = new ImageIcon(getClass().getResource("/resources/artdealer.jpg"));
        G35Frame.setIconImage(image.getImage());

        patternGuessComboBox = new JComboBox<>(game.getPatterns()); // Example initialization

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
        chooseLevelButton.setFocusable(false);

        // Initialize the guess status label
        guessStatusLabel = new JLabel("<html>Attempts: 0<br>Remaining: 3</html>"); // Initial message
        guessStatusLabel.setForeground(Color.GREEN); // Set text color to green
        guessStatusLabel.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference

        // Add the button to the instruction panel (far right) with event listener
        instructionPanel.add(chooseLevelButton, BorderLayout.EAST);
        chooseLevelButton.addActionListener(e -> {
            G35Frame.dispose(); // Close this window
            GradeChooserGUI.main(new String[0]); // Re-open the GradeChooserGUI
        });

        // Main Panel with grid layout
        mainPanel = new JPanel(new GridLayout(4, 13, 10, 10)); // 4 rows, 13 columns
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Set a neon green border with thickness 2

        // Bottom Panel
        JPanel bottomPanel = new JPanel(); // Panel for output messages
        bottomPanel.setPreferredSize(new Dimension(0, 120)); // Set height of the bottom panel to 100 pixels
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(guessStatusLabel); // Add the label to the bottom panel

        // Card suits and values
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "Ace", "King", "Queen", "Jack", "10", "9", "8", "7", "6", "5", "4", "3", "2" };

        // Icons for selected states
        checkMarkIcon = new ImageIcon(getClass().getResource("/resources/checkMarkIcon.png"));

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
                ImageIcon cardIcon = new ImageIcon(getClass().getResource("/resources/playingCards/" + cardFileName));

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

    private void updateGuessStatusLabel() {
        int remainingAttempts = 3 - attemptCount; // Assuming maximum attempts are 3
        guessStatusLabel.setText("<html>Attempts: " + attemptCount + "<br>Remaining: " + remainingAttempts + "</html>");
        guessStatusLabel.revalidate(); // Revalidate to ensure the label is updated
        guessStatusLabel.repaint(); // Repaint to reflect the changes
    }

    // Handle card selection
    private void handleCardSelection(JLayeredPane layeredPane, JLabel checkMarkLabel, String value, String suit) {
        if (checkMarkLabel.isVisible()) {
            // Deselecting the card
            selectedCount--; // Decrease selected count upon unselection
            eraseSelectedCard(value, suit);
            checkMarkLabel.setVisible(false); // Hide checkmark
            updateOutputArea(); // Update the output area at the bottom
        } else if (selectedCount < 4) { // Check if less than 4 cards are selected
            selectedCards[selectedCount] = value + " of " + suit; // Format selected card to display correctly
            selectedCount++; // Increase count
            checkMarkLabel.setVisible(true); // Show checkmark
            updateOutputArea(); // Update the output area at the bottom
        } else {
            JOptionPane.showMessageDialog(null, "You can only select 4 cards!"); // Error message
            return;
        }

        if (selectedCount == 4) {
            checkSelectedCards(); // Check if the maximum number of cards is selected
        }
    }

    private void eraseSelectedCard(String value, String suit) {
        String[] newSelectedCards = new String[4];
        for (int i = 0; i < selectedCards.length; i++) {
            if (selectedCards[i] != null && !selectedCards[i].equals(value + " of " + suit)) {
                newSelectedCards[i] = selectedCards[i];
            }
        }
    }

    // Method to update the output area with selected cards
    private void updateOutputArea() {
        StringBuilder output = new StringBuilder("Selected Cards:\n");
        for (String card : selectedCards) {
            if (card != null) {
                output.append(card).append(", ");
            }
        }

        // Remove the last comma and space if there are any selected cards
        if (selectedCount > 0) {
            output.setLength(output.length() - 2);
        }
        outputArea.setText(output.toString()); // Update the output area
    }

    // Method to reset all selections and remove checkmarks
    private void resetSelections() {
        selectedCount = 0; // Reset selected count
        Arrays.fill(selectedCards, null); // Clear selected cards

        // Loop through all components in the main panel to hide checkmarks
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JLayeredPane) {
                JLayeredPane layeredPane = (JLayeredPane) component;
                for (Component layer : layeredPane.getComponents()) {
                    if (layer instanceof JLabel) {
                        JLabel label = (JLabel) layer;
                        // Check if it is the checkmark label and hide it
                        if (label.getIcon() == checkMarkIcon) {
                            label.setVisible(false); // Hide checkmark
                        }
                    }
                }
            }
        }
        updateOutputArea(); // Clear output area
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
                // Increment the attempt count
                attemptCount++;

                // User confirmed their selection, proceed with the game logic
                System.out.println("User confirmed their selection. Proceeding...");

                // Create an instance of ArtDealerGame35 and check matching cards
                int numMatches = 0;
                StringBuilder matched = new StringBuilder();
                matched.append("Cards Purchased by the Dealer:\n");
                for (String card : selectedCards) {
                    if (game.checkCardMatch(card)) {
                        numMatches++;
                        matched.append(card).append("\n");
                    }
                }

                // Show the result in a message dialog
                JOptionPane.showMessageDialog(null, matched, "Matching Cards Result", JOptionPane.INFORMATION_MESSAGE);

                if (numMatches == 4) {
                    showPatternSelectionOptions(guessedPattern -> {
                        // Compare guessed pattern with the dealer's pattern
                        if (guessedPattern.equals(game.getDealerPattern())) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You guessed the pattern correctly!",
                                    "Pattern Guess", JOptionPane.INFORMATION_MESSAGE);

                            // Play cheer sound
                            // new SoundPlayer().playCheerSound();

                            JFrame balloonFrame = new JFrame("Celebration!");
                            balloonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            balloonFrame.setSize(500, 500);

                            // Create the BalloonPanel and set its background to black
                            BalloonPanel balloonPanel = new BalloonPanel();
                            balloonPanel.setBackground(Color.BLACK); // Set the background of the panel to black
                            balloonPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5)); // Neon green border

                            balloonFrame.add(balloonPanel);

                            // Play cheer sound with looping
                            SoundPlayer soundPlayer = new SoundPlayer();
                            soundPlayer.playCheerSound(); // Start playing and looping the sound

                            // Add a listener to stop the sound when the window is closed
                            balloonFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                                @Override
                                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                    soundPlayer.stopCheerSound(); // Stop the sound when window closes
                                }
                            });

                            // Show the balloon frame for the celebration
                            balloonFrame.setVisible(true);

                            // Reset the game for the next round
                            // game.resetGame();

                            // Ask the user if they want to play again or quit
                            int playAgainResponse = JOptionPane.showConfirmDialog(null,
                                    "The game has been reset. Do you want to play again?", "Play Again?",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if (playAgainResponse == JOptionPane.YES_OPTION) {
                                // User chose to play again, reset the game again or start a new round
                                game.resetGame(); // Reset the game state again
                                System.out.println("DEBUG: User chose to play again.");
                                // TODO: Fully exit k2 game and go back to main window
                            } else {
                                // User chose not to play again, quit the application
                                System.out.println("DEBUG: User chose to quit.");
                                System.exit(0); // Quit the application
                            }

                        } else {
                            if (attemptCount >= 3) {
                                // They lost the game
                                JOptionPane.showMessageDialog(null,
                                        "Sorry! Your guess was incorrect. The pattern was: " + game.getDealerPattern(),
                                        "Pattern Guess", JOptionPane.ERROR_MESSAGE);
                                // TODO: Exit back to main menu of game
                            }
                        }
                    });
                }
                // Update guess status label
                updateGuessStatusLabel();

                // Check if the user has reached the maximum attempts
                if (attemptCount >= 3) {
                    // Give user the option to guess the pattern
                    // TO DO: Fix, it doesn't show up
                    showPatternSelectionOptions(guessedPattern -> {
                        // Compare guessed pattern with the dealer's pattern
                        if (guessedPattern.equals(game.getDealerPattern())) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You guessed the pattern correctly!",
                                    "Pattern Guess", JOptionPane.INFORMATION_MESSAGE);

                            // Play cheer sound
                            // new SoundPlayer().playCheerSound();

                            JFrame balloonFrame = new JFrame("Celebration!");
                            balloonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            balloonFrame.setSize(500, 500);

                            // Create the BalloonPanel and set its background to black
                            BalloonPanel balloonPanel = new BalloonPanel();
                            balloonPanel.setBackground(Color.BLACK); // Set the background of the panel to black
                            balloonPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5)); // Neon green border

                            balloonFrame.add(balloonPanel);

                            // Play cheer sound with looping
                            SoundPlayer soundPlayer = new SoundPlayer();
                            soundPlayer.playCheerSound(); // Start playing and looping the sound

                            // Add a listener to stop the sound when the window is closed
                            balloonFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                                @Override
                                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                    soundPlayer.stopCheerSound(); // Stop the sound when window closes
                                }
                            });

                            // Show the balloon frame for the celebration
                            balloonFrame.setVisible(true);

                            // Reset the game for the next round
                            // game.resetGame();

                            // Ask the user if they want to play again or quit
                            int playAgainResponse = JOptionPane.showConfirmDialog(null,
                                    "The game has been reset. Do you want to play again?", "Play Again?",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if (playAgainResponse == JOptionPane.YES_OPTION) {
                                // User chose to play again, reset the game again or start a new round
                                game.resetGame(); // Reset the game state again
                                System.out.println("DEBUG: User chose to play again.");
                            } else {
                                // User chose not to play again, quit the application
                                System.out.println("DEBUG: User chose to quit.");
                                System.exit(0); // Quit the application
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry! Your guess was incorrect. The pattern was: " + game.getDealerPattern(),
                                    "Pattern Guess", JOptionPane.ERROR_MESSAGE);
                            // TODO: Game over reset back to main menu
                        }

                        // Reset for the next round if the guess was correct or incorrect
                        attemptCount = 0; // Reset attempts for the next round
                        updateOutputArea(); // Clear output area
                    });
                }

                // // Reset selections if the guess was incorrect
                resetSelections(); // Reset all selections
            } else {
                // User chose "No", allow them to continue selecting/changing cards
                System.out.println("User wants to change their selection.");
                resetSelections(); // Reset all selections

            }
        }
    }

    public void showPatternSelectionOptions(java.util.function.Consumer<String> callback) {
        // Step 1: Create a new JFrame (the window)
        JFrame frame = new JFrame("JComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Set the size of the window

        // Step 2: Set a layout and add the JComboBox to the frame
        frame.setLayout(new java.awt.FlowLayout()); // Simple layout for the example
        frame.add(patternGuessComboBox); // Add the comboBox to the frame

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.BLACK); // Set background color to black
        submitButton.setForeground(Color.GREEN); // Set text color to green
        submitButton.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference

        submitButton.addActionListener(e -> {
            String aguessedPattern = (String) patternGuessComboBox.getSelectedItem();
            frame.dispose(); // Close this window
            callback.accept(aguessedPattern); // Call the callback with the guessed pattern
        });

        frame.add(submitButton); // Add the submit button to the frame

        // Step 3: Make the frame visible
        frame.setVisible(true);
    }
}
