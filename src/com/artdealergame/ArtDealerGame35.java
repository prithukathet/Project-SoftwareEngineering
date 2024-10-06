package com.artdealergame;

import java.util.Random;
import com.options.Pattern35;
import com.options.PatternK2;

public class ArtDealerGame35 {

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

    private String dealerPattern;

    public String[] getPurchasedCards() {

        // Placeholder implementation, replace with actual logic

        return new String[] { "Ace of Spades", "King of Hearts", "Queen of Diamonds", "Jack of Clubs" };

    }

    private int guessesMade = 0; // Counter to track guesses

    // Maximum number of guesses allowed
    private static final int MAX_GUESSES = 3;

    // Patterns available for the dealer
    private static final String[] PATTERNS = { "All Red", "All Black", "All Hearts", "All Diamonds", "All Clubs",
            "All Spades", "All Aces", "All Kings", "All Queens", "All Jacks", "All Tens", "All Nines", "All Eights",
            "All Sevens", "All Sixes", "All Fives", "All Fours", "All Threes", "All Twos", "All Single-Digit Primes",
            "Sum to 9", "Ace and Black Jack" };

    public ArtDealerGame35() {
        resetGame(); // Initialize the game with a random pattern
    }

    // Reset the game and generate a new pattern
    private void resetGame() {
        Random random = new Random();
        dealerPattern = "All Red"; //PATTERNS[random.nextInt(PATTERNS.length)];
        guessesMade = 0; // Reset the guesses
        System.out.println("New Dealer's pattern: " + dealerPattern); // For debugging
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

        // Iterate through the selected cards
        int totalMatches = 0;
        for (String card : selectedCards) {
            boolean isMatch = false; // To track if the current card matches the dealer's pattern

            // Check the card against the dealer's pattern
            switch (dealerPattern) {
            case "All Red":
                isMatch = PatternK2.allRed(card);
                break;
            case "All Black":
                isMatch = PatternK2.allBlack(card);
                break;
            case "All Aces":
                isMatch = PatternK2.allAces(card);
                break;
            case "Ace and Black Jack":
                isMatch = Pattern35.checkAceAndBlackjack(new String[] { card });
                break;
            case "Sum to 9":
                isMatch = Pattern35.sumToNine(new String[] { card });
                break;
            // Add additional pattern cases as needed
            default:
                return new BooleanStringPair(false, "Dealer's pattern not implemented.");
            }

            // Based on whether the card matches the pattern, append feedback to the result
            if (isMatch) {
                result.append(card).append(" was purchased by the dealer.\n");
                atLeastOneMatch = true;
                totalMatches++;
            } else {
                result.append(card).append(" was not purchased by the dealer.\n");
            }
        }

        // Increment guesses counter after the check
        guessesMade++;

        // Return final feedback message
        if (totalMatches == 4) {
            return new BooleanStringPair(true, "All cards were purchased by the dealer. You win!");
        } else if (atLeastOneMatch) {
            return new BooleanStringPair(false, result.toString()); // Return the detailed result
        } else if (guessesMade >= MAX_GUESSES) {
            return new BooleanStringPair(false,
                    "None of the cards were purchased. Game over. The dealer's pattern was: " + dealerPattern);
        } else {
            return new BooleanStringPair(false, "None of the cards were purchased by the dealer. "
                    + (MAX_GUESSES - guessesMade) + " guesses left.");
        }
    }

    // Method to check if a single card is being purchased by the dealer
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
        case "All Single-Digit Primes":
            return Pattern35.allSingleDigitPrimes(new String[] { card });
        case "Sum to 9":
            return Pattern35.sumToNine(new String[] { card });
        case "Ace and Black Jack":
            return Pattern35.checkAceAndBlackjack(new String[] { card });
        default:
            System.out.println("Unknown dealer pattern: " + dealerPattern);
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
