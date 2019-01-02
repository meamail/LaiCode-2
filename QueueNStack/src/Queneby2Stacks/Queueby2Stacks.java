package Queneby2Stacks;
import java.util.Deque;
import java.util.LinkedList;

public class Queueby2Stacks {

	public static void main(String args[]) {

		Queueby2Stacks mq = new Queueby2Stacks();
		mq.offer(1);
		mq.offer(2);
		System.out.print(mq.peek());
		System.out.print(mq.poll());
		System.out.print(mq.poll());
		

	}

	// use two stacks

	Deque<Integer> in;
	Deque<Integer> out;

	// constructor
	public Queueby2Stacks() {

		in = new LinkedList<>();
		out = new LinkedList<>();// size = 0

	}
	
	// T/S O(1)/O(1)

	public void offer(int value) {

		in.push(value);

	}

	// use Integer instead of int to include corner case null
	// T/S WCS: O(n) Amortized: O(1) / O(1)
	public Integer poll() {

		if (out.isEmpty()) {
			if (in.isEmpty())
				return null;
			while (!in.isEmpty())
				out.push(in.pop());
		}
		return out.pop();

	}
	
	// T/S WCS: O(n) Amortized: O(1) / O(1)
	public Integer peek() {

		if (out.isEmpty()) {
			// if(in.isEmpty()) return null;
			while (!in.isEmpty())
				out.push(in.pop());
		}
		return out.peek();

	}

	public boolean isEmpty() {

		return in.isEmpty() && out.isEmpty();

	}

	public int size() {

		return in.size() + out.size();

	}

}
