/***  LC 210 --- Graph, BFS, Topological Sort ***/
import java.util.*;

class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        /** Calculate the in degree of courses and build
         * the edges pointing to their successors **/
        for (int[] pre : prerequisites) {
            inDegree[pre[0]] += 1;
            edges.putIfAbsent(pre[1], new HashSet<>());
            edges.get(pre[1]).add(pre[0]);
        }

        int[] res = new int[numCourses];
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int currCourse = q.poll();
            res[index] = currCourse;
            index++;

            if (edges.get(currCourse) != null) {
                for (int nextCourse : edges.get(currCourse)) {
                    inDegree[nextCourse] -= 1;
                    if (inDegree[nextCourse] == 0) {
                        q.offer(nextCourse);
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

    public static void main(String[] args) {
        CourseScheduleII sol = new CourseScheduleII();
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int numCourses = 4;
        int[] schedule = sol.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(schedule)); 

        prerequisites = new int[][]{};
        numCourses = 1;
        schedule = sol.findOrder(numCourses , prerequisites);
        System.out.println(Arrays.toString(schedule)); 

        prerequisites = new int[][]{{0, 1}};
        numCourses = 2;
        schedule = sol.findOrder(numCourses , prerequisites);
        System.out.println(Arrays.toString(schedule)); 

    }
}
