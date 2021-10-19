package Main;
import org.jetbrains.annotations.NotNull;

import java.util.*;
public class Blackjack {
    //arraylist generator
    private ArrayList<Integer> pickedCardsTypeD = new ArrayList<>();
    private ArrayList<Integer> pickedCardsTypeP = new ArrayList<>();
    private ArrayList<Integer> spadeCards = new ArrayList<>();
    private ArrayList<Integer> heartCards = new ArrayList<>();
    private ArrayList<Integer> clubsCards = new ArrayList<>();
    private ArrayList<Integer> diamondsCards = new ArrayList<>();
    private ArrayList<String> cardsType = new ArrayList<>();
    private ArrayList<Integer> cardsP = new ArrayList<>();
    private ArrayList<Integer> cardsD = new ArrayList<>();
    //Vars for points
    protected boolean aceTimeP;
    protected boolean aceTimeD;
    private int pointsP = 0;
    private int pointsD = 0;
    //Vars for gameloop
    private boolean gameShouldWork = true;
    //clearing of array contents
    public void arrayClearBlackjack(){
        cardsP.clear();
        cardsD.clear();
        pickedCardsTypeD.clear();
        pickedCardsTypeP.clear();
        spadeCards.clear();
        heartCards.clear();
        clubsCards.clear();
        diamondsCards.clear();
    }
    //Randomizer generator
    Random Randomizer = new Random();
    //filling of arraylists
    public void gameStartBlackjack(){
        pointsD = 0;
        pointsP = 0;
        aceTimeD = false;
        aceTimeP = false;
        arrayClearBlackjack();
        int temp = 1;
        for (int i = 0; i < 13; i++) {
            spadeCards.add(i, temp);
            heartCards.add(i, temp);
            clubsCards.add(i, temp);
            diamondsCards.add(i, temp);
            temp++;
            if (temp > 13) {
                temp = 1;
            }
        }
        cardsType.add(0, "Hearts");
        cardsType.add(1, "Spades");
        cardsType.add(2, "Clubs");
        cardsType.add(3, "Diamonds");
    }
    //getters for looking at arraylists
    public ArrayList<Integer> getCardsP() {
        return cardsP;
    }
    public ArrayList<String> getCardsType() {
        return cardsType;
    }
    public ArrayList<Integer> getPickedCardsTypeP() {
        return pickedCardsTypeP;
    }
    public ArrayList<Integer> getPickedCardsTypeD() {
        return pickedCardsTypeD;
    }
    public ArrayList<Integer> getCardsD() {
        return cardsD;
    }
    public int getPointsP() {
        return pointsP;
    }
    public int getPointsD() {
        return pointsD;
    }
    public boolean getGameShouldWork() {
        return gameShouldWork;
    }
    public ArrayList<Integer> getSpadeCards() {
        return spadeCards;
    }
    public ArrayList<Integer> getHeartCards() {
        return heartCards;
    }
    public ArrayList<Integer> getDiamondsCards() {
        return diamondsCards;
    }
    public ArrayList<Integer> getClubsCards() {
        return clubsCards;
    }
    //setters for emergencies
    public void setGameShouldWork(boolean gameShouldWork) {
        this.gameShouldWork = gameShouldWork;
    }
    public void setCardsP(ArrayList<Integer> cardsP) {
        this.cardsP = cardsP;
    }
    public void setCardsType(ArrayList<String> cardsType) {
        this.cardsType = cardsType;
    }
    public void setPointsP(int pointsP) {
        this.pointsP = pointsP;
    }
    public void setPointsD(int pointsD) {
        this.pointsD = pointsD;
    }
    public void setPickedCardsTypeD(ArrayList<Integer> pickedCardsTypeD) {
        this.pickedCardsTypeD = pickedCardsTypeD;
    }
    public void setPickedCardsTypeP(ArrayList<Integer> pickedCardsTypeP) {
        this.pickedCardsTypeP = pickedCardsTypeP;
    }
    public void setCardsD(ArrayList<Integer> cardsD) {
        this.cardsD = cardsD;
    }
    public void setSpadeCards(ArrayList<Integer> cardsP) {
        this.spadeCards = cardsP;
    }
    public void setHeartCards(ArrayList<Integer> heartCards) {
        this.heartCards = heartCards;
    }
    public void setDiamondsCards(ArrayList<Integer> diamondsCards) {
        this.diamondsCards = diamondsCards;
    }
    public void setClubsCards(ArrayList<Integer> clubsCards) {
        this.clubsCards = clubsCards;
    }
    //addition to points
    public void addToPointD(int toAdd){
        int temp = this.pointsD;
        this.pointsD = temp + toAdd;
    }
    public void addToPointP(int toAdd){
        int temp = this.pointsP;
        this.pointsP = temp + toAdd;
    }
    //see if it generated correctly
    public void amountOfCards(){
        System.out.println("Spades: " + getSpadeCards());
        System.out.println("Hearts: " + getHeartCards());
        System.out.println("Diamonds: " + getDiamondsCards());
        System.out.println("Clubs: " + getClubsCards());
    }
    //computer's card picker
    public void dealerCard(){
        int temp = 0;
        int DChoice1 = Randomizer.nextInt(3);
        int DChoice2 = Randomizer.nextInt(12);
        int index = pickedCardsTypeD.size();
        pickedCardsTypeD.add(index, DChoice1);
        int index1 = cardsD.size();
        cardsD.add(index1, DChoice2);
        cardCheck("dealer");
        //point addition for dealer
        if(DChoice2 >= 9){
            addToPointD(10);
        }
        else if (DChoice2 == 0 && !aceTimeD){
            addToPointD(11);
            aceTimeD = true;
        }
        else{
            temp = DChoice2;
            temp++;
            addToPointD(temp);
        }
        System.out.println("Dealer points: " + getPointsD());
    }
    //player's card picker
    public void playerCard(){
        int temp = 0;
        int PChoice1 = Randomizer.nextInt(3);
        int PChoice2 = Randomizer.nextInt(12);
        int index = pickedCardsTypeP.size();
        pickedCardsTypeP.add(index, PChoice1);
        int index1 = cardsP.size();
        cardsP.add(index1, PChoice2);
        cardCheck("player");
        //point addition for player
        if(PChoice2 >= 9){
            addToPointP(10);
        }
        else if (PChoice2 == 0 && !aceTimeP){
            addToPointP(11);
            aceTimeP = true;
        }
        else{
            temp = PChoice2;
            temp++;
            addToPointP(temp);
        }
        System.out.println("Player points: " + getPointsP());
    }
    //End game
    public void gameEnd(){
        if (pointsD > 21){
            System.out.println("Player Wins");
            arrayClearBlackjack();
            setGameShouldWork(false);
        }
        else if(pointsP > 21){
            System.out.println("Dealer Wins");
            arrayClearBlackjack();
            setGameShouldWork(false);
        }
        else{
            setGameShouldWork(true);
        }
    }
    //Which card was chosen checker
    private String cardCheck(@NotNull String CheckForWho){
        String b;
        String a;
        String Result;
        String Result1;
        String dealerP = String.valueOf(getPickedCardsTypeD());
        String playerP = String.valueOf(getPickedCardsTypeP());
        String dealer = String.valueOf(getCardsD());
        String player = String.valueOf(getCardsP());
        if(CheckForWho.equals("dealer")){
            b = dealer;
            a = dealerP;
            CheckForWho = "Dealer";
        }
        else if(CheckForWho.equals("player")){
            b = player;
            a = playerP;
            CheckForWho = "Player";
        }
        else{
            b = null;
            a = null;
        }
        switch (b){
            case "[0]" -> {
                Result = "Ace of ";
                break;
            }
            case "[1]" -> {
                Result = "2 of ";
                break;
            }
            case "[2]" -> {
                Result = "3 of ";
                break;
            }
            case "[3]" -> {
                Result = "4 of ";
                break;
            }
            case "[4]" -> {
                Result = "5 of ";
                break;
            }
            case "[5]" -> {
                Result = "6 of ";
                break;
            }
            case "[6]" -> {
                Result = "7 of ";
                break;
            }
            case "[7]" -> {
                Result = "8 of ";
                break;
            }
            case "[8]" -> {
                Result = "9 of ";
                break;
            }
            case "[9]" -> {
                Result = "10 of ";
                break;
            }
            case "[10]" -> {
                Result = "Jack of ";
                break;
            }
            case "[11]" -> {
                Result = "Queen of ";
                break;
            }
            case "[12]" -> {
                Result = "King of ";
                break;
            }
            default -> {
                Result = "error";
                System.out.println("error");
                break;
            }
        }
        switch (a) {
            case "[0]" -> {
                Result1 = "Hearts";
                break;
            }
            case "[1]" -> {
                Result1 = "Clubs";
                break;
            }
            case "[2]" -> {
                Result1 = "Diamonds";
                break;
            }
            case "[3]" -> {
                Result1 = "Spades";
                break;
            }
            default -> {
                Result1 = "error";
                System.out.println("error");
                break;
            }
        }
        System.out.println(CheckForWho + " picked up " + Result + Result1);
        return Result + Result1;
    }
}