/** LC 418 --- String. **/

public class SentenceScreenFitting {
    /** Brute Force approach, as the optimal one is pretty tricky and not of
    a common pattern;
    Use a counter to walk through the screen and calculate the number of sentence fitted. */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String sen = String.join(" ", sentence) + " ";
        int count = 0;
        int len = sen.length();

        for (int i = 0; i < rows; i++) {
            count += cols;
            if (sen.charAt(count % len) == ' ') {
                count++;
            } else {
                while (count > 0 && sen.charAt((count - 1) % len) != ' ') {
                    count--;
                }
            }
        }
        return count / len;
    }
}
