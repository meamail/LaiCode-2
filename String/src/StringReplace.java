import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringReplace {


    public static void main(String[] args) {

        StringReplace sr = new StringReplace();
        System.out.print(sr.replace("unohmnonononoh", "no", "b"));


    }

    // Time : O(n) WCS: O(n*m)
    // Space: O(1) WCS: O(n) loc
    public String replace(String input, String source, String target) {
        // source <= target, left to right
        char[] str = input.toCharArray();

        if (source.length() >= target.length()) {
            int right = replaceFromLeft(str, source, target);
            return new String(Arrays.copyOfRange(str, 0, right + 1));
        }

        // source <= target, right to left
        else {

            // get the location of each source occurance
            List<Integer> loc = searchSource(str, source, target);

            int additionalSpace = loc.size() * (target.length() - source.length());
            //expand char[] str
            char[] res = Arrays.copyOf(str, str.length + additionalSpace);
            int right = str.length - 1;

            int left = replaceFromRight(res, right, source, target, loc);

            return new String(Arrays.copyOfRange(res, left, res.length));
        }
    }

    public int replaceFromLeft(char[] str, String source, String target) {
        int s = 0;
        int f = 0;

        while (f <= str.length - 1) {

            if (equalSubstring(str, f, source)) {
                // replace
                for (int i = 0; i < target.length(); i++) {
                    str[i + s] = target.charAt(i);
                }
                s = s + target.length();
                f = f + source.length();

            } else {
                //copy
                str[s++] = str[f++];
            }
        }
        return s - 1;
    }

    // get the list of matching index
    private List<Integer> searchSource(char[] str, String source, String target) {

/**
 * Input: "aaa", "aa", "bbb"
 * expected:<[bbba]> but was:<[abbb]>
 * 这里不可以从右往左记index，得从左往右记index，每个index再加 source.length()-1
 * 否则替换的不是 a1a2 而是 a2a3
 */

        List<Integer> loc = new ArrayList<>();
        int f = 0;
        while (f < str.length) {
            if (equalSubstring(str, f, source)) {
                loc.add(f + source.length() - 1);
                f = f + source.length();
            } else {
                f++;
            }
        }

        return loc;
    }

    private int replaceFromRight(char[] str, int rightStart, String source, String target, List<Integer> loc) {
        int s = str.length - 1;
        int f = rightStart;
        int index = loc.size() - 1;
        while (f >= 0) {

            if (index >= 0 && f == loc.get(index)) {
                for (int i = 0; i < target.length(); i++) {
                    str[s - (target.length() - 1) + i] = target.charAt(i);
                }
                s = s - target.length();
                f = f - source.length();
                index--;
            } else {
                str[s--] = str[f--];
            }
        }
        return s + 1;
    }

    // check whether str matches source from fromIndex
    private boolean equalSubstring(char[] str, int fromIndex, String source) {

        for (int i = 0; i < source.length(); i++) {

            if (fromIndex + i >= str.length || str[fromIndex + i] != source.charAt(i)) {

                return false;
            }
        }
        return true;

    }
}
