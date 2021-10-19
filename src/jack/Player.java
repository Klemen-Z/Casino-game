package jack;

public final class Player {
    //vars for player
    private final String name;
    private int points;
    //
    public Player(String name, CardTypes card){
        this.name = name;
        addPoints(card, true);
    }
    //get the name of a player
    public String getName() {
        return name;
    }

    public int addPoints(CardTypes card, boolean show) {
        //give a player points
        points = card.getNewValue(points);
        if(show){
            //how many points a player has displayed
            System.out.println(this.name + " has " + this.points + " points!");
        }
        if(points < 21) {
            //lose aka points is higher than 21
            return 0;
        } else if(points == 21) {
            //win aka points is 21
            return 1;
        } else {
            //everything else
            return 2;
        }
    }
    //get Points of a player
    public int getPoints() {
        return points;
    }
    //check if player has points that are equal to max in other file
    public boolean isEqualToPoints(int value) {
        return value == points;
    }

}
