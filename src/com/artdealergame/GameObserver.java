package com.artdealergame;

import java.util.ArrayList;

public interface GameObserver {
    void update(ArrayList<Card> selectedCards);

    void update(int numberOfAttempts);
}
