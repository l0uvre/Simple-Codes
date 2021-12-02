/** LC 1047 -- Stack, String ***/
import java.util.*;
public class RemoveAdjacentDuplicates {

    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && ch == stack.peek()) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveAdjacentDuplicates sol = new RemoveAdjacentDuplicates();
        String s = "abbaca";
        System.out.println(sol.removeDuplicates(s));

        s = "azxxzy";
        System.out.println(sol.removeDuplicates(s));
    }
}
