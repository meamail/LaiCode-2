package StringPermutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StringPermutationDup {
    public List<String> permutations(String set) {
        List<String> res = new ArrayList<>();
        permutation(set.toCharArray(), 0, res);
        return res;
    }

    // Time : < O(m!)
    // Space: O(m) (DFS)
    private void permutation(char[] str, int index, List<String> res) {
        // base case
        if (index == str.length - 1) {
            res.add(new String(str));
            return;
        }

        // de-duplicate:  use hashSet to store added char
        HashSet<Character> hs = new HashSet<>();
        for (int i = index; i < str.length; i++) {
            if (hs.add(str[i])) {
                swap(str, index, i);
                permutation(str, index + 1, res);
                swap(str, index, i);
            }
        }
    }

    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
