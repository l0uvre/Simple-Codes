/** LC 1522 -- Tree, DFS. **/
import java.util.*;
public class DiameterNaryTree {
    class Node {
        public int val;
        public List<Node> children;

        
        public Node() {
            children = new ArrayList<Node>();
        }
        
        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }
        
        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    /** Like what we did in LC 543,
     * define a recursive function to calculate the top 2 heights of
     * one node's children, keep the top 2;
     * update our res to max(res, max + secondMax + 2);
     * and return max + 1 as the hieght of current node. **/
    int res = 0;
    public int diameter(Node root) {
        res = 0;
        heightFrom(root);
        return res;
        
    }
    private int heightFrom(Node node) {
        if (node == null) {
            return -1;
        } else {
            Queue<Integer> minPQ = new PriorityQueue<Integer>();
            for (Node child : node.children) {
                minPQ.offer(heightFrom(child));
                if (minPQ.size() > 2) {
                    minPQ.poll();
                }
            }
            /** in case the number of its children is less
             * than 2. **/
            while (minPQ.size() < 2) {
                minPQ.offer(-1);
            }
            int secondMax = minPQ.poll();
            int max = minPQ.poll();
            res = Math.max(2 + max + secondMax, res);
            return 1 + max;
        }
    }
}
