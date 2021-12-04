/*** LC 1209 --- Stack, String ****/
import java.util.*;
public class RemoveAdjacentDuplicatesII {
    private class AdjLetter {
        private char ch;
        private int freq;
        AdjLetter (char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String removeDuplicates(String s, int k) {
        Deque<AdjLetter> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(new AdjLetter(ch, 1));
            } else {
                if (ch == stack.peek().ch) {
                    AdjLetter adj = stack.pop();
                    /*** if the length of the duplicates is less
                     * than k, we continue ***/
                    if (adj.freq + 1 < k) {
                        stack.push(new AdjLetter(adj.ch, adj.freq + 1));
                    }
                } else {
                    stack.push(new AdjLetter(ch, 1));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (AdjLetter adj : stack) {
            char ch = adj.ch;
            int freq = adj.freq;
            sb.append(String.valueOf(ch).repeat(freq));
        }
        return sb.reverse().toString();
        
    }

    public static void main(String[] args) {
        RemoveAdjacentDuplicatesII sol = new RemoveAdjacentDuplicatesII(); 
        String s = "abcd";
        int k = 2;
        System.out.println(sol.removeDuplicates(s, k));

        s = "deeedbbcccbdaa";
        k = 3;
        System.out.println(sol.removeDuplicates(s, k));

    }
}
