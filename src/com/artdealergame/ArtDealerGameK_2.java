package com.artdealergame;

import com.options.Pattern_K_2;

import java.util.*;

public class ArtDealerGameK_2  implements ArtDealerGame{
    private static final String[] SIMPLE_PATTERNS = {"All Red", "All Black", "All Hearts", "All Queens"};

    Pattern_K_2 checkCards = new Pattern_K_2();

    //the current pattern the dealer is looking for
    private String dealerpattern;

    public ArtDealerGameK_2() {
        List<String> patternList = new ArrayList<>(Arrays.asList(SIMPLE_PATTERNS));
        Collections.shuffle(patternList);
        dealerpattern = patternList.get(0);
    }


    /*simulate the dealer's decision whether to buy the card based
    on the pattern
    */

    @Override
    public boolean isDealerBuying(String[] cards) {
        switch (dealerpattern) {
            case "All Red":
                return checkCards.allRed(cards);
            case "All Black":
                return checkCards.allBlack(cards);
            case "All Hearts":
                return checkCards.allHearts(cards);
            case "All Queens":
                return checkCards.allQueens(cards);
            default:
                return false;
        }
    }

    @Override
    public String getDealerPattern() {
        return dealerpattern;
    }

}
