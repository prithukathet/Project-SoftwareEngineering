package com.artdealergame;

import com.options.PatternBase;

import java.util.ArrayList;

import com.options.Pattern35;
// import com.options.Pattern68;

public class ArtDealerGame68 extends ArtDealerGameBase {

    public ArtDealerGame68() {
        super(new String[] { "Three of a Kind", "Two Pair", "One Pair", "Sum to 9", "Ace and Black Jack", "Single Digit Primes", "All Red", "All Black", "All Hearts", "All Diamonds", "All Clubs", "All Spades",
                "All Aces", "All Kings", "All Queens", "All Jacks", "All Tens", "All Nines", "All Eights", "All Sevens",
                "All Sixes", "All Fives", "All Fours", "All Threes", "All Twos" });
    }

    // Method to check if a single card is being purchased by the dealer
    // add all patterns from K-2 and 3-5
    @Override
    public ArrayList<Card> isDealerBuying(ArrayList<Card> cards) {
        switch (getDealerPattern()) {
        case "All Red":
            return PatternBase.allRed(cards);
        case "All Black":
            return PatternBase.allBlack(cards);
        case "All Aces":
            return PatternBase.allAces(cards);
        case "All Kings":
            return PatternBase.allKings(cards);
        case "All Queens":
            return PatternBase.allQueens(cards);
        case "All Jacks":
            return PatternBase.allJacks(cards);
        case "All Tens":
            return PatternBase.allTens(cards);
        case "All Nines":
            return PatternBase.allNines(cards);
        case "All Eights":
            return PatternBase.allEights(cards);
        case "All Sevens":
            return PatternBase.allSevens(cards);
        case "All Sixes":
            return PatternBase.allSixes(cards);
        case "All Fives":
            return PatternBase.allFives(cards);
        case "All Fours":
            return PatternBase.allFours(cards);
        case "All Threes":
            return PatternBase.allThrees(cards);
        case "All Twos":
            return PatternBase.allTwos(cards);
        case "All Hearts":
            return PatternBase.allHearts(cards);
        case "All Diamonds":
            return PatternBase.allDiamonds(cards);
        case "All Clubs":
            return PatternBase.allClubs(cards);
        case "All Spades":
            return PatternBase.allSpades(cards);
        case "All Single-Digit Primes":
            return Pattern35.allSingleDigitPrimes(cards);
        case "Sum to 9":
            // TODO: How to handle this case?
            return new ArrayList<Card>();
        // return !Pattern35.findCombinationsToNine(new String[] { card }).isEmpty();
        case "Ace and Black Jack":
            // TODO: How to handle this case?
            return new ArrayList<Card>();
        // return Pattern35.checkAceAndBlackjack(new String[] { card });
        case "One Pair":
            // TODO: How to handle this case?
            return new ArrayList<Card>();
        // return Pattern68.onePair(new String[] { card });
        case "Two Pairs":
            // TODO: How to handle this case?
            return new ArrayList<Card>();
        // return Pattern68.twoPair(new String[] { card });
        case "Three of a Kind":
            // TODO: How to handle this case?
            return new ArrayList<Card>();
        // return Pattern68.threePair(new String[] { card });
        case "Four of a Kind":
            // TODO: How to handle this case?
            return new ArrayList<Card>();
        // return Pattern68.fourPair(new String[] { card });
        default:
            System.out.println("Game35 Window : Unknown dealer pattern: " + dealerPattern);
            return new ArrayList<Card>();
        }
    }
}
