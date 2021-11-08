package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
//For keeping track of cards
public final class CardManager {
    //Arraylist of all cards generated so far
    private static final ArrayList<CardTypes> assignedCards = new ArrayList<>();
    //how many occurrences of a cards are possible
    static int decks = 0;
    //cards generation
    public static CardTypes newCard() {
        CardTypes[] allTypes = CardTypes.values();
        while(true){
            //pick random cards
            int randomInt = new Random().nextInt(51);
            //if a cards has come up more than possible ie more than one per deck generate new cards
            if(Collections.frequency(assignedCards, allTypes[randomInt]) < decks) {
                //add generated cards to Arraylist of generated cards
                assignedCards.add(allTypes[randomInt]);
                return allTypes[randomInt];
            }
        }
    }
    //get size of all cards generated
    public static int assignedCardsLength() {
        return assignedCards.size();
    }
    //set how many decks are in a game
    public static void setDeckSet(int value) {
        decks = value;
    }
    //get how many decks in game
    public static int getDecks() {
        return decks;
    }
}
