/*** LC 721 -- DFS, BFS, Graph. ***/
import java.util.*;
public class AccountsMerge {

    /*** build the edges between emails, and do the dfs. **/
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /** build the graph with edges between emails. **/
        Map<String, Set<String>> graph = new HashMap<>();
        /** lookup dictionary for emails **/
        Map<String, String> emailToName = new HashMap<>();

        /** build the graph, and the lookup dict for emails **/
        for (List<String> account : accounts) {
            String name = account.get(0);
            /** initialize the nodes **/
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                graph.putIfAbsent(email, new HashSet<>());
                emailToName.put(email, name);
            }
            /** link the edge **/
            for (int i = 1; i < account.size() - 1; i++) {
                String email1 = account.get(i);
                String email2 = account.get(i + 1);
                graph.get(email1).add(email2);
                graph.get(email2).add(email1);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();

        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                /*** emails for one accout **/
                List<String> emails = new ArrayList<>();
                String name = emailToName.get(email);
                bfs(email, graph, visited, emails);
                Collections.sort(emails);
                /** add the name to the head of emails. **/
                emails.add(0, name);
                res.add(emails);
            }
        }
        return res;

    }

    /** bfs, build a list emails for one account. **/
    private void bfs(String email, Map<String, Set<String>> graph,
            Set<String> visited, List<String> emails) {
        Queue<String> q = new LinkedList<>();
        q.offer(email);
        while (!q.isEmpty()) {
            String curr = q.poll();
            /** double check to avoid exploring the same node
             * twice. **/
            if (!visited.contains(curr)) {
                emails.add(curr);
                visited.add(curr);
                for (String next : graph.get(curr)) {
                    if (!visited.contains(next)) {
                        q.offer(next);
                    }
                }
            }
        }
    }

    /** dfs, build a list emails for one account. **/
    private void dfs(String email, Map<String, Set<String>> graph,
            Set<String> visited, List<String> emails) {
        visited.add(email);
        emails.add(email);
        for (String neighbor : graph.get(email)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, emails);
            }
        }
    }

    public static void main(String[] args) {
        AccountsMerge sol = new AccountsMerge(); 
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(List.of("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(List.of("Mary","mary@mail.com"));
        accounts.add(List.of("John","johnnybravo@mail.com"));
        accounts.add(List.of("John","johnsmith@mail.com","john_newyork@mail.com"));
        System.out.println(sol.accountsMerge(accounts));

        accounts = new ArrayList<>();
        accounts.add(List.of("Mary","mary@mail.com"));
        System.out.println(sol.accountsMerge(accounts));
    }
                   
}

