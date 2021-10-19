package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class CardManager {

    private static final ArrayList<CardTypes> assignedCards = new ArrayList<>();

    static int decks = 0;

    public static CardTypes newCard() {
        CardTypes[] allTypes = CardTypes.values();
        while(true){
            int randomInt = new Random().nextInt(51);
            if(Collections.frequency(assignedCards, allTypes[randomInt]) < decks) {
                assignedCards.add(allTypes[randomInt]);
                return allTypes[randomInt];

            }
        }
    }

    public static int assignedCardsLength() {
        return assignedCards.size();
    }

    public static void setDeckSet(int value) {
        decks = value;
    }

    public static int getDecks() {
        return decks;
    }
}
