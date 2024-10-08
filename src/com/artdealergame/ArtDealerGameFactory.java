package com.artdealergame;

public class ArtDealerGameFactory {

    public static ArtDealerGameBase createGame(GameType gameType) {
        switch (gameType) {
            case GAMEK2:
                return new ArtDealerGameK2();
            case GAME35:
                return new ArtDealerGame35();
            case GAME68:
                return new ArtDealerGame68();
            default:
                return null;
        }
    }
}
