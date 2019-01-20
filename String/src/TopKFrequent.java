import java.util.*;

/**
 * Given a composition with different kinds of words,
 * return a list of the top K most frequent words in the composition.
 * a list of words ordered from most frequent one to least frequent one
 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
 * Time : O(n) for-loop + O(klogk) offer + O(n-klogk) poll + O(klogk) poll
 * Time : O(nlogk)
 * Space: O(n) hashmap + O(k) minheap = O(n)
 */

public class TopKFrequent {


    //
    public String[] topK(String[] combo, int k) {

        // corner case
        if (combo.length == 0) return new String[0];

        //use a hash table to record word frequency
        Map<String, Integer> hashmap = new HashMap<String, Integer>();
        for (String s : combo) {


            if (!hashmap.containsKey(s)) {

                hashmap.put(s, 1);

            } else {
                int freq = hashmap.get(s);
                hashmap.put(s, ++freq);

            }

        }


        //use minHeap to get top k ele

        // No need to pre-process k >= arr.length

        // use a minHeap to store ele

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,


                // compare by getValue in natural order
                // if in reverse order, use lambda expression((obj o1, obj o2) -> {return ...})
                Comparator.comparing(Map.Entry<String, Integer>::getValue)
        );

        for (Map.Entry<String, Integer> entry : hashmap.entrySet()) {// IMPORTANT

            if (minHeap.size() < k) {

                minHeap.offer(entry);
            } else {

                int value = minHeap.peek().getValue();
                if (entry.getValue() > value) {
                    minHeap.poll();
                    minHeap.offer(entry);

                }
            }

        }

        String res[] = new String[minHeap.size()];

        for (int i = minHeap.size() - 1; i >= 0; i--) {

            res[i] = minHeap.poll().getKey();
        }
        return res;

    }


}
