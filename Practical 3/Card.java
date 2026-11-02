import java.util.Objects;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(rank, card.rank) && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public static void main(String[] args) {
        System.out.println("=== Duplicate Card Check Demo ===");

        Card[] deck = new Card[5];
        Card[] incomingCards = {
            new Card("King", "Diamonds"),
            new Card("Ace", "Spades"),
            new Card("10", "Hearts"),
            new Card("Ace", "Spades"),
            new Card("Jack", "Clubs")
        };

        int count = 0;
        for (Card nextCard : incomingCards) {
            System.out.println("Processing: " + nextCard);

            boolean duplicate = false;
            for (int i = 0; i < count; i++) {
                if (deck[i].equals(nextCard)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("Duplicate found: " + nextCard);
            } else {
                deck[count++] = nextCard;
            }
        }
    }
}
