public class ILoveYahoo {

    public static void main(String[] args) {

        ILoveYahoo il = new ILoveYahoo();
        System.out.print(il.reverseWords("an apple"));


    }

    // Time: O(n) Space O(1)
    public String reverseWords(String input) {

        if (input == null || input.length() == 0) return input;
        char[] str = input.toCharArray();
        reverse(str, 0, str.length - 1);

        int left = 0;
        for (int i = 1; i <= str.length; i++) {
            if (i == str.length || str[i] == ' ') {//WRONG: i == str.length must be at the front
                int right = i - 1;
                reverse(str, left, right);
                left = i + 1;
            }
        }
        return new String(str);


    }

    private void reverse(char[] str, int left, int right) {

        for (int i = left; i <= left + (right - left) / 2; i++) {  // WRONG, WAS i <= (right - left) /2
            swap(str, i, right + left - i); // WRONG, WAS right - left - i
        }
    }

    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
