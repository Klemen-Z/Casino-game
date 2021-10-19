package main;

public final class Player {

    private final String name;
    private int points;

    public Player(String name, CardTypes card){
        this.name = name;
        addPoints(card, true);
    }

    public String getName() {
        return name;
    }

    public int addPoints(CardTypes card, boolean show) {
        points = card.getNewValue(points);
        if(show){
            System.out.println(this.name + " has " + this.points + " points!");
        }
        if(points < 21) {
            return 0;
        } else if(points == 21) {
            return 1;
        } else {
            return 2;
        }
    }

    public int getPoints() {
        return points;
    }

    public boolean isEqualToPoints(int value) {
        return value == points;
    }

}
