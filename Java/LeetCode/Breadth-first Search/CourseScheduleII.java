import java.util.*;

class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        int index = 0;
        int[] inDegrees = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] pair : prerequisites) {
            inDegrees[pair[0]]++;
            graph.putIfAbsent(pair[1], new HashSet<>());
            graph.get(pair[1]).add(pair[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                res[index] = i;
                index++;
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (graph.get(curr) != null) {
                for (int neighbor : graph.get(curr)) {
                    inDegrees[neighbor]--;
                    if (inDegrees[neighbor] == 0) {
                        res[index] = neighbor;
                        index++;
                        q.offer(neighbor);
                    }
                }
            }
        }
        if (index == numCourses) {
            return res;
        } else {
            return new int[0];
        }
    }
    public static void showArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        CourseScheduleII sol = new CourseScheduleII();
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] schedule = sol.findOrder(4, prerequisites);
        showArray(schedule); 
    }
}
