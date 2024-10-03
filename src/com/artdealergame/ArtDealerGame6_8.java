package com.artdealergame;

import com.constants.Data;
import com.options.Pattern3_5;
import com.options.Pattern6_8;
import com.options.Pattern_K_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArtDealerGame6_8 {


     Pattern_K_2 pattern_k_2 = new Pattern_K_2();
     Pattern3_5 pattern3_5 = new Pattern3_5();
     Pattern6_8 pattern6_8 = new Pattern6_8();
    private String dealerPattern;

    public ArtDealerGame6_8() {
        List<String> patternList = new ArrayList<>(Arrays.asList(Data.SIMPLE_PATTERNS));
        Collections.addAll(patternList, Data.COMPLEX_PATTERNS_2);
        Collections.shuffle(patternList);
        dealerPattern = patternList.get(0);
    }


    public boolean isDealerBuying(String[] cards) {
        switch (dealerPattern) {
            case "All Red":
                return pattern_k_2.allRed(cards);
            case "All Black":
                return pattern_k_2.allBlack(cards);
            case "All Hearts":
                return pattern_k_2.allHearts(cards);
            case "All Queens":
                return pattern_k_2.allQueens(cards);
            case "All Single-Digit Primes":
                return pattern3_5.allSingleDigitPrimes(cards);
            case "Sum to 9":
                return pattern3_5.sumToNine(cards);
            case "One Pair":
                return pattern6_8.onePair(cards);
            case "Flush":
                return pattern6_8.flush(cards);
            default:
                return false;
        }
    }

    public String getDealerPattern() {
        return dealerPattern;
    }

}
