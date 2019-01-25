import java.util.Arrays;

/**
 * Given a string in compressed form, decompress it to the original string.
 * “a1c0b2c4” → “abbcccc”
 */
public class StringDecompression {

    public static void main(String args[]) {


        StringDecompression sd = new StringDecompression();
        sd.decompress("x2y0i0z3");

    }

    // Time : O(repCount) Space: O(res.length)
    public String decompress(String input) {

        if (input.length() == 0) return input;

        char[] str = input.toCharArray();

        // copy from left to right
        // process char that appears 0 / 1 time
        char[] shortStr = decompressSingle(str); // O(n)


        // the char of 0 and 1 occurance has been decompressed
        // now calculate the expansion space needed

        int repCount = 0;// incremental space
        for (int i = 0; i < shortStr.length; i++) {  // O(n)
            if (Character.isDigit(shortStr[i])) {
                repCount += Character.getNumericValue(shortStr[i]) - 2;
                // WORING: WAS - 1 , shall minus the number itself AND the letter itself
            }
        }

        char[] res = Arrays.copyOf(shortStr, shortStr.length + repCount);

        // copy from right to left
        // process char that appears >=2 times
        decompressRep(res, shortStr.length - 1);// O(n)
        return new String(res);

    }

    // left to right, decompress char of 0 and 1
    private char[] decompressSingle(char[] str) {
        int f = 1;
        int s = 0;
        while (f < str.length) {
            if (Character.isDigit(str[f])) {
                if (Character.getNumericValue(str[f]) == 1) {
                    str[s++] = str[f - 1];// only copy the char
                } else if (Character.getNumericValue(str[f]) != 0) {
                    str[s++] = str[f - 1];// copy the char and the number
                    str[s++] = str[f];
                }
                // else the value is 0, do nothing

                f += 2;// move to the next number
            }
        }

        return Arrays.copyOfRange(str, 0, s);
    }

    private void decompressRep(char[] str, int f) {
        int s = str.length - 1;
        while (f >= 0) {
            if (Character.isDigit(str[f])) {
                int count = Character.getNumericValue(str[f]) - 1;
                for (int i = 0; i < count; i++) {
                    str[s--] = str[f - 1];
                }
                f--;
            }
            str[s--] = str[f--];
        }
    }


}
