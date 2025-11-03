import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> card;

    private static final String[] SUITS = {"♥", "♦", "♣", "♠"};
    private static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public Deck(){
        card = new ArrayList<>();
        for(String suit : SUITS) {
            for(String value: VALUES){
                card.add(new Card(suit, value));
            }

        }
    }

    public void shuffle(){
        Collections.shuffle(card);
    }

    public Card deal(){
        if(card.isEmpty()){
            return null;
        }
        
        return card.remove(0);
    }

    public int size(){
        return card.size();
    }
}
