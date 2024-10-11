package com.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.artdealergame.ArtDealerGameBase;
import com.artdealergame.ArtDealerGameFactory;
import com.artdealergame.AttemptObserver;
import com.artdealergame.GameType;
import com.artdealergame.Card;
import com.artdealergame.SelectedCardsObserver;

import java.awt.event.ActionEvent;

import com.artdealergame.Constants;

public class ArtDealerGameGui {
    private JTextArea outputArea; // Declare outputArea as a class member
    private JComboBox<String> patternGuessComboBox; // Add this declaration
    private JPanel mainPanel; // Declare mainPanel as a class member
    private ImageIcon checkMarkIcon; // Declare checkMarkIcon as a class member
    private ArtDealerGameBase game; // Declare game as a class member
    private JLabel guessStatusLabel; // Declare the JLabel to display guess status
    private SoundPlayer soundPlayer; // Declare the SoundPlayer as a class member
    private JFrame balloonFrame; // Declare the JFrame as a class member

    public ArtDealerGameGui(GameType gameType) {

        soundPlayer = new SoundPlayer(); // Initialize the SoundPlayer

        // Initialize the game instance
        game = ArtDealerGameFactory.createGame(gameType);
        // Create the K-2 Main window frame
        String windowName = "";
        switch (gameType) {
        case GAMEK2:
            windowName = "Grade K-2 - Card Values";
            break;
        case GAME35:
            windowName = "Grade 3-5 - Card Values";
            break;
        case GAME68:
            windowName = "Grade 6-8 - Card Values";
            break;
        }
        JFrame K2Frame = new JFrame(windowName);
        K2Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        K2Frame.setSize(1600, 700); // Increased height to accommodate output area
        K2Frame.setLocationRelativeTo(null); // Center the window
        K2Frame.setResizable(false); // Do not allow resizing
        ImageIcon image = new ImageIcon(getClass().getResource("/resources/artdealer.jpg"));
        K2Frame.setIconImage(image.getImage());

        patternGuessComboBox = new JComboBox<>(game.getPatterns()); // Example initialization

        // ----------------------North Panel------------------//
        JPanel northGamePanel = new JPanel(new BorderLayout());
        northGamePanel.setBackground(Color.BLACK); // Set background color to black
        JLabel instructionLabel = new JLabel(
                "The Art Dealer wants you to choose 4 paintings below to guess the pattern he is buying");
        instructionLabel.setForeground(Color.GREEN); // Set text color to green
        instructionLabel.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference
        northGamePanel.add(instructionLabel); // Add label to instruction panel

        // Create the button
        JButton northChooseButton = new JButton("Choose a Different Level");
        northChooseButton.setBackground(Color.BLACK); // Set background color to black
        northChooseButton.setForeground(Color.GREEN); // Set text color to green
        northChooseButton.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference
        northChooseButton.setFocusable(false);

        // Add the button to the instruction panel (far right) with event listener
        northGamePanel.add(northChooseButton, BorderLayout.EAST);
        northChooseButton.addActionListener(e -> {
            K2Frame.dispose(); // Close this window
            GradeChooserGUI.main(new String[0]); // Re-open the GradeChooserGUI
        });

        K2Frame.add(northGamePanel, BorderLayout.NORTH); // Add instruction panel at the top

        // -----------------------Main Panel---------------------//
        mainPanel = new JPanel(new GridLayout(4, 13, 10, 10)); // 4 rows, 13 columns
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Set a neon green border with thickness 2

        // Icons for selected states
        checkMarkIcon = new ImageIcon(getClass().getResource("/resources/checkMarkIcon.png"));

        // Resize the checkmark icon to match the card dimensions
        Image resizedCheckMarkImage = checkMarkIcon.getImage().getScaledInstance(Constants.CARD_WIDTH,
                Constants.CARD_HEIGHT, Image.SCALE_SMOOTH);
        checkMarkIcon = new ImageIcon(resizedCheckMarkImage);

        // Add buttons for each card image
        Card[] cards = game.getCards();
        for (Card card : cards) {
            // Create a JLayeredPane for stacking card and checkmark
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));

            // Create and add a JLabel for the card image
            JLabel cardLabel = new JLabel(card.getImage());
            cardLabel.setBounds(0, 0, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
            layeredPane.add(cardLabel, Integer.valueOf(0)); // Bottom layer for card image

            // Create and add a JLabel for the checkmark icon
            JLabel checkMarkLabel = new JLabel(checkMarkIcon);
            checkMarkLabel.setBounds(0, 0, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
            checkMarkLabel.setVisible(false); // Initially hidden

            CardUIObserver cardObserver = new CardUIObserver(checkMarkLabel);
            card.addObserver(cardObserver);

            layeredPane.add(checkMarkLabel, Integer.valueOf(1)); // Top layer for checkmark over card

            // Add action listener for button clicks
            layeredPane.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    handleCardSelection(layeredPane, checkMarkLabel, card);
                }
            });

            // Add the label to the bottom panel
            mainPanel.add(layeredPane); // Add layered pane to the grid
        }

        K2Frame.add(mainPanel, BorderLayout.CENTER); // Add main panel in the center

        // ------------------Bottom Panel--------------------//
        JPanel bottomPanel = new JPanel(new BorderLayout()); // Create a new panel for the bottom
        bottomPanel.setBackground(Color.BLACK); // Set background color to black
        bottomPanel.setPreferredSize(new Dimension(0, 80)); // Set height of the bottom panel to 200 pixels
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Neon green border

        // --------------------Bottom Left Panel------------------//
        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        guessStatusLabel = new JLabel("<html><b>Attempts:</b> 0<br>Remaining: 5</html>"); // Initial message
        guessStatusLabel.setForeground(Color.GREEN); // Set text color to green
        guessStatusLabel.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference
        bottomLeftPanel.setPreferredSize(new Dimension(160, 120)); // Set height of the bottom panel to 100 pixels
        bottomLeftPanel.setBackground(Color.BLACK);
        bottomLeftPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Neon green border
        bottomLeftPanel.add(guessStatusLabel); // Add the label to the bottom panel

        // Create the attempt observer
        AttemptObserver gu = new AttemptObserver(guessStatusLabel);
        game.addObserver(gu);

        // -------------------------Bottom Center Panel-----------------------//
        JPanel bottomCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for output messages
        bottomCenterPanel.setPreferredSize(new Dimension(0, 120)); // Set height of the bottom panel to 100 pixels
        bottomCenterPanel.setBackground(Color.BLACK);
        bottomCenterPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Neon green border

        outputArea = new JTextArea(3, 55); // Create outputArea
        outputArea.setEditable(false); // Make it non-editable
        outputArea.setBackground(Color.BLACK); // Set background color black
        outputArea.setForeground(Color.GREEN); // Set text color green
        outputArea.setFont(new Font("Comic Sans", Font.PLAIN, 14)); // Set font preference
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Neon green border
        outputArea.setLineWrap(true); // Enable line to wrap
        bottomCenterPanel.add(outputArea); // Add output area to bottom panel

        // Create the output observer
        SelectedCardsObserver outputObserver = new SelectedCardsObserver(outputArea);
        game.addObserver(outputObserver);

        // ---------------------Bottom Right Panel--------------------//
        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomRightPanel.setBackground(Color.BLACK);
        bottomRightPanel.setBorder(new LineBorder(Color.GREEN, 2)); // Neon green border
        bottomRightPanel.setPreferredSize(new Dimension(160, 120)); // Set height of the bottom panel to 100 pixels
        JLabel patternOptionsLabel = new JLabel("Patterns Available:");
        patternOptionsLabel.setForeground(Color.GREEN); // Set text color to green
        bottomRightPanel.add(patternOptionsLabel);
        bottomRightPanel.add(patternGuessComboBox); // Add the comboBox to the panel
        patternGuessComboBox.setBackground(Color.BLACK); // Set background color to black
        patternGuessComboBox.setForeground(Color.GREEN); // Set text color to white
        patternGuessComboBox.setBorder(new LineBorder(Color.GREEN, 2)); // Set neon green border
        patternGuessComboBox.setFocusable(false);

        K2Frame.add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel at the bottom
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST); // Add bottomLeftPanel to the left (WEST)
        bottomPanel.add(bottomCenterPanel, BorderLayout.CENTER); // Add bottom panel at the bottom
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST); // Add bottom panel at the bottom

        // Makes the frame visible
        K2Frame.setVisible(true); // Make the K-2 window visible
    }

    public void stopCheerSound() {
        soundPlayer.stopCheerSound(); // Call stopCheerSound on the soundPlayer instance
    }

    // Method to play the cheer sound if needed
    public void playCheerSound() {
        soundPlayer.playCheerSound(); // Call playCheerSound on the soundPlayer instance
    }

    // Handle card selection
    private void handleCardSelection(JLayeredPane layeredPane, JLabel checkMarkLabel, Card card) {
        if (!game.cardSelected(card)) {
            JOptionPane.showMessageDialog(null, "You can only select 4 cards!"); // Error
        }

        // Check if maximum cards are selected and ask the user for confirmation
        if (game.getSelectedCards().size() == Constants.MAX_SELECTED_CARDS) {

            StringBuilder selectedMessage = new StringBuilder("Are you sure these are the four cards?\n");
            for (Card selected : game.getSelectedCards()) {
                selectedMessage.append(selected.getFullCardName()).append("\n");
            }

            // Display a confirmation dialog asking if the user is sure
            int response = JOptionPane.showConfirmDialog(null, selectedMessage.toString(), "Confirm Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                ArrayList<Card> purchasedCards = game.guessAttempted();

                StringBuilder purchasedString = new StringBuilder();
                purchasedString.append("Cards Purchased by the Dealer:\n");
                for (Card purchased : purchasedCards) {
                    purchasedString.append(purchased.getFullCardName()).append("\n");
                }

                // Show the cards the art dealer has purchased
                JOptionPane.showMessageDialog(null, purchasedString, "Matching Cards Result",
                        JOptionPane.INFORMATION_MESSAGE);

                registerPatternSelectionCallback();
            } else {
                game.clearSelectedCards();
            }
        }
    }

    public void showBalloonFrame() {
        balloonFrame = new JFrame("Congratulations!");
        balloonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        balloonFrame.setSize(500, 500);
        BalloonPanel balloonPanel = new BalloonPanel();
        balloonPanel.setBackground(Color.BLACK);
        balloonPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        balloonFrame.add(balloonPanel);
        balloonFrame.setVisible(true);
        balloonFrame.setLocationRelativeTo(null);
   
        // Schedule the window to close after 5 seconds
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (balloonFrame != null) {
                    balloonFrame.dispose(); // Close the window
                }
            }
        });
        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start();
    }

    public void askPlayAgain(ArtDealerGameBase game) {
        int playAgainResponse = JOptionPane.showConfirmDialog(null,
                "The game has been reset. Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (playAgainResponse == JOptionPane.YES_OPTION) {
            // Ensure balloonFrame is not null before disposing it
            if (balloonFrame != null) {
                balloonFrame.dispose();
            }

            // Stop the cheer sound if it's playing
            stopCheerSound();

            // Reset the game state for another round
            game.resetGame();
            System.out.println("Game has been reset. Ready to play again.");

        } else {
            // If user chooses 'No', exit the game
            System.out.println("DEBUG: User chose to quit.");
            System.exit(0);
        }
    }

    // Method to show pattern selection options upon wanting to guess
    public void showPatternSelectionOptions(java.util.function.Consumer<String> callback) {
        // Step 1: Create a new JFrame (the window)
        JFrame frame = new JFrame("Choose the Art Dealers pattern!"); // Create a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Set the size of the window

        frame.getContentPane().setBackground(Color.BLACK); // Set background color to black
        frame.getContentPane().setForeground(Color.GREEN); // Set text color to green
        patternGuessComboBox.setBackground(Color.BLACK); // Set background color to black
        patternGuessComboBox.setForeground(Color.GREEN); // Set text color to green
        patternGuessComboBox.setFocusable(false); // Disable focus

        // Step 2: Set a layout and add the JComboBox to the frame
        frame.setLayout(new java.awt.FlowLayout()); // Simple layout for the example
        frame.add(patternGuessComboBox); // Add the comboBox to the frame

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.green); // Set background color to black
        submitButton.setForeground(Color.black); // Set text color to green
        submitButton.setFont(new Font("Comic Sans", Font.BOLD, 16)); // Set font preference
        submitButton.setFocusable(false); // Disable focus

        submitButton.addActionListener(e -> {
            String aguessedPattern = (String) patternGuessComboBox.getSelectedItem();
            frame.dispose(); // Close this window
            callback.accept(aguessedPattern); // Call the callback with the guessed pattern
        });

        frame.add(submitButton); // Add the submit button to the frame

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Step 3: Make the frame visible
        frame.setVisible(true);
    }

    public void handlePatternGuess(String guessedPattern) {
        if (guessedPattern.equals(game.getDealerPattern())) {
            JOptionPane.showMessageDialog(null, "Congratulations! You guessed the pattern correctly!", "Pattern Guess",
                    JOptionPane.INFORMATION_MESSAGE);

            showBalloonFrame();
            playCheerSound();
            askPlayAgain(game);

        } else {
            if (game.getNumberOfGuessesRemaining() > 0) {
                JOptionPane.showMessageDialog(null,
                        "Sorry! Your guess was incorrect, please continue selecting cards.\nYou have "
                                + game.getNumberOfGuessesRemaining() + " guesses remaining.",
                        "Pattern Guess", JOptionPane.ERROR_MESSAGE);
                game.clearSelectedCards();
                return;
            }

            // User out of guesses, show they lost the game
            JOptionPane.showMessageDialog(null,
                    "Sorry! Your guess was incorrect and you have no more guesses. \nThe pattern was: "
                            + game.getDealerPattern(),
                    "Pattern Guess", JOptionPane.INFORMATION_MESSAGE);
            askPlayAgain(game);
        }
    }

    public void registerPatternSelectionCallback() {
        showPatternSelectionOptions(this::handlePatternGuess);
    }
}
