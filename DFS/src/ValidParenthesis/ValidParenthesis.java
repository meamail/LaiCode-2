package ValidParenthesis;

public class ValidParenthesis {

    public static void main(String[] args) {

        ValidParenthesis vp = new ValidParenthesis();
        vp.printValidPermulation(3);

    }

    public void printValidPermulation(int pairs) {

        StringBuilder sol = new StringBuilder();

        printValidPermulation(pairs, 0, 0, sol);


    }

    // Depth: 2*pairs, Time complexity: O(2^2*pairs) = O(4^pairs) Space: O(2*pairs)
    // DFS 深度优先时，最深的深度即为container所需的最大空间，i.e. Space Complexity

    private void printValidPermulation(int pairs, int left, int right, StringBuilder sol) {
        // base case
        if (left == pairs && right == pairs) {

            System.out.println(sol);
            return;

        }

        // trimmed binary recursion tree

        if (left < pairs) {
            sol.append('{');
            printValidPermulation(pairs, left + 1, right, sol);
            sol.deleteCharAt(sol.length() - 1);
        }

        if (right < left) {

            sol.append('}');
            printValidPermulation(pairs, left, right + 1, sol);
            sol.deleteCharAt(sol.length() - 1);

        }

    }

}
