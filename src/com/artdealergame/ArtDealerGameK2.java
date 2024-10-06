package com.artdealergame;

import java.util.Random;
import com.options.PatternK2;

public class ArtDealerGameK2 {

    private String dealerPattern;

    public String checkMatchingCards(String[] selectedCards) {
        // Your logic to check the matching cards goes here
        // Return a result string based on the game logic
        return "Result logic not implemented yet"; // Placeholder return
    }

    // Patterns available for the dealer
    private static final String[] PATTERNS = { "All Red", "All Black", "All Hearts", "All Diamonds", "All Clubs",
            "All Spades", "All Aces", "All Kings", "All Queens", "All Jacks", "All Tens", "All Nines", "All Eights",
            "All Sevens", "All Sixes", "All Fives", "All Fours", "All Threes", "All Twos" };

    public ArtDealerGameK2() {
        Random random = new Random();
        dealerPattern = PATTERNS[random.nextInt(PATTERNS.length)];
        System.out.println("Dealer's pattern: " + dealerPattern); // For debugging
    }

    public boolean isDealerBuying(String[] cards) {
        switch (dealerPattern) {
        case "All Red":
            return PatternK2.allRed(cards);
        case "All Black":
            return PatternK2.allBlack(cards);
        case "All Kings":
            return PatternK2.allKings(cards);
        case "All Queens":
            return PatternK2.allQueens(cards);
        case "All Jacks":
            return PatternK2.allJacks(cards);
        case "All Tens":
            return PatternK2.allTens(cards);
        case "All Nines":
            return PatternK2.allNines(cards);
        case "All Eights":
            return PatternK2.allEights(cards);
        case "All Sevens":
            return PatternK2.allSevens(cards);
        case "All Sixes":
            return PatternK2.allSixes(cards);
        case "All Fives":
            return PatternK2.allFives(cards);
        case "All Fours":
            return PatternK2.allFours(cards);
        case "All Threes":
            return PatternK2.allThrees(cards);
        case "All Twos":
            return PatternK2.allTwos(cards);
        case "All Hearts":
            return PatternK2.allHearts(cards);
        case "All Diamonds":
            return PatternK2.allDiamonds(cards);
        case "All Clubs":
            return PatternK2.allClubs(cards);
        case "All Spades":
            return PatternK2.allSpades(cards);
        default:
            System.out.println("Unknown dealer pattern: " + dealerPattern);
            return false;
        }
    }

    public String getDealerPattern() {
        return dealerPattern;
    }
}
