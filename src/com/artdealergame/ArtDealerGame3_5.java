package com.artdealergame;

import com.constants.Data;
import com.options.Pattern3_5;
import com.options.Pattern_K_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArtDealerGame3_5 implements ArtDealerGame {

    Pattern_K_2 patterns = new Pattern_K_2();
    Pattern3_5 pattern3_5 = new Pattern3_5();

    private String dealerPattern;

    public ArtDealerGame3_5() {
        List<String> patternList = new ArrayList<>(Arrays.asList(Data.SIMPLE_PATTERNS));
        Collections.addAll(patternList, Data.COMPLEX_PATTERNS);
        Collections.shuffle(patternList);
        dealerPattern = patternList.get(0);
    }

    @Override
    public boolean isDealerBuying(String[] cards) {
        switch (dealerPattern) {
            case "All Red":
                return patterns.allRed(cards);
            case "All Black":
                return patterns.allBlack(cards);
            case "All Hearts":
                return patterns.allHearts(cards);
            case "All Queens":
                return patterns.allQueens(cards);
            case "All Single-Digit Primes":
                return pattern3_5.allSingleDigitPrimes(cards);
            case "Sum to 9":
                return pattern3_5.sumToNine(cards);
            default:
                return false;
        }
    }

    @Override
    public String getDealerPattern() {
        return dealerPattern;
    }

}