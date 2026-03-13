
import java.util.Comparator;

public class RangeBinarySearch {

    // Returns the index of the *first* element in terms[] that equals the search
    // key,
    // according to the given comparator, or -1 if there are no matching elements.
    // Complexity: O(log N), where N is the length of the array
    public static int firstIndexOf(Term[] terms, Term key, Comparator<Term> comparator) {

        int lo = 0;
        int hi = terms.length - 1;
        int result = -1;

        while (lo <= hi) {

            int mid = (lo + hi) / 2;

            int same = comparator.compare(key, terms[mid]);

            if (same > 0) {
                lo = mid + 1;
            }

            else if (same < 0) {
                hi = mid - 1;
            }

            else {
                // Match found but we continue searching left
                result = mid;
                hi = mid - 1;
            }
        }
        return result;
    }

    // Returns the index of the *last* element in terms[] that equals the search
    // key,
    // according to the given comparator, or -1 if there are no matching elements.
    // Complexity: O(log N)
    public static int lastIndexOf(Term[] terms, Term key, Comparator<Term> comparator) {

        int lo = 0;
        int hi = terms.length - 1;
        int result = -1;

        while (lo <= hi) {

            int mid = (lo + hi) / 2;

            int same = comparator.compare(key, terms[mid]);

            if (same > 0) {
                lo = mid + 1;
            }

            else if (same < 0) {
                hi = mid - 1;
            }

            else {
                result = mid;
                lo = mid + 1;
            }
        }
        return result;
    }
}
