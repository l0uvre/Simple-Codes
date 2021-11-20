// LC 211 Trie, dfs
import java.util.*;
class WordDictionary {

    private Node root;

    private class Node {
        boolean isWord;
        Map<Character, Node> children;

        Node() {
            isWord = false;
            children = new HashMap<>();
        }
    }

    public WordDictionary() {
        root = new Node();     
    }
    
    public void addWord(String word) {
        Node pointer = root;
        for (char ch : word.toCharArray()) {
            pointer.children.putIfAbsent(ch, new Node()); 
            pointer = pointer.children.get(ch);
        }
        pointer.isWord = true;
        
    }
    
    public boolean search(String word) {

        return search(word, 0, root);
    }
    
    // dfs method to handle "." any letter
    private boolean search(String word, int index, Node node) {
        if (index == word.length()) {
            return node.isWord;
        } else {
            char ch = word.charAt(index);
            if (ch == '.') {
                for (char nextChar : node.children.keySet()) {
                    Node child = node.children.get(nextChar);
                    if (search(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!node.children.containsKey(ch)) {
                    return false;
                } else {
                    return search(word, index + 1, node.children.get(ch));
                }

            }
        }
    }


    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary(); 
        String[] words = new String[] {
            "bad","dad","mad"
        };

        for (String word: words) {
            wd.addWord(word);
        }

        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }
}
