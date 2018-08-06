class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || numCourses <= 0) {
            return true;
        }
        int[] inDegree = new int[numCourses]; // in degree arrays
        Map<Integer, Set<Integer>> graph = new HashMap<>(); // the graph.
        
        /***Read the input.**/
        for (int[] pair : prerequisites) {
            if (!graph.containsKey(pair[0])) {
                graph.put(pair[0], new HashSet<>());
            }
            inDegree[pair[1]]++;
            graph.get(pair[0]).add(pair[1]);
        }
        
        /** For BFS ***/
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (q.size() != 0) {
            int course = q.poll();
            numCourses--;
            if (graph.get(course) != null) {
                for (int next : graph.get(course)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        if (numCourses == 0) {
            return true;
        }
        return false;
    }
}
