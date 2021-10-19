package main;

public enum CardTypes {
    Diamond_2("2 of Diamonds", 2),
    Diamond_3("3 of Diamonds", 3),
    Diamond_4("4 of Diamonds", 4),
    Diamond_5("5 of Diamonds", 5),
    Diamond_6("6 of Diamonds", 6),
    Diamond_7("7 of Diamonds", 7),
    Diamond_8("8 of Diamonds", 8),
    Diamond_9("9 of Diamonds", 9),
    Diamond_10("10 of Diamonds", 10),
    Heart_2("2 of Hearts", 2),
    Heart_3("3 of Hearts", 3),
    Heart_4("4 of Hearts", 4),
    Heart_5("5 of Hearts", 5),
    Heart_6("6 of Hearts", 6),
    Heart_7("7 of Hearts", 7),
    Heart_8("8 of Hearts", 8),
    Heart_9("9 of Hearts", 9),
    Heart_10("10 of Hearts", 10),
    Spade_2("2 of Spades", 2),
    Spade_3("3 of Spades", 3),
    Spade_4("4 of Spades", 4),
    Spade_5("5 of Spades", 5),
    Spade_6("6 of Spades", 6),
    Spade_7("7 of Spades", 7),
    Spade_8("8 of Spades", 8),
    Spade_9("9 of Spades", 9),
    Spade_10("10 of Spades", 10),
    Club_2("2 of Clubs", 2),
    Club_3("3 of Clubs", 3),
    Club_4("4 of Clubs", 4),
    Club_5("5 of Clubs", 5),
    Club_6("6 of Clubs", 6),
    Club_7("7 of Clubs", 7),
    Club_8("8 of Clubs", 8),
    Club_9("9 of Clubs", 9),
    Club_10("10 of Clubs", 10),
    Diamond_Ace("Ace of Diamonds", 11),
    Diamond_Queen("Queen of Diamonds", 10),
    Diamond_King("King of Diamonds", 10),
    Diamond_Jack("Jack of Diamonds", 10),
    Heart_Ace("Ace of Hearts", 11),
    Heart_Queen("Queen of Hearts", 10),
    Heart_King("King of Hearts", 10),
    Heart_Jack("Jack of Hearts", 10),
    Spade_Ace("Ace of Spades", 11),
    Spade_Queen("Queen of Spades", 10),
    Spade_King("King of Spades", 10),
    Spade_Jack("Jack of Spades", 10),
    Club_Ace("Ace of Clubs", 11),
    Club_Queen("Queen of Clubs", 10),
    Club_King("King of Clubs", 10),
    Club_Jack("Jack of Clubs", 10);

    private final String name;
    private final int value;

    CardTypes(String name, int value){
        this.name = name;
        this.value = value;
    }

    public int getNewValue(int currentValue){
        if(this.value >= 11 && currentValue > 10){
            return currentValue + 1;
        }
        return currentValue + this.value;
    }

    public String getName() {
        return this.name;
    }
}
