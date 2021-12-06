public class LCAofBinaryTreeIII {
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    /** easier to implement iteratively 
     * since we can access the parent node. **/
    public Node lowestCommonAncestor(Node p, Node q) {
        /** store the path from p to root **/
        Set<Node> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = p.parent;
        }

        /** the first node on the path from p to root
         * included in the path from q to root
         *  is the lowest common ancestor **/
        while (q != null) {
            if (set.contains(q)) {
                return q;
            } else {
                q = q.parent;
            }
        }
        return null;
    }
}
