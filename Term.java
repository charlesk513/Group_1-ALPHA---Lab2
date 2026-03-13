import java.util.Comparator;

class ComparatorByLexicographicOrder implements Comparator<Term> {
    public int compare(Term a, Term b) {
        return (a.getWord().compareToIgnoreCase(b.getWord()));
    }
}

public class Term {
    private String word;
    private long weight;

    // Initializes a term with a given word and weight.
    public Term(String word, long weight) {
        this.word = word;
        this.weight = weight;
    }

    // Gets the word.
    public String getWord() {
        return word;
    }

    // Gets the weight.
    public long getWeight() {
        return weight;
    }

    // Extracts a prefix from the word.
    public String getPrefix(int len) {

        if (len >= word.length())
            return word;
        else
            return word.substring(0, len);
    }

    // Compares the two terms in case-insensitive lexicographic order.
    public static Comparator<Term> byLexicographicOrder() {

        return (new ComparatorByLexicographicOrder());
    }

    // Comparator that sorts terms by weight in descending order
    public static Comparator<Term> byReverseWeightOrder() {

        return (new Comparator<Term>() {
            public int compare(Term a, Term b) {
                return Long.compare(b.weight, a.weight);
            }
        });
    }

    // Compares the two terms in case-insensitive lexicographic order,
    // but using only the first k characters of each word.
    public static Comparator<Term> byPrefixOrder(int k) {

        return (new Comparator<Term>() {
            public int compare(Term a, Term b) {

                String Ta = a.getPrefix(k);
                String Tb = b.getPrefix(k);

                return Ta.compareTo(Tb);
            }
        });
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the word.
    public String toString() {
        return String.format("%12d    %s", this.getWeight(), this.getWord());
    }

}
