package com.artdealergame;

public interface ArtDealerGame {
    boolean isDealerBuying(String[] cards);
    String getDealerPattern();
    String checkMatchingCards(String[] selectedCards);
}