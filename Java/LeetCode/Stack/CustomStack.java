// LC 1381 Stack
import java.util.Stack;

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */

class CustomStack {
    
    private Stack<Integer> stack;
    private int[] increments; // incr[i]: the value to be added for stack[0 : i)
    private int maxSize;

    public CustomStack(int maxSize) {
        stack = new Stack<>();
        increments = new int[maxSize + 1];
        this.maxSize = maxSize;
    }
    
    public void push(int x) {
        if (stack.size() < maxSize) {
            stack.push(x);
        }
    }
    
    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int i = stack.size();        
        increments[i - 1] += increments[i]; // Accumalating
        int res = stack.pop() + increments[i];
        increments[i] = 0;
        return res ;
    }
    
    public void increment(int k, int val) {
        int i = Math.min(k, stack.size()); 
        if (i >= 0) {
            increments[i] += val;
        }
    }

}

