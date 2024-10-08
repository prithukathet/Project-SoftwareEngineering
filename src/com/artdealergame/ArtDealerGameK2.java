package com.artdealergame;

import com.options.PatternBase;

public class ArtDealerGameK2 extends ArtDealerGameBase {

    public ArtDealerGameK2() {
        super(new String[] { "All Red", "All Black", "All Hearts", "All Diamonds", "All Clubs", "All Spades",
                "All Aces", "All Kings", "All Queens", "All Jacks", "All Tens", "All Nines", "All Eights", "All Sevens",
                "All Sixes", "All Fives", "All Fours", "All Threes", "All Twos" });
    }

    // Method to check if a single card is being purchased by the dealer
    @Override
    public boolean isDealerBuying(String card) {
        switch (getDealerPattern()) {
        case "All Red":
            return PatternBase.allRed(card);
        case "All Black":
            return PatternBase.allBlack(card);
        case "All Aces":
            return PatternBase.allAces(card);
        case "All Kings":
            return PatternBase.allKings(card);
        case "All Queens":
            return PatternBase.allQueens(card);
        case "All Jacks":
            return PatternBase.allJacks(card);
        case "All Tens":
            return PatternBase.allTens(card);
        case "All Nines":
            return PatternBase.allNines(card);
        case "All Eights":
            return PatternBase.allEights(card);
        case "All Sevens":
            return PatternBase.allSevens(card);
        case "All Sixes":
            return PatternBase.allSixes(card);
        case "All Fives":
            return PatternBase.allFives(card);
        case "All Fours":
            return PatternBase.allFours(card);
        case "All Threes":
            return PatternBase.allThrees(card);
        case "All Twos":
            return PatternBase.allTwos(card);
        case "All Hearts":
            return PatternBase.allHearts(card);
        case "All Diamonds":
            return PatternBase.allDiamonds(card);
        case "All Clubs":
            return PatternBase.allClubs(card);
        case "All Spades":
            return PatternBase.allSpades(card);
        default:
            return false;
        }
    }
}
