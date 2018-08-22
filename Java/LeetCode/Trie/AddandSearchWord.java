class AddandSearchWord {
    TrieNode root;
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    /** Initialize your data structure here. */
    public AddandSearchWord() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            if (p.children[word.charAt(i) - 'a'] == null) {
                p.children[word.charAt(i) - 'a'] = new TrieNode();
                
            } 
            p = p.children[word.charAt(i) - 'a'];
        }
        p.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
       return dfs(word.toCharArray(), 0, root);
    }
    
    private boolean dfs(char[] word, int index, TrieNode root) {
        if (index == word.length) {
            return root.isWord; 
        } 
        
        if (word[index] != '.') {
            TrieNode next = root.children[word[index] - 'a'];
            return next != null && dfs(word, index + 1, next);
        } else {
            for (int i = 0; i < root.children.length; i++) {
                TrieNode next = root.children[i];
                if (next != null) {
                    if (dfs(word, index + 1, next)) {
                        return true;
                    }
                } 
            }
            return false;
        }
        
    }
    public static void main(String[] args) {
        AddandSearchWord  sol = new AddandSearchWord();
        sol.addWord("bad");
        sol.addWord("dad");
        sol.addWord("mad");
        System.out.println(sol.search("pad"));
        System.out.println(sol.search("dad"));
        System.out.println(sol.search(".ad"));
        System.out.println(sol.search("b.."));
        System.out.println(sol.search(".."));
        System.out.println(sol.search("..."));
    }
}


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
