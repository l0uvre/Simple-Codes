/** LC 394 -- Stack, String. **/
import java.util.Deque;
import java.util.LinkedList;
public class DecodeString {
    /** Use two stacks, one for digits, the other for characters. **/
    public String decodeString(String s) {
        Deque<Integer> inStack = new LinkedList<>();
        Deque<StringBuilder> strStack = new LinkedList<>();
        int num = 0;
        StringBuilder curr = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {
                inStack.push(num);
                num = 0;
                strStack.push(curr);
                curr = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder sub = curr;
                curr = strStack.pop();
                int k = inStack.pop();
                for (int i = 0; i < k; i++) {
                    curr.append(sub);
                }
            } else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }

    public static void main(String[] args) {
        DecodeString sol = new DecodeString();
        String s = "3[a]2[bc]";
        System.out.println(sol.decodeString(s));

        s = "3[a2[c]]";
        System.out.println(sol.decodeString(s));

        s = "2[abc]3[cd]ef";
        System.out.println(sol.decodeString(s));
    }
}
