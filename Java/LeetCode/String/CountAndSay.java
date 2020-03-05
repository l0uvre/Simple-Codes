// Leetcode 38 String
//

public class CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else {
            String prev = countAndSay(n - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < prev.length(); i++) {
                char ch = prev.charAt(i);
                int start = i;
                while (i < prev.length() && prev.charAt(i) == ch) {
                    i++;
                }
                sb.append(i - start);
                sb.append(ch);
                i--;
            }
            return sb.toString();
        }    
    }

    public static void main(String[] args) {
        CountAndSay cas = new CountAndSay();
        System.out.println(cas.countAndSay(1));
        System.out.println(cas.countAndSay(4));
        System.out.println(cas.countAndSay(5));

    }


}
