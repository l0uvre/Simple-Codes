import java.util.*;

class WordSearchII {
    class Trie {
        TrieNode root;
        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;
            TrieNode() {
                children = new HashMap<>();
                isWord = false;
            }
        }
        Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode td = root;
            for (char ch : word.toCharArray()) {
                /**if (!td.children.containsKey(ch)) {
                    td.children.put(ch, new TrieNode);
                }**/
                td.children.putIfAbsent(ch, new TrieNode());
                td = td.children.get(ch);
            }
            td.isWord = true;
        }
        
        public boolean isPrefix(String word) {
           TrieNode td = root;
            for (char ch : word.toCharArray()) {
                if (!td.children.containsKey(ch)) {
                    return false;
                }
                td = td.children.get(ch);
            }
            return true;
        }
        
        public boolean isWord(String word) {
           TrieNode td = root;
            for (char ch : word.toCharArray()) {
                if (!td.children.containsKey(ch)) {
                    return false;
                }
                td = td.children.get(ch);
            }
            return td.isWord;
        }
    }
    private static int[] rows = {0, 0, 1, -1};
    private static int[] cols = {1, -1, 0, 0};
    private boolean[][] visited;
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        if (board == null || board.length == 0) {
            return res;
        }
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
            //System.out.println(word + " " + trie.isWord(word) + " " +  trie.isPrefix(word));
        }
        Set<String> aux = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, "" + board[i][j], i, j, trie, aux);
            }
        }
        res = new LinkedList<>(aux);
        return res;
    }
    
    private void dfs(char[][] board, String curr, int row, int col, Trie trie, Set<String> res) {
        if (trie.isWord(curr)) {
            res.add(curr);
            //return;  shouldn't return for a word may be another's prefix.
        }
        
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int newRow = row + rows[i];
            int newCol = col + cols[i];
            if (newRow >= 0 && newRow < board.length && newCol >= 0
               && newCol < board[newRow].length) {
                if (!visited[newRow][newCol] && trie.isPrefix(curr + board[newRow][newCol])) {
                   dfs(board, curr + board[newRow][newCol], newRow, newCol, trie, res); 
                }      
            }
        }
        visited[row][col] = false;
    }

    public static void main(String[] args) {
        WordSearchII sol = new WordSearchII();
        char[][] b1 = {{'a','a'}};
        System.out.println(sol.findWords(b1, new String[]{"a"}));
        char[][] b2 = {{'a','b'},{'a','a'}};
        String[] words = {"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"};
        System.out.println(sol.findWords(b2, words));
    }
}
