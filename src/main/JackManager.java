package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public final class JackManager {

    private final BufferedReader reader;

    private int amountOfPlayers;

    private final ArrayList<Player> players = new ArrayList<>();

    private final CardTypes secretCard;

    private boolean openHouse;

    private Player dealer;

    public JackManager() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        askForPlayerCount(100);
        CardManager.setDeckSet(amountOfPlayers/2);
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
        if (returnV == 2) {
            System.out.println(player.getName() + " was eliminated!");
        } else if (returnV == 1) {
            System.out.println(player.getName() + " wins with " + player.getPoints());
        }
    }

    private void game() throws IOException {
        while(true) {
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
                for (Player player : winners) {
                    System.out.println(player.getName() + " wins with " + player.getPoints());
                }
                return;
            } else if(players.size() == 1) {
                System.out.println(players.get(0).getName() + " wins with " + players.get(0).getPoints());
                return;
            }
            ArrayList<Player> killed = new ArrayList<>();
            ArrayList<Player> passed = new ArrayList<>();
            for (Player player : players) {
                if (!player.getName().equals("Computer")) {
                    System.out.println(player.getName() + " it's your turn!");
                    System.out.println("Do you wish to pick a card?");
                    System.out.println("1 = True, 0 = False");
                    String returnValue = reader.readLine();
                    if (returnValue.contains("1")) {
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
                    } else if (returnValue.contains("0")) {
                        System.out.println(player.getName() + " skips");
                        passed.add(player);
                    }
                    else {
                        System.out.println(player.getName() + " you can't type 1 or 0, you forfeit your turn.");
                        passed.add(player);
                    }
                } else {
                    /* if(player.getPoints() < 18) {
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
                    } else {
                        System.out.println(player.getName() + " skips");
                    }*/
                }
            }
            players.removeAll(killed);
            if(passed.size() == players.size() - 1){
                System.out.println("The secret card was the " + secretCard.getName() + "!");
                while (true){
                    ArrayList<Player> killedd = new ArrayList<>();
                    if(dealer.getPoints() < 18) {
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
                        ArrayList<Integer> values = new ArrayList<>();
                        ArrayList<Player> playerss = new ArrayList<>(players);
                        for (Player player : players) {
                            if(player.getPoints() <= 21) {
                                values.add(player.getPoints());
                            }
                        }
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
