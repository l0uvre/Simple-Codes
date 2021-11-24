// LC 1109 --- Array, Prefix Sum
import java.util.*;
public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];
        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];

            answer[first - 1] += seats;
            if (last < n) {
                answer[last] -= seats;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            answer[i + 1] += answer[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        CorporateFlightBookings sol = new CorporateFlightBookings(); 
        int[][] bookings = new int[][]{
                {1,2,10},
                {2,3,20},
                {2,5,25}
        };
        int n = 5;
        
        System.out.println(Arrays.toString(sol.corpFlightBookings(bookings, n)));

    }
}
