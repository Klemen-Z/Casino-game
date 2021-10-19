package Main;
public class Main
{
    public static void main(String[] args)
    {
        int i = 0;
        Blackjack Mort = new Blackjack();
        Mort.gameStartBlackjack();
        while (Mort.getGameShouldWork()) {
            if(i < 4){
                Mort.dealerCard();
                Mort.playerCard();
                i++;
            }
            Mort.gameEnd();
        }
    }
}