/** LC 937 -- String, Sorting. **/
import java.util.*;

public class ReorderLogFiles {
    /** Write a custom comparator for sorting;
     * Notes: Arrays.sort is stable in Java. **/
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            /** apply the pattern only 1 time. **/
            String[] split1 = a.split(" ", 2);
            String[] split2 = b.split(" ", 2);

            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (isDigit1 && isDigit2) {
                return 0;
            } else if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp == 0) {
                    return split1[0].compareTo(split2[0]);
                } else {
                    return cmp;
                }
            } else if (!isDigit1 && isDigit2) {
                return -1;
            } else {
                return 1;
            }
        });
        return logs;
    }

    public static void main(String[] args) {
        ReorderLogFiles sol = new ReorderLogFiles(); 
        String[] logs = new String[] {
            "dig1 8 1 5 1",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero"
        };
        System.out.println(Arrays.toString(sol.reorderLogFiles(logs)));

        logs = new String[] {
            "a1 9 2 3 1",
                "g1 act car",
                "zo4 4 7",
                "ab1 off key dog",
                "a8 act zoo"
        };
        System.out.println(Arrays.toString(sol.reorderLogFiles(logs)));
    }

}
