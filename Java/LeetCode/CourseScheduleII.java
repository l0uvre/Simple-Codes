import java.util.*;

class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

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
