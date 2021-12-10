/** LC 636 -- Stack **/
import java.util.*;
public class ExclusiveTimeOfFunctions {

    /** push the id and timestamp of a function call to a stack if it's 
    * a start, pop the top off if the log is an end;
    * when poping the top off, if the stack is not empty,
    * decrement the res[top(index)]. **/
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<int[]> stack = new LinkedList<>();
        for (String log : logs) {
            String[] strs = log.split(":");
            int id = Integer.valueOf(strs[0]);
            String type = strs[1];
            int timestamp = Integer.valueOf(strs[2]);
            if ("start".equals(type)) {
                stack.push(new int[]{id, timestamp});
            } else {
                int[] funcall = stack.pop();
                /** the start timestamp is the beginning of a timestamp;
                 * the end timestamp is the end of timestamp; 
                 * the length of a timestamp is 1. **/
                int timeUnit = timestamp - funcall[1] + 1;
                res[id] += timeUnit;
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= timeUnit;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        ExclusiveTimeOfFunctions sol = new ExclusiveTimeOfFunctions();

        int n = 1;
        List<String> logs = List.of("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7");
        System.out.println(Arrays.toString(sol.exclusiveTime(n, logs)));

        n = 2;
        logs = List.of("0:start:0","1:start:2","1:end:5","0:end:6");
        System.out.println(Arrays.toString(sol.exclusiveTime(n, logs)));

        n = 2;
        logs = List.of("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7");
        System.out.println(Arrays.toString(sol.exclusiveTime(n, logs)));
    }

}
