import java.util.*;

class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        if (words == null || words.length == 0 || maxWidth < 1) {
            return res;
        }
        List<String> curr = new ArrayList<>();
        int numOfLetters = 0;
        for (String word : words) {
            if (curr.size() + numOfLetters + word.length() > maxWidth) {
                int numOfSpaces = maxWidth - numOfLetters;
                for (int i = 0; i < numOfSpaces; i++) {
                    int blockOfSpace = curr.size() - 1;
                    if (blockOfSpace == 0) {
                        blockOfSpace = 1;
                    }
                    curr.set(i % blockOfSpace, curr.get(i % blockOfSpace) + " "); // Round Robin idea!!!
                }
                
                res.add(String.join("", curr));
                curr.clear();
                numOfLetters = 0;
            } 
            curr.add(word);
            numOfLetters += word.length();
        }
        
        StringBuilder lastLine = new StringBuilder(String.join(" ", curr));
        for (int i = 0; i <= maxWidth - numOfLetters - curr.size(); i++) {
            lastLine.append(" ");
        }
        res.add(lastLine.toString());
        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        TextJustification sol = new TextJustification();
        System.out.println(sol.fullJustify(words, maxWidth));
        words = new String[]{"What","must","be","acknowledgment","shall","be"};
        maxWidth = 16;
        System.out.println(sol.fullJustify(words, maxWidth));
        words = new String[]{"Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"};
        maxWidth = 20;
        System.out.println(sol.fullJustify(words, maxWidth));
    }
}
