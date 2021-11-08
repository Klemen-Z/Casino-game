package main;
//import
import javax.swing.*;
/*import java.io.BufferedReader;*/
import java.io.IOException;
/*import java.io.InputStreamReader;*/
import java.util.ArrayList;
import java.util.Collections;
//Game Logic
public final class JackManager {
    //Fenster wird automatisch beim erstellen des Objektes erstellt
    LabelProgress lframe = new LabelProgress();
    Frame frame = new Frame(); //Das eigentliche Fenster wird gestartet
    //Winners list
    ArrayList<Player> winners = new ArrayList<>();
    //eliminated players list
    ArrayList<Player> killed = new ArrayList<>();
    //players who passed/forfeit their turn
    ArrayList<Player> passed = new ArrayList<>();
    //input reader
    /*private final BufferedReader reader;*/
    //variables

    private static int dPoints;
    private static int pPoints;
    private String returnValue;
    private String Immage;
    private int amountOfPlayers;
    private final ArrayList<Player> players = new ArrayList<>();
    private final CardTypes secretCard;
    private final Player dealer;

    //get points
    public static int getdPoints() {
        return dPoints;
    }
    public static int getpPoints() {
        return pPoints;
    }
    //get Image
    public String getImmage() {
        return Immage;
    }

    //Game start
    public JackManager() throws IOException {
        /*reader = new BufferedReader(new InputStreamReader(System.in));*/
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
        while (!frame.value.getText().equals(Integer.toString(getpPoints()))){
            frame.value.setText(Integer.toString(getpPoints()));
        }
        Player dealer = new Player("Computer", card);
        this.dealer = dealer;
        players.add(dealer);
        dPoints = dealer.getPoints();
        frame.valuec.setText(Integer.toString(getdPoints()));
        secretCard = CardManager.newCard();
        dealer.addPoints(secretCard, false);
        System.out.println("Computer drew the " + card.getName());
        System.out.println("Computer drew the secret Card");
        game();
    }
    //How many players are playing
    private void askForPlayerCount(int maxPlayer) /*throws IOException */{
        System.out.println("How many players?");
        String amountPlayers = "1"/*reader.readLine()*/;
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
    private void newPlayer(int i) /*throws IOException*/ {
        System.out.println("Player " + i + ", please enter your name!");
        String name = "p" /*reader.readLine()*/;
        CardTypes card = CardManager.newCard();
        Player player = new Player(name, card);
        players.add(player);
        System.out.println(name + " drew the " + card.getName());
        card = CardManager.newCard();
        int returnV = player.addPoints(card, true);
        System.out.println(name + " drew the " + card.getName());
        //labels pain
        pPoints = player.getPoints();
        ImageIcon image = new ImageIcon(getImmage());
        frame.cards.setIcon(image);
        frame.cards.setBounds(400, 397, 200, 300);
        frame.value.setText(Integer.toString(getpPoints()));
        //in case they somehow win/lose immediately
        if (returnV == 2) {
            System.out.println(player.getName() + " was eliminated!");
        } else if (returnV == 1) {
            winners.add(player);
            System.out.println(player.getName() + " got a Blackjack!");
        }
    }
    //Game loop
    private void game() /*throws IOException*/ {
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
                    System.out.println("Do you wish to pick a cards?");
                    System.out.println("1 = True, 0 = False");
                    while (returnValue == null){
                        returnValue = Frame.getButton()/*reader.readLine()*/;
                    }
                    if (returnValue.contains("1")) { //do they pick up a new cards?
                        CardTypes card = CardManager.newCard();
                        Immage = card.getImageName();
                        System.out.println(player.getName() + " drew the " + card.getName());
                        int returnV = player.addPoints(card, true);
                        pPoints = player.getPoints();
                        ImageIcon image = new ImageIcon(getImmage());
                        frame.cards.setIcon(image);
                        frame.cards.setBounds(400, 397, 200, 300);
                        frame.value.setText(Integer.toString(JackManager.getpPoints()));
                        returnValue = null;
                        if (returnV == 2) {
                            System.out.println(player.getName() + " was eliminated!");
                            killed.add(player);
                        } else if (returnV == 1) {
                            System.out.println(player.getName() + " achieved " + player.getPoints() + " points!");
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
                //Todo winner frame (lose)
                JLabel lose = new JLabel();
                ImageIcon imageL = new ImageIcon("lose.png");
                lose.setBounds(0, 0, 1000, 700);
                lose.setIcon(imageL);
                frame.add(lose);
                frame.setComponentZOrder(lose, 0);
                return;
            }
            else if(players.size() == 1 && winners.size() > 0){
                for(Player player : winners){
                    if(!players.contains(player)){
                        players.add(player);
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
                //reveal dealer's second cards
                System.out.println("The secret cards was the " + secretCard.getName() + "!");
                System.out.println("The dealer has " + dealer.getPoints() + " points!");
                frame.valuec.setText(Integer.toString(getdPoints()));
                while (true){
                    //dealer picks up no more cards after they get a score of 18
                    if(dealer.getPoints() <= 17) {
                        //dealer draws a cards plus all win/lose/draw again logic
                        CardTypes card = CardManager.newCard();
                        System.out.println(dealer.getName() + " drew the " + card.getName());
                        int returnV = dealer.addPoints(card, true);
                        dPoints = dealer.getPoints();
                        while(!frame.valuec.getText().equalsIgnoreCase(Integer.toString(dPoints))){
                            frame.valuec.setText(Integer.toString(getdPoints()));
                        }
                        boolean temp = false;
                        for (Player player : winners){
                            if (player.getPoints() == dealer.getPoints()){
                                System.out.println(player.getName() + " and the dealer tied");
                                temp = true;
                            }
                        }
                        if (temp){
                            //Todo winner frame (tie)
                            frame.value.setText(Integer.toString(getpPoints()));
                            JLabel draw = new JLabel();
                            ImageIcon imageD = new ImageIcon("draw.png");
                            draw.setBounds(0, 0, 1000, 700);
                            draw.setIcon(imageD);
                            frame.add(draw);
                            frame.setComponentZOrder(draw, 0);
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
                            //Todo winner frame (tie)
                            frame.value.setText(Integer.toString(getpPoints()));
                            JLabel draw = new JLabel();
                            ImageIcon imageD = new ImageIcon("draw.png");
                            draw.setBounds(0, 0, 1000, 700);
                            draw.setIcon(imageD);
                            frame.add(draw);
                            frame.setComponentZOrder(draw, 0);
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
                            //Todo winner frame (win)
                            frame.value.setText(Integer.toString(getpPoints()));
                            JLabel win = new JLabel();
                            ImageIcon imageW = new ImageIcon("win.png");
                            win.setBounds(0, 0, 1000, 700);
                            win.setIcon(imageW);
                            frame.add(win);
                            frame.setComponentZOrder(win, 0);
                        }
                        return;
                    }
                }

            }
        }
    }

}