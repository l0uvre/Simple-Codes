import java.util.*;
class ImplementStackusingQueues {
    
    private Queue<Integer> queue;
    private Queue<Integer> aux;

    /** Initialize your data structure here. */
    public ImplementStackusingQueues() {
        queue = new LinkedList<>();
        aux = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        reorder();
        return queue.poll();
    }
    
    private void reorder() {
        if (queue.isEmpty()) {
            queue = new LinkedList<>(aux);
            aux.clear();
        }
        while(queue.size() > 1) {
            aux.offer(queue.poll());
        }
    }
    
    /** Get the top element. */
    public int top() {
        reorder();
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty() && aux.isEmpty();
    }

    public static void main(String[] args) {
        ImplementStackusingQueues myStack = new ImplementStackusingQueues();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        myStack.pop();
        System.out.println(myStack.empty());
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
