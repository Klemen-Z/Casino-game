package Main;
public class Main
{
    public static void main(String[] args)
    {
        Blackjack Mort = new Blackjack();
        while (Mort.getGameShouldWork()){
            Mort.gameStartBlackjack();
            Mort.dealerCard();
            Mort.playerCard();
            Mort.gameEnd();
        }
    }
}