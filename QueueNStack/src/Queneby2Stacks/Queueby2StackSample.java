package Queneby2Stacks;
import java.util.Deque;
import java.util.LinkedList;

public class Queueby2StackSample {

	public static void main(String args[]) {

		Queueby2StackSample mq = new Queueby2StackSample();
		mq.offer(1);
		mq.offer(2);
		System.out.print(mq.peek());
		System.out.print(mq.poll());
		System.out.print(mq.poll());
		System.out.print(mq.poll());
		

	}

	// use two stacks

	Deque<Integer> in;
	Deque<Integer> out;

	// constructor
	public Queueby2StackSample() {

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

		shuffleIfNecessary();
		if(out.isEmpty()) return null;
		return out.pop();

	}
	
	// T/S WCS: O(n) Amortized: O(1) / O(1)
	public Integer peek() {

		shuffleIfNecessary();
		return out.peek();

	}
	
	// T/S WCS: O(n) Amortized: O(1) / O(1)
	public void shuffleIfNecessary() {
		
		if (out.isEmpty()) {
			while (!in.isEmpty())
				out.push(in.pop());
		}
		
		
	}

	public boolean isEmpty() {

		return in.isEmpty() && out.isEmpty();

	}

	public int size() {

		return in.size() + out.size();

	}

}
