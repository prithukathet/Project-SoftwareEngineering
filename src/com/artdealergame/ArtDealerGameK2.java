package com.artdealergame;

import java.util.ArrayList;

import com.options.PatternBase;

public class ArtDealerGameK2 extends ArtDealerGameBase {

    public ArtDealerGameK2() {
        super(new String[] { "All Red", "All Black", "All Hearts", "All Diamonds", "All Clubs", "All Spades",
                "All Aces", "All Kings", "All Queens", "All Jacks", "All Tens", "All Nines", "All Eights", "All Sevens",
                "All Sixes", "All Fives", "All Fours", "All Threes", "All Twos" });
    }

    // Method to check if a single card is being purchased by the dealer
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
        default:
            return new ArrayList<Card>();
        }
    }
}
