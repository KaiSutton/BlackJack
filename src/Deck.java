import acm.util.RandomGenerator;
import com.prog2.blackjack.Card;


public class Deck {

    private Card[] cards;
    private int top;

    public Deck(){
        // initialize the array
        cards = new Card[52];
        // use enhance for! to initialize all the cards
        // For each Suit suit in Suit.values()
        int pos = 0;
        for (Card.Suit suit : Card.Suit.values()){
            for (Card.Face face : Card.Face.values()){
                Card card = new Card(face, suit); // make a new card
                cards[pos++] =  card; // put the card in the deck
            }
        }
        // call shuffle
        shuffle();
    }

    public void shuffle(){
        //loop over cards array
        for (int i = 0; i <= cards.length-1; i++) {
            // get a random number
            int randomPos = RandomGenerator.getInstance().nextInt(0, cards.length - 1);

            // store the card that is in i
            Card temp = cards[i];

            // get the card at a random number position and put it in the i pos
            cards[i] = cards[randomPos];
            // put the stored card back into the random number pos
            cards [randomPos] = temp;
        }
        top = 0;
    }

    public Card deal(){
        // what if the top is at the bottom?
        if (top == cards.length){
            shuffle();
        }
        return cards[top++];

    }

}
