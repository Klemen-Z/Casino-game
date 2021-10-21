package jack;
//import
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
//Game Logic
public final class JackManager {
    //Winners list
    ArrayList<Player> winners = new ArrayList<>();
    //eliminated players list
    ArrayList<Player> killed = new ArrayList<>();
    //players who passed/forfeit their turn
    ArrayList<Player> passed = new ArrayList<>();
    //input reader
    private final BufferedReader reader;
    //variables
    private int amountOfPlayers;
    private final ArrayList<Player> players = new ArrayList<>();
    private final CardTypes secretCard;
    private final Player dealer;
    //Game start
    public JackManager() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        askForPlayerCount(10);
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
        //in case they somehow win/lose immediately
        if (returnV == 2) {
            System.out.println(player.getName() + " was eliminated!");
        } else if (returnV == 1) {
            System.out.println(player.getName() + " got a Blackjack!");
            winners.add(player);
        }
    }
    //Game loop
    private void game() throws IOException {
        while(true) {
            //safety rope for removal
            players.removeAll(winners);
            players.removeAll(killed);
            //is the amount of cards left higher than players left
            if (((CardManager.getDecks() * 52) - CardManager.assignedCardsLength()) < players.size()) {
                System.out.println("Game Over!");
                ArrayList<Integer> values = new ArrayList<>();
                for (Player player : players) {
                    values.add(player.getPoints());
                }
                boolean temp = false;
                int max = Collections.max(values);
                for (Player player : players) {
                    if (player.isEqualToPoints(max)) {
                        winners.add(player);
                        temp = true;
                    }
                }
                if(temp){
                    return;
                }
                for (Player player : winners) { //Does a player win
                    System.out.println(player.getName() + " wins with " + player.getPoints());
                    return;
                }
            } else if(players.size() == 1) { // last man standing (wins as last player left)
                System.out.println(players.get(0).getName() + " wins with " + players.get(0).getPoints());
                return;
            }
            //Array list
            for (Player player : players) {
                //question player for action
                if (!player.getName().equalsIgnoreCase("computer")) {
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
                            System.out.println(player.getName() + " achieved " + player.getPoints() + "points!");
                            winners.add(player);
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
            //remove winning and eliminated players from Arraylist players
            players.removeAll(winners);
            players.removeAll(killed);
            players.removeAll(passed);
            if(players.size() == 1 && winners.size() < 1 && passed.size() < 1){
                System.out.println("Computer wins by Default!");
                return;
            }
            else if(players.size() == 1 && winners.size() > 0){
                for(Player player : winners){
                    if(!players.contains(player)){
                        players.addAll(Collections.singleton(player));
                    }
                }
            }
            //re add passed players, so they can continue playing and clear passed arraylist
            if (passed.size() > 0){
                for(Player player : passed){
                    if(!players.contains(player)){
                        players.addAll(Collections.singleton(player));
                    }
                }
            }
            //Did all players pass their turn?
            if(passed.size() > players.size() - 1 || winners.size() > players.size() - 1){
                //reveal dealer's second card
                System.out.println("The secret card was the " + secretCard.getName() + "!");
                while (true){
                    //dealer picks up no more cards after they get a score of 18
                    if(dealer.getPoints() <= 16) {
                        //dealer draws a card plus all win/lose/draw again logic
                        CardTypes card = CardManager.newCard();
                        System.out.println(dealer.getName() + " drew the " + card.getName());
                        int returnV = dealer.addPoints(card, true);
                        boolean temp = false;
                        for (Player player : winners){
                            if (player.getPoints() == dealer.getPoints()){
                                System.out.println(player.getName() + " and the dealer tied");
                                temp = true;
                            }
                        }
                        if (temp){
                            return;
                        }
                        if (returnV == 2) {
                            System.out.println(dealer.getName() + " was eliminated!");
                        } else if (returnV == 1) {
                            System.out.println(dealer.getName() + " achieved " + dealer.getPoints() + "points!");
                        }
                    }
                    else {
                        boolean temp = false;
                        for (Player player : winners){
                            if (player.getPoints() == dealer.getPoints()){
                                System.out.println(player.getName() + " and the dealer tied");
                                temp = true;
                            }
                        }
                        if (temp){
                            return;
                        }
                        //check for winner by looking through all players left for highest point amount
                        ArrayList<Integer> values = new ArrayList<>();
                        ArrayList<Player> playersS = new ArrayList<>(players);
                        for (Player player : players) {
                            if(player.getPoints() <= 21) {
                                values.add(player.getPoints());
                            }
                        }
                        //who won
                        int max = Collections.max(values);
                        for (Player player : playersS) {
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