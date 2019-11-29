// Leetcode 295 
import java.util.*;

class MedianFinder {
    Queue<Integer> smaller;
    Queue<Integer> larger; 
    /** initialize your data structure here. */
    public MedianFinder() {
        smaller = new PriorityQueue<>(); 
        larger = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    }
    
    public void addNum(int num) {
        smaller.offer(num);
        larger.offer(smaller.poll());
        if (larger.size() > smaller.size() + 1) {
            smaller.offer(larger.poll());
        }
    }
    
    public double findMedian() {
        if (larger.size() == smaller.size()) {
            return (double) (larger.peek() + smaller.peek()) / 2;
        } else {
            return larger.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
}
