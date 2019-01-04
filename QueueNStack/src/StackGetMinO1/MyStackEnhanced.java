package StackGetMinO1;

import java.util.Deque;
import java.util.LinkedList;


/**
 * Enhance the stack implementation to support min() operation.
 * min() should return the current minimum value in the stack.
 * If the stack is empty, min() should return -1.
 * s1 6
 * s2[6,1]
 * <p>
 * s1 6 9
 * s2[6,2]
 * <p>
 * s1 6 9 5
 * s2[6,2][5,1]
 * <p>
 * s1 6 9 5 5
 * s2[6,2][5,2]
 * <p>
 * s1 6 9 5
 * s2[6,2][5,1]
 * <p>
 * s1 6 9
 * s2[6,2][5,0] -> s2[6,2]
 */


public class MyStackEnhanced {


    public static void main(String args[]) {

        MyStackEnhanced ms = new MyStackEnhanced();
        ms.push(6);
        System.out.println("min is " + ms.min());
        ms.push(5);
        ms.push(9);
        System.out.println("top is " + ms.top());
        System.out.println("min is " + ms.min());
        System.out.println("min size is " + ms.getMin().size);
        System.out.println("pop " + ms.pop());
        System.out.println("min is " + ms.min());
        System.out.println("min size is " + ms.getMin().size);


    }

    Deque<Integer> s1;
    Deque<Min> s2;
    int count;

    public MyStackEnhanced() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();

    }

    public int pop() {
        if (s1.isEmpty()) return -1;
        if (--s2.peek().size == 0) s2.pop();
        return s1.pop();
    }

    public void push(int element) {
        s1.push(element);

        if (s2.isEmpty()) s2.push(new Min(element, 1));

        else if (element < s2.peek().value) {// update min

            s2.push(new Min(element, 1));

        } else {// min remains
            s2.peek().size++;
        }
    }

    public int top() {
        if (s1.isEmpty()) return -1;
        return s1.peek();
    }

    public int min() {
        if (s2.isEmpty()) return -1;
        return s2.peek().value;
    }

    public Min getMin() {
        if (s2.isEmpty()) return null;
        return s2.peek();

    }

    public static class Min {
        int value;
        int size;// to record how many values to the left of s1

        Min(int value, int size) {
            this.value = value;
            this.size = size;
        }
    }
}
