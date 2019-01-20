import java.util.*;

public class TopKFrequent {
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

        Map.Entry<String, Integer>[] arr = hashmap.entrySet().toArray(new Map.Entry[0]);

        //use minHeap to get top k ele


        if (k >= arr.length) {// return the whole array

            // return the whole array

            Arrays.sort(arr, (m1, m2) -> {

                if (m1.getValue() == m2.getValue()) return 0;
                return (int) m1.getValue() < (int) m2.getValue() ? 1 : -1;
            });


            String[] res = new String[arr.length];
            for (int i = 0; i < arr.length; i++) {

                res[i] = arr[i].getKey();

            }

            return res;
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,
                (Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) -> {

                    if (m1.getValue() == m2.getValue()) return 0;
                    return m2.getValue() < m1.getValue() ? -1 : 1;
                });

        for (int i = 0; i < arr.length; i++) {

            if (i < k) {

                minHeap.offer(arr[i]);
            } else {

                int value = minHeap.peek().getValue();
                if (arr[i].getValue() > value) {
                    minHeap.poll();
                    minHeap.offer(arr[i]);

                }
            }

        }

        String res[] = new String[k];

        for (int i = k - 1; i >= 0; i--) {

            res[i] = minHeap.poll().getKey();
        }
        return res;

    }


}
