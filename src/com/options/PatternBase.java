package com.options;

import com.artdealergame.CardSuit;

import java.util.ArrayList;

import com.artdealergame.Card;
import com.artdealergame.CardValue;

public interface PatternBase {

    // Specific methods to check all types
    public static ArrayList<Card> allRed(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == CardSuit.HEARTS || card.getSuit() == CardSuit.DIAMONDS) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allBlack(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == CardSuit.SPADES || card.getSuit() == CardSuit.CLUBS) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allAces(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.ACE) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allKings(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.KING) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allQueens(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.QUEEN) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allJacks(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.JACK) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allTens(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.TEN) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allNines(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.NINE) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allEights(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.EIGHT) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allSevens(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.SEVEN) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allSixes(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.SIX) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allFives(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.FIVE) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allFours(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.FOUR) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allThrees(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.THREE) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allTwos(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getValue() == CardValue.TWO) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allHearts(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == CardSuit.HEARTS) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allDiamonds(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == CardSuit.DIAMONDS) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allSpades(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == CardSuit.SPADES) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public static ArrayList<Card> allClubs(ArrayList<Card> cards) {
        ArrayList<Card> matchingCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == CardSuit.CLUBS) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }
}
