import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocomplete(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N), where N is the number of terms
    private void sortDictionary() {

        Arrays.sort(dictionary, new ComparatorByLexicographicOrder());
    }

    // Returns all terms that start with the given prefix, in descending order of
    // weight.
    // Complexity: O(log N + M log M), where M is the number of matching terms

    public Term[] allMatches(String prefix) {

        Term key = new Term(prefix, 0);

        Comparator<Term> comparator = Term.byPrefixOrder(prefix.length());

        int first = RangeBinarySearch.firstIndexOf(dictionary, key, comparator);
        int last = RangeBinarySearch.lastIndexOf(dictionary, key, comparator);

        if (first == -1 || last == -1) {
            return new Term[0];
        }

        int size = last - first + 1;

        Term[] matches = new Term[size];

        for (int i = 0; i < size; i++) {
            matches[i] = dictionary[first + i];
        }

        // Sort matches by descending weight
        Arrays.sort(matches, Term.byReverseWeightOrder());

        return matches;

    }

    // Returns the number of terms that start with the given prefix.
    // Complexity: O(log N)
    public int numberOfMatches(String prefix) {

        Term key = new Term(prefix, 0);

        Comparator<Term> comparator = Term.byPrefixOrder(prefix.length());

        int first = RangeBinarySearch.firstIndexOf(dictionary, key, comparator);
        int last = RangeBinarySearch.lastIndexOf(dictionary, key, comparator);

        if (first == -1 || last == -1)
            return 0;
        return last - first + 1;
    }

}
