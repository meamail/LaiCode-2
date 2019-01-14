package GetSubsets;

public class GetSubsets {

    public static void main(String args[]) {


        GetSubsets gt = new GetSubsets();
        gt.getSubsets("abc");


    }

    public void getSubsets(String input) {

        // sol is a container

        StringBuilder sol = new StringBuilder();
        getSubsets(input.toCharArray(), 0, sol);

    }

    //Time O(2^ input.length) Space: O(input.length)

    private void getSubsets(char[] input, int index, StringBuilder sol) {

        // base case
        if (index == input.length) {

            System.out.println(sol);
            return;

        }

        // binary recursion tree
        // branch 1: include input[index]
        sol.append(input[index]);
        getSubsets(input, index + 1, sol);

        // branch 2: do not include input[index]
        sol.deleteCharAt(sol.length() - 1);
        getSubsets(input, index + 1, sol);

    }
}
