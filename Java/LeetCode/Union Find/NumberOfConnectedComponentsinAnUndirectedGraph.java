/** LC 323 --- Graph, Union Find  Can also be solve by DFS/BFS **/
public class NumberOfConnectedComponentsinAnUndirectedGraph {

    public int countComponentsUF(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.count();


    }

    class UnionFind {
        private int[] id;
        private int[] size;
        private int N;
        
        public UnionFind(int n) {
            id = new int[n];
            size = new int[n];
            N = n;
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int i) {
            while (id[i] != i) {
                i = id[i];
            }
            return i;
        }
        
        public void union(int i, int j) {
            int id1 = find(i);
            int id2 = find(j);
            if (id1 == id2) {
            return;
            } 
            if (size[id1] > size[id2]) {
                id[id2] = id1;
                size[id1] += size[id2];
            } else {
                id[id1] = id2;
                size[id2] += size[id1];
            }
            N--;
        }

        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }

        public int count() {
            return N;
        }
        
    }

}
