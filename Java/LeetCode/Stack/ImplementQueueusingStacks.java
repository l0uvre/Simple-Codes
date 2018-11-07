import java.util.*;

public class ImplementQueueusingStacks {

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        System.out.println(q.pop());
        q.push(3);
        q.push(4);
        System.out.println(q.pop());
        System.out.println(q.peek());

    }

}
class MyQueue {
    
    private Stack<Integer> inOrder;
    private Stack<Integer> stack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inOrder = new Stack<>();
        stack = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        reorder();
        return inOrder.pop();
    }
    
    private void reorder() {
        if (stack.isEmpty() || !inOrder.isEmpty()) {
            return;
        }
        while (!stack.isEmpty()) {
            inOrder.push(stack.pop());
        }
    }
    
    /** Get the front element. */
    public int peek() {
        reorder();
        return inOrder.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty() && inOrder.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
