import java.util.ArrayList;
import java.util.List;

/**
 * Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
 * In each of the two sorted arrays, there could be duplicate numbers.
 * Both two arrays are not null.
 * A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
 * <p>
 * READ THIS!
 * two-pointer is enough
 * NO need to use HashMap because array is sorted(ordered) <- there is no permutation
 * when order does not matter but only # of occurrence matter, use HashSet/Map
 **/

/**
 READ THIS!
 two-pointer is enough
 NO need to use HashMap because array is sorted(ordered) <- there is no permutation
 when order does not matter but only # of occurrence matter, use HashSet/Map
 **/

/**
 Input: java.util.Arrays.asList(1,1,2,4,4,6,6,6), java.util.Arrays.asList(1,3,5,5,6,6)
 expected:<[1, 6, 6]> but was:<[1]>
 **/

public class CommonInteger {

    // Time: O(min(m,n)) Space: O(1)
    public List<Integer> common(List<Integer> A, List<Integer> B) {
        int l1 = 0;
        int l2 = 0;
        List<Integer> res = new ArrayList<>();
        while (l1 < A.size() && l2 < B.size()) {
            if (A.get(l1) < B.get(l2)) {
                while (l1 < A.size() && A.get(l1) < B.get(l2)) l1++;// WRONG: WAS A.get(l1) != B.get(l2)
                if (l1 < A.size() && A.get(l1) == B.get(l2)) {
                    res.add(B.get(l2));
                    l1++;
                    l2++;
                }
            } else if (A.get(l1) > B.get(l2)) {
                while (l2 < B.size() && A.get(l1) > B.get(l2)) l2++;
                if (l2 < B.size() && A.get(l1) == B.get(l2)) {
                    res.add(B.get(l2));
                    l1++;
                    l2++;
                }
            } else {
                res.add(B.get(l2));
                l1++;
                l2++;
            }
        }
        return res;
    }
}
