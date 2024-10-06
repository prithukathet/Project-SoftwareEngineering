package com.artdealergame;

import java.util.Random;
import javax.swing.JOptionPane;
import com.options.PatternK2;

public class ArtDealerGameK2 {

    private String dealerPattern;
    private Random random = new Random();

    public class BooleanStringPair {
        private boolean status;
        private String str;

        public BooleanStringPair(boolean status, String str) {
            this.status = status;
            this.str = str;
        }

        public boolean successful() {
            return status;
        }

        public String msg() {
            return str;
        }
    }

    private int guessesMade = 0; // Counter to track guesses

    // Maximum number of guesses allowed
    private static final int MAX_GUESSES = 3;

    // Patterns available for the dealer
    private static final String[] PATTERNS = { "All Red", "All Black", "All Hearts", "All Diamonds", "All Clubs",
            "All Spades", "All Aces", "All Kings", "All Queens", "All Jacks", "All Tens", "All Nines", "All Eights",
            "All Sevens", "All Sixes", "All Fives", "All Fours", "All Threes", "All Twos" };

    public ArtDealerGameK2() {
        resetGame(); // Initialize the game with a random pattern
    }

    // Reset the game and generate a new pattern
    public void resetGame() {
        // Random random = new Random();
        dealerPattern = PATTERNS[random.nextInt(PATTERNS.length)];
        guessesMade = 0; // Reset the guesses
        System.out.println("ARTDEALERK@: Game was reset"); // For debugging
        System.out.println("ARTDEALER35: New Dealer's pattern: " + dealerPattern); // For debugging
    }

    // Method to check if the cards match the dealer's pattern
    public BooleanStringPair checkMatchingCards(String[] selectedCards) {
        StringBuilder result = new StringBuilder(); // To store the result message
        boolean atLeastOneMatch = false; // To track if at least one card was purchased by the dealer

        // Check if max guesses have been reached
        if (guessesMade >= MAX_GUESSES) {
            return new BooleanStringPair(false,
                    "Game over. You have used all your guesses. The dealer's pattern was: " + dealerPattern);
        }

        int totalMatches = 0;

        for (String card : selectedCards) {
            boolean isMatch = isDealerBuying(card);

            // If there's a match, append the card
            if (isMatch) {
                if (!atLeastOneMatch) {
                    // Add the header once, before appending the cards
                    result.append("Cards Purchased by the Dealer:\n");
                    atLeastOneMatch = true; // Ensure this block is executed only once
                }
                result.append(card).append("\n");
                totalMatches++;
            }
        }

        // Increment guesses counter after the check
        guessesMade++;

        // Return final feedback message
        if (totalMatches == 4) {
            int response = JOptionPane.showOptionDialog(null,
                    "Congratulations! The dealer has purchased all four cards. Do you want to guess the pattern?",
                    "Guess the Pattern", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // Use default
                                                                                                        // icon
                    new Object[] { "Guess The Pattern", "Continue Guessing" }, // Options
                    null // Default option
            );


            // If they choose to guess the pattern
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("ARTDEALER35: User chose to guess the pattern.");
                // I NEED TO ADD THE POPUP BOX FOR MEMBER TO GUESS THE PATTERN
                return new BooleanStringPair(true, "You have a Chance to Guess The Pattern.");
            } else {
                return new BooleanStringPair(false,
                        "You chose to continue guessing. You have " + (MAX_GUESSES - guessesMade) + " guesses left.");
            }

        } else if (atLeastOneMatch) {
            return new BooleanStringPair(false, result.toString());
        } else if (guessesMade >= MAX_GUESSES) {
            return new BooleanStringPair(false,
                    "None of the cards were purchased. Game over. The dealer's pattern was: " + dealerPattern);
        } else {
            return new BooleanStringPair(false, "None of the cards were purchased by the dealer. "
                    + (MAX_GUESSES - guessesMade) + " guesses left.");
        }
    }

    // Method to check if a single card is being purchased by the dealer
    // add all patterns from K-2
    public boolean isDealerBuying(String card) {
        switch (dealerPattern) {
            case "All Red":
                return PatternK2.allRed(card);
            case "All Black":
                return PatternK2.allBlack(card);
            case "All Kings":
                return PatternK2.allKings(card);
            case "All Queens":
                return PatternK2.allQueens(card);
            case "All Jacks":
                return PatternK2.allJacks(card);
            case "All Tens":
                return PatternK2.allTens(card);
            case "All Nines":
                return PatternK2.allNines(card);
            case "All Eights":
                return PatternK2.allEights(card);
            case "All Sevens":
                return PatternK2.allSevens(card);
            case "All Sixes":
                return PatternK2.allSixes(card);
            case "All Fives":
                return PatternK2.allFives(card);
            case "All Fours":
                return PatternK2.allFours(card);
            case "All Threes":
                return PatternK2.allThrees(card);
            case "All Twos":
                return PatternK2.allTwos(card);
            case "All Hearts":
                return PatternK2.allHearts(card);
            case "All Diamonds":
                return PatternK2.allDiamonds(card);
            case "All Clubs":
                return PatternK2.allClubs(card);
            case "All Spades":
                return PatternK2.allSpades(card);
            default:
                System.out.println("GameK2 Window : Unknown dealer pattern: " + dealerPattern);
                return false;
        }
    }

    // Method to get the current dealer's pattern
    public String getDealerPattern() {
        return dealerPattern;
    }

    public String[] getPatterns() {
        return PATTERNS;
    }
}
