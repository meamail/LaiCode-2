package StackGetMinO1;

import java.util.Deque;
import java.util.LinkedList;


//T/S O(1)/<<<<O(n)

public class MyStack {
	Deque<Integer> stack;
	Deque<Integer> min;

	public static void main(String args[]) {

		MyStack ms = new MyStack();
		ms.push(7);
		ms.push(9);
		ms.push(6);
		ms.push(6);
		ms.push(5);
		ms.push(8);
		ms.push(5);
		ms.push(3);
		ms.push(7);
		ms.push(10);
		ms.push(3);
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		System.out.println("min is " + ms.min());
		System.out.println("pop " + ms.pop());
		

	}

	public MyStack(){
		stack = new LinkedList<>();
		min = new LinkedList<>();
	}

	
	public void push(int value) {
		stack.push(value);
		if (min.isEmpty())
			min.push(value);
		// push the value to min if this value is no less than existing one
		else if (value <= min.peek())
			min.push(value);
	}

	public Integer pop() {
		if (stack.isEmpty())
			return null;
		if (stack.peek() == min.peek())
			min.pop();
		return stack.pop();
	}

	public Integer peek() {
		return stack.peek();
	}

	public Integer min() {
		return min.peek();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public Integer size() {
		return stack.size();
	}
}
