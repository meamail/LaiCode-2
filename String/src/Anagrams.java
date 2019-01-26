import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Sliding window
 * Sliding window的题都是Linear 大多数O(n)的
 * (l,r)
 * 左开右开
 * Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.
 * l = "abcbac", s = "ab", return [0, 3]

 * 思路： 用hashMap存s的presence和occurrence
 * window每吃一个char，occurence -1；每吞一个char，occurence +1
 * 当所有的occurrence == 0 时match
 **/

public class Anagrams {
    // Time: O(n)
    // Space: O(s.length)
    public List<Integer> allAnagrams(String s, String input) {

        char[] source = s.toCharArray();

        // record down the anagram statistics
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : source) {
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) + 1);
            } else {
                hm.put(c, 1);
            }
        }
        // sliding windows
        int l = -1;
        int r = 0;
        char[] target = input.toCharArray();

        List<Integer> res = new ArrayList<>();

        while (r < target.length) {

            if (hm.containsKey(target[r])) {
                while (hm.get(target[r]) == 0) {
                    slideLeft(hm, target, l++);
                }
                slideRight(hm, target, r++);
                if (isAnagram(hm)) {
                    res.add(l + 1);
                    slideLeft(hm, target, l++);
                }
            } else {// the char is not in source, left slide ???????

                // leftslide to close the window
                while (l < r - 1) {
                    slideLeft(hm, target, l++);
                }
                // jump over the chars that are not in source
                while (r < target.length && !hm.containsKey(target[r])) { // WRONG: OMITTED r < target.length
                    l++;
                    r++;
                }
                if (r < target.length) {
                    slideRight(hm, target, r++);
                }
            }

        }
        return res;
    }

    private boolean isAnagram(HashMap<Character, Integer> map) {
        for (Integer i : map.values()) {
            if (i != 0) return false;
        }
        return true;
    }

    private void slideLeft(HashMap<Character, Integer> map, char[] str, int l) {
        map.put(str[l + 1], map.get(str[l + 1]) + 1);

    }

    private void slideRight(HashMap<Character, Integer> map, char[] str, int r) {
        map.put(str[r], map.get(str[r]) - 1);

    }


}
