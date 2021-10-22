package jack;

//all cards defined with properties
public enum CardTypes {
    //cards
    Diamond_2("2 of Diamonds", 2, "K2.png"),
    Diamond_3("3 of Diamonds", 3, "K3.png"),
    Diamond_4("4 of Diamonds", 4, "K4.png"),
    Diamond_5("5 of Diamonds", 5, "K5.png"),
    Diamond_6("6 of Diamonds", 6, "K6.png"),
    Diamond_7("7 of Diamonds", 7, "K7.png"),
    Diamond_8("8 of Diamonds", 8, "K8.png"),
    Diamond_9("9 of Diamonds", 9, "K9.png"),
    Diamond_10("10 of Diamonds", 10, "K10.png"),
    Heart_2("2 of Hearts", 2, "H2.png"),
    Heart_3("3 of Hearts", 3, "H3.png"),
    Heart_4("4 of Hearts", 4, "H4.png"),
    Heart_5("5 of Hearts", 5, "H5.png"),
    Heart_6("6 of Hearts", 6, "H6.png"),
    Heart_7("7 of Hearts", 7, "H7.png"),
    Heart_8("8 of Hearts", 8, "H8.png"),
    Heart_9("9 of Hearts", 9, "H9.png"),
    Heart_10("10 of Hearts", 10, "H10.png"),
    Spade_2("2 of Spades", 2, "S2.png"),
    Spade_3("3 of Spades", 3, "S3.png"),
    Spade_4("4 of Spades", 4, "S4.png"),
    Spade_5("5 of Spades", 5, "S5.png"),
    Spade_6("6 of Spades", 6, "S6.png"),
    Spade_7("7 of Spades", 7, "S7.png"),
    Spade_8("8 of Spades", 8, "S8.png"),
    Spade_9("9 of Spades", 9, "S9.png"),
    Spade_10("10 of Spades", 10, "S10.png"),
    Club_2("2 of Clubs", 2, "B2.png"),
    Club_3("3 of Clubs", 3, "B3.png"),
    Club_4("4 of Clubs", 4, "B4.png"),
    Club_5("5 of Clubs", 5, "B5.png"),
    Club_6("6 of Clubs", 6, "B6.png"),
    Club_7("7 of Clubs", 7, "B7.png"),
    Club_8("8 of Clubs", 8, "B8.png"),
    Club_9("9 of Clubs", 9, "B9.png"),
    Club_10("10 of Clubs", 10, "B10.png"),
    Diamond_Ace("Ace of Diamonds", 11, "KA.png"),
    Diamond_Queen("Queen of Diamonds", 10, "KK.png"),
    Diamond_King("King of Diamonds", 10, "KK.png"),
    Diamond_Jack("Jack of Diamonds", 10, "KK.png"),
    Heart_Ace("Ace of Hearts", 11, "HA.png"),
    Heart_Queen("Queen of Hearts", 10, "HK.png"),
    Heart_King("King of Hearts", 10, "HK.png"),
    Heart_Jack("Jack of Hearts", 10, "HK.png"),
    Spade_Ace("Ace of Spades", 11, "SA.png"),
    Spade_Queen("Queen of Spades", 10, "SK.png"),
    Spade_King("King of Spades", 10, "SK.png"),
    Spade_Jack("Jack of Spades", 10, "SK.png"),
    Club_Ace("Ace of Clubs", 11, "BA.png"),
    Club_Queen("Queen of Clubs", 10, "BK.png"),
    Club_King("King of Clubs", 10, "BK.png"),
    Club_Jack("Jack of Clubs", 10, "BK.png");
    //vars for cards
    private final String name;
    private final int value;
    private final String ImageName;
    //how cards are defined
    CardTypes(String name, int value, String ImageName){
        this.name = name;
        this.value = value;
        this.ImageName = ImageName;
    }
    //ace logic if getting it is not going to raise you higher than 21 give 11 points else give 1 point
    public int getNewValue(int currentValue){
        if(this.value >= 11 && currentValue > 10){
            return currentValue + 1;
        }
        return currentValue + this.value;
    }
    //get the name of a cards
    public String getName() {
        return this.name;
    }
    //get cards value
    public int getValue() {
        return this.value;
    }
    //get the cards image
    public String getImageName(){
        return this.ImageName;
    }
}
