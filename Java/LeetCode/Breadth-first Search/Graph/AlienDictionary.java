/*** LC 269 --- Alien Dictionary ***/
import java.util.*;
public class AlienDictionary {

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        } 
        Map<Character, Set<Character>> edges = new HashMap<>();
        Map<Character, Integer> inDegrees = new HashMap<>();
        for (char ch : words[0].toCharArray()) {
            edges.putIfAbsent(ch, new HashSet<>());
            inDegrees.putIfAbsent(ch, 0);
        }

        for (int i = 0; i < words.length - 1; i++) {
            String pre = words[i];     
            String curr = words[i + 1];     
            int j = 0;
            for (j = 0; j < pre.length() && j < curr.length(); 
                    j++) {
                char preCh = pre.charAt(j);
                char currCh = curr.charAt(j);
                edges.putIfAbsent(preCh, new HashSet<>());
                inDegrees.putIfAbsent(preCh, 0);
                /** Build the graph and record the indegrees of nodes **/
                if (preCh != currCh) {
                    /** avoid duplicate edges ***/
                    if (!edges.get(preCh).contains(currCh)) {
                        inDegrees.put(currCh, inDegrees.getOrDefault(currCh, 0) + 1);
                        edges.get(preCh).add(currCh);
                    }
                    break;
                }
            }
            
            /** I guess this test case is invalid as the strings are
             * not sorted **/
            if (j >= curr.length() && j < pre.length()) {
                return "";
            }

            /** add tailing characters to the graph.**/
            for (int k = j; k < curr.length(); k++) {
                edges.putIfAbsent(curr.charAt(k), new HashSet<>());
                inDegrees.putIfAbsent(curr.charAt(k), 0);
            }
        }
        
        Queue<Character> queue = new LinkedList<>();
        /** store the length of the string of unique characters **/
        int length = inDegrees.keySet().size();
        for (char ch : inDegrees.keySet()) {
            if (inDegrees.get(ch) == 0) {
                queue.offer(ch);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            Set<Character> neighbors = edges.get(ch);
            if (neighbors != null) {
                for (char nextCh : neighbors) {
                    inDegrees.put(nextCh, inDegrees.get(nextCh) - 1);
                    if (inDegrees.get(nextCh) == 0) {
                        queue.offer(nextCh);
                    } 
                }
            }
        }

        /** if there is a cycle in the directed graph **/
        if (sb.length() != length) {
            return "";
        } else {
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        AlienDictionary sol = new AlienDictionary(); 
        String[] words = new String[]{
            "wrt","wrf","er","ett","rftt"
        };
        System.out.println(sol.alienOrder(words));

        words = new String[]{"z", "x"};
        System.out.println(sol.alienOrder(words));

        words = new String[]{"x", "z", "x"};
        System.out.println(sol.alienOrder(words));

        words = new String[]{"x"};
        System.out.println(sol.alienOrder(words));

        words = new String[]{"x", "x"};
        System.out.println(sol.alienOrder(words));

        words = new String[]{"wrt", "wrtx"};
        System.out.println(sol.alienOrder(words));
    }
}
