package jack;
//import
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
//Game Logic
public final class JackManager {
    //input reader
    private final BufferedReader reader;
    //variables
    private int amountOfPlayers;
    private final ArrayList<Player> players = new ArrayList<>();
    private final CardTypes secretCard;
    private Player dealer;
    //Game start
    public JackManager() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        askForPlayerCount(100);
        if(amountOfPlayers == 1){
            CardManager.setDeckSet(1);
        }
        else{
            CardManager.setDeckSet(amountOfPlayers/2);
        }
        for (int i = 1; i <= amountOfPlayers; i++) {
            newPlayer(i);
        }
        CardTypes card = CardManager.newCard();
        Player dealer = new Player("Computer", card);
        this.dealer = dealer;
        players.add(dealer);
        secretCard = CardManager.newCard();
        dealer.addPoints(secretCard, false);
        System.out.println("Computer drew the " + card.getName());
        System.out.println("Computer drew the secret Card");
        game();
    }
    //How many players are playing
    private void askForPlayerCount(int maxPlayer) throws IOException {
        System.out.println("How many players?");
        String amountPlayers = reader.readLine();
        try {
            int value = Integer.parseInt(amountPlayers);
            if(value > maxPlayer) {
                System.out.println("We do not support this feature yet, please return at a later date");
                amountOfPlayers = maxPlayer;
            } else if (value <= 0) {
                System.out.println("We do not support this feature yet, please return at a later date");
                askForPlayerCount(maxPlayer);
            }
            else {
                amountOfPlayers = value;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter an Integer!");
            askForPlayerCount(maxPlayer);
        }
    }
    //adds players based on Player count and gives them cards
    private void newPlayer(int i) throws IOException {
        System.out.println("Player " + i + ", please enter your name!");
        String name = reader.readLine();
        CardTypes card = CardManager.newCard();
        Player player = new Player(name, card);
        players.add(player);
        System.out.println(name + " drew the " + card.getName());
        card = CardManager.newCard();
        int returnV = player.addPoints(card, true);
        System.out.println(name + " drew the " + card.getName());
        //incase they somehow win/lose immediately
        if (returnV == 2) {
            System.out.println(player.getName() + " was eliminated!");
        } else if (returnV == 1) {
            System.out.println(player.getName() + " wins with " + player.getPoints());
        }
    }
    //Game loop
    private void game() throws IOException {
        while(true) {
            //is the amount of cards left higher than players left
            if (((CardManager.getDecks() * 52) - CardManager.assignedCardsLength()) < players.size()) {
                System.out.println("Game Over!");
                ArrayList<Integer> values = new ArrayList<>();
                for (Player player : players) {
                    values.add(player.getPoints());
                }
                int max = Collections.max(values);
                ArrayList<Player> winners = new ArrayList<>();
                for (Player player : players) {
                    if (player.isEqualToPoints(max)) {
                        winners.add(player);
                    }
                }
                for (Player player : winners) { //Does a player win
                    System.out.println(player.getName() + " wins with " + player.getPoints());
                }
                return;
            } else if(players.size() == 1) { // last man standing (wins as last player left)
                System.out.println(players.get(0).getName() + " wins with " + players.get(0).getPoints());
                return;
            }
            //Array list
            ArrayList<Player> killed = new ArrayList<>();
            ArrayList<Player> passed = new ArrayList<>();
            for (Player player : players) {
                //question player
                if (!player.getName().equals("Computer")) {
                    System.out.println(player.getName() + " it's your turn!");
                    System.out.println("Do you wish to pick a card?");
                    System.out.println("1 = True, 0 = False");
                    String returnValue = reader.readLine();
                    if (returnValue.contains("1")) { //do they pick up a new card?
                        CardTypes card = CardManager.newCard();
                        System.out.println(player.getName() + " drew the " + card.getName());
                        int returnV = player.addPoints(card, true);
                        if (returnV == 2) {
                            System.out.println(player.getName() + " was eliminated!");
                            killed.add(player);
                        } else if (returnV == 1) {
                            System.out.println(player.getName() + " wins with " + player.getPoints());
                            return;
                        }
                    } else if (returnValue.contains("0")) { //Does the current player skip their turn?
                        System.out.println(player.getName() + " skips");
                        passed.add(player);
                    }
                    else { // same as top
                        System.out.println(player.getName() + " you can't type 1 or 0, you forfeit your turn.");
                        passed.add(player);
                    }
                }
            }
            //remove eliminated players from Arraylist players
            players.removeAll(killed);
            if(players.size() == 1){
                System.out.println("Computer wins by Default!");
                return;
            }
            //Did all players pass their turn?
            if(passed.size() == players.size() - 1){
                //reveal dealer's second card
                System.out.println("The secret card was the " + secretCard.getName() + "!");
                while (true){
                    //dealer picks up no more cards after they get a score of 18
                    if(dealer.getPoints() <= 18) {
                        //dealer draws a card plus all win/lose/draw again logic
                        CardTypes card = CardManager.newCard();
                        System.out.println(dealer.getName() + " drew the " + card.getName());
                        int returnV = dealer.addPoints(card, true);
                        if (returnV == 2) {
                            System.out.println(dealer.getName() + " was eliminated!");
                        } else if (returnV == 1) {
                            System.out.println(dealer.getName() + " wins with " + dealer.getPoints());
                            return;
                        }
                    } else {
                        //check for winner by looking through all players left for highest point amount
                        ArrayList<Integer> values = new ArrayList<>();
                        ArrayList<Player> playerss = new ArrayList<>(players);
                        for (Player player : players) {
                            if(player.getPoints() <= 21) {
                                values.add(player.getPoints());
                            }
                        }
                        //who won
                        int max = Collections.max(values);
                        ArrayList<Player> winners = new ArrayList<>();
                        for (Player player : playerss) {
                            if (player.isEqualToPoints(max)) {
                                winners.add(player);
                            }
                        }
                        for (Player player : winners) {
                            System.out.println(player.getName() + " wins with " + player.getPoints());
                        }
                        return;
                    }
                }

            }
        }
    }

}