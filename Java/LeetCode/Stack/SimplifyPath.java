/*** LC 71 --- Stack, String ***/
import java.util.*;
public class SimplifyPath {

    
    /** use string.split(). **/
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String str : path.split("/+")) {
           if ("..".equals(str)) {
               if (!stack.isEmpty()) {
                   stack.pop();
               }
           } else if (!"".equals(str) && !".".equals(str)) {
               stack.push(str);
           }
        }
        if (stack.isEmpty()) {
           return "/";
        } else {
           StringBuilder sb = new StringBuilder();
           while (!stack.isEmpty()) {
               sb.append("/").append(stack.pollLast());
           }
           return sb.toString();
        }
    }

    /** using stack to store the directories and
     * file names; when the name is '.', ignore it;
     * when the name is '..', pop from the stack. **/
    public String simplifyPath(String path) {
        if (path == null || path.length() <= 1) {
            return path;
        } else {
            Deque<String> stack = new LinkedList<>();
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) == '/') {
                    continue;
                }
                int j = i;
                while (j < path.length() && path.charAt(j) != '/') {
                    j++;
                }
                String name = path.substring(i, j);
                /** adjust the index **/
                i = j - 1;
                if (".".equals(name)) {
                    continue;
                } else if ("..".equals(name)) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    } 
                } else {
                    stack.push(name);
                }
            }

            if (stack.isEmpty()) {
                return "/";
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append('/').append(stack.pollLast());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        SimplifyPath sol = new SimplifyPath();  
        String path = "/home/";
        System.out.println(sol.simplifyPath(path));

        path = "/../";
        System.out.println(sol.simplifyPath(path));
        
        path = "/home//foo/";
        System.out.println(sol.simplifyPath(path));
        
        path = "/a/./b/../../c/";
        System.out.println(sol.simplifyPath(path));
    }
}
