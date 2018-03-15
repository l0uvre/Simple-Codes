
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;


public class CodeBreakP1 {

  static String secretContent = "";
  static Map<String, Integer> wordOccurence = new HashMap<>();

  private static void readFile(String file) throws FileNotFoundException, IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    char[] cbuf = new char[1000];
    int charsRead = 0;

    while ((charsRead = bufferedReader.read(cbuf, 0, 1000)) != -1) {
      secretContent += new String(cbuf, 0, charsRead);
    }

    bufferedReader.close();
  }

  private static void countWords(String toRead) {
    String subString;
    for (int i = 0; i < toRead.length(); i = i + 1) {
      if (i + 3 >= toRead.length()) {
        subString = toRead.substring(i);
      } else {
        subString = toRead.substring(i, i + 3);
      }
////  index
      if (wordOccurence.containsKey(subString)) {
        wordOccurence.put(subString, wordOccurence.get(subString) + 1);
      } else {
        wordOccurence.put(subString, 1);
      }
    }
  }

  private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
    for (Entry<T, E> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  private static int maxOccurence(Map<String, Integer> map) {
    int maxValue = 0;
    if (map.isEmpty()) {
      return 0;
    } else {
      Collection<Integer> values = map.values();
      Iterator<Integer> iterator = values.iterator();
      int currentValue;

      while (iterator.hasNext()) {
        if ((currentValue = iterator.next()) > maxValue) {
          maxValue = currentValue;
        }
      }
      return maxValue;
    }

  }

  public static void main(String[] args) {
    int maxFrequent;
    if (args.length != 1) {
      System.out.println("Usage: java CodeBreakP1 <argument>");
      System.exit(1);
    }

    try {
      readFile(args[0]);
      //System.out.println(secretContent.length());
      countWords(secretContent);
      maxFrequent = maxOccurence(wordOccurence);
      
      System.out.println(getKeyByValue(wordOccurence, maxFrequent));
      // System.out.println(secretContent);

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }

}
