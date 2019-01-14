package StringPermutation;

// 透过现象看本质，这个也是多叉树用for-loop
public class StringPermutation {


    public static void main(String args[]) {

        StringPermutation sp = new StringPermutation();
        sp.stringPermutationSwap("abcd");

    }


    public void stringPermutationSwap(String input) {


        stringPermutationSwap(input.toCharArray(), 0);

    }

    // Time complexity O(n!)全排列 Space: O(input.length)

    private void stringPermutationSwap(char[] input, int index) {

        // base case
        if (index == input.length) {

            for (char c : input) {
                System.out.print(c + " ");
            }

            System.out.println();
            return;

        }

        for (int i = index; i < input.length; i++) {
            swap(input, index, i);
            stringPermutationSwap(input, index + 1);
            swap(input, index, i);//不能删！遍历 index - input.length的元素时元素的位置顺序不能变

        }


    }

    private void swap(char[] input, int i, int j) {

        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;


    }


}
