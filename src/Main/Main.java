package Main;
public class Main
{
    public static void main(String[] args)
    {
        Blackjack Mort = new Blackjack();
        Mort.gameStartBlackjack();
        while (Mort.getGameShouldWork()) {
            for (int i = 0; i < 2; i++) {
                Mort.dealerCard();
                Mort.playerCard();
                Mort.gameEnd();
            }
        }
    }
}