package CoinCombination;


//eg. k = 99 cents
//coin values: 25 10 5 1 cent


public class CoinCombination {

    public static void main(String args[]) {

        CoinCombination cc = new CoinCombination();
        int[] coin = {25, 10, 5, 1};
        cc.getCombo(99, coin);

    }

    public void getCombo(int amount, int[] coin) {


        int[] sol = new int[coin.length];
        getCombo(99, coin, 0, sol);

    }

    // Time complexity : O(max # of branches ^ max # of levels ) = O(99^4)
    // Space complexity : O(level) = O(4)
    private void getCombo(int balance, int[] coin, int level, int[] sol) {

        // base case
        if (level == coin.length - 1) {

            sol[level] = balance;
            for (int i : sol) {

                System.out.print(i + " ");
            }
            System.out.println();
            return;

        }

        for (int i = 0; i * coin[level] <= balance; i++) {

            sol[level] = i;
            getCombo(balance - i * coin[level], coin, level + 1, sol);

        }


    }


}
