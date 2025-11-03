public class Card {

    private final String suit;
    private final String value;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public String toString(){
        return value + " " + suit;
    }
}
