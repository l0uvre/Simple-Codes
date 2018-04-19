import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * A utility to break a cipher text.
 * 
 * @author Simon
 *
 */
public class CodeBreaker {

  static String secretContent = "";
  static Map<String, Integer> wordOccurence = new HashMap<>();
  static HashMap<Character, Integer> sysmbolOccurence = new HashMap<>();

  static HashMap<Character, Character> trans = new HashMap<>();

  static String[] likelyWords = { "seventeen", "starboard", "thirteen", "fourteen", "eighteen", "nineteen", "fifteen",
      "sixteen", "degree", "fathom", "eleven", "twelve", "twenty", "thirty", "forty", "fifty", "seven", "eight",
      "three", "north", "south", "yard", "foot", "feet", "inch", "mile", "east", "west", "port", "four", "five", "nine",
      "one", "two", "six", "ten" , "and", "that", "have", "with", "for", "you", "from", "there", "come"};

  // No constructor needed, this is to inhibit javadoc constructor
  private CodeBreaker() {

  }

  /**
   * Read the file into static field secretContent.
   * 
   * @param file
   *          The file to open
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void readFile(String file) throws FileNotFoundException, IOException {

    secretContent = "";
    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    char[] cbuf = new char[1000];
    int charsRead = 0;
    while ((charsRead = bufferedReader.read(cbuf, 0, 1000)) != -1) {
      secretContent += new String(cbuf, 0, charsRead);
    }

    bufferedReader.close();
  }

  /***
   * Count the numbers of symbol's occurrence.
   * @param toRead
   *        The string to read
   */
  private static void countSymbol(String toRead) {
    sysmbolOccurence.clear();

    Character currentChar;
    for (int i = 0; i < toRead.length(); i++) {
      currentChar = toRead.charAt(i);
      if (sysmbolOccurence.containsKey(toRead.charAt(i))) {
        sysmbolOccurence.put(currentChar, sysmbolOccurence.get(currentChar) + 1);
      } else {
        sysmbolOccurence.put(currentChar, 1);
      }
    }
  }

  /***
   * Return a list with characters ordered by occurrence.
   * @param map 
   *         The <Character, Integer> HashMap.
   * @return  <Character, Integer> LinkedHashMap 
   */
  private static LinkedHashMap<Character, Integer> sortByValue(HashMap<Character, Integer> map) {
    List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());

    LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
    for (Entry<Character, Integer> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

  /***
   * Return an ArrayList according to a LinkedHashMap
   * @param map
   *        The LinkedHashMap parameter.
   * @return result
   *        ArrayList with characters ordered by occurrence.
   */
  private static ArrayList<Character> getCharArrayByfrequency(LinkedHashMap<Character, Integer> map) {
    ArrayList<Character> result = new ArrayList<>(map.size());
    for (Entry<Character, Integer> entry : map.entrySet()) {
      result.add(entry.getKey());
    }
    Collections.reverse(result);
    return result;

  }

  /***
   * Clean the ArrayList if it contains non alphabetic symbol.
   * @param list
   *        The ArrayList parameter
   * @return
   *        A cleaned ArrayList
   */
  private static ArrayList<Character> cleanList(ArrayList<Character> list) {
    ArrayList<Character> result = new ArrayList<>(26);
    for (Character character : list) {
      if (Character.isLowerCase(character)) {
        result.add(character);
      }
    }
    return result;
  }
  
  
  /***
   * Build a HashMap to use with secrets as keys and alphabetics as values.
   * @param list1
   *        The secret symbol's ArrayList.
   * @param list2
   *        The alphabetic symbol's ArrayList.
   * @param offset
   *        The offset to use if the size don't match
   * @return result
   *        The final HashMap to use.
   */
  private static HashMap<Character, Character> getMapByWordFrequency(ArrayList<Character> list1,
      ArrayList<Character> list2, int offset) {
    HashMap<Character, Character> map = new HashMap<>();
    int lengthDiff = list1.size() > list2.size() ? list1.size() - list2.size() : list2.size() - list1.size();
    int length = list1.size() > list2.size() ? list2.size() : list1.size();

    for (int i = 0; i < length; i++) {
      if (offset != 0) {
        if (offset <= lengthDiff) {
          if (list1.size() > list2.size()) {
            map.put(list1.get(i + offset), list2.get(i));
          } else {
            map.put(list1.get(i), list2.get(i + offset));
          }
        } else {
          map.put(list1.get(i), list2.get(i + lengthDiff));
        }
      } else {
        map.put(list1.get(i), list2.get(i));
      }
    }

    return map;
  }

  /**
   * Count the occurrence times of three continuous symbols in the string.
   * 
   * @param toRead
   *          The string to read.
   */
  public static void countWords(String toRead) {
    wordOccurence.clear();
    String subString;
    for (int i = 0; i < toRead.length(); i = i + 3) {
      if (i + 3 >= toRead.length()) {
        subString = toRead.substring(i);
      } else {
        subString = toRead.substring(i, i + 3);
      }
      //// index
      if (wordOccurence.containsKey(subString)) {
        wordOccurence.put(subString, wordOccurence.get(subString) + 1);
      } else {
        wordOccurence.put(subString, 1);
      }
    }
  }

  /**
   * Given a map and a value, return the corresponding key.
   * 
   * @param map
   *          The Map type variable.
   * @param value
   *          The value in the map.
   * @return <T, E> T The corresponding key.
   */
  public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
    for (Entry<T, E> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  /**
   * Return the maximum value of occurrence time according to the map input.
   * 
   * @param map
   *          The specific map in which string as a key and the occurrence time as
   *          a value.
   * @return the maximum value of the map in keys.
   */
  public static int maxOccurence(Map<String, Integer> map) {
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

  /***
   * Judge whether a pattern is portable in the secretContent.
   * 
   * @param map
   *          The map reflection in which a symbol is reflected to a character
   * @param word
   *          The current wordPattern to test
   * @return true or false If the word is the substring of the secretContent,
   *         return true; else, return false.
   */
  private static boolean isPortablePattern(HashMap<Character, Character> map, String word) {
    if (CodeUtil.decode(secretContent, map).toString().indexOf(CodeUtil.wordPattern(word, map).toString()) < 0) {
      return false;
    }
    return true;
  }

  /***
   * Calculate how many words can be found in the message according to the
   * HashMap.
   * 
   * @param map
   *          The HashMap in which symbols as keys and true characters as values
   * @param words
   *          The likely words array containing words that are probably in the
   *          message.
   * @return the number of likely words can be found in the encrypted message
   *         using the HashMap.
   */
  private static int calMatches(HashMap<Character, Character> map, String[] words) {
    int matches = 0;
    String decoded = CodeUtil.decode(secretContent, map).toString();
    for (int i = 0; i < words.length; i++) {
      int index = 0;
      while (index < decoded.length()) {
        int startIndex = decoded.indexOf(words[i], index);
        if (startIndex == -1) {
          break;
        } else {
          matches += words[i].length();
          index += startIndex + words[i].length();
        }
      }
    }
    return matches;
  }

  /***
   * A recursive method to build the corresponding HashMap.
   * 
   * @param map
   *          the current HashMap to build.
   * @param index
   *          the index of the likely words array.
   * @param words
   *          The likely words array.
   * @return final built HashMap The final HashMap to decrypt the data.
   */
  public static int buildMap(HashMap<Character, Character> map, int index, String[] words) {
    if (index >= words.length) {
      return calMatches(map, words);
    }

    if (isPortablePattern(map, words[index])) {
      HashMap<Character, Character> trans1 = new HashMap<>();
      int matches1, matches2;
      HashMap<Character, Character> trans2 = new HashMap<>();
      trans1.putAll(map);
      trans2.putAll(map);

      StringBuffer currentDecoded = CodeUtil.decode(secretContent, map);
      String likelyWordDecoded = CodeUtil.wordPattern(words[index], map).toString();
      String wordUndecoded;
      int patternIndex;
      boolean isWordOK = true;

      if ((patternIndex = currentDecoded.toString().indexOf(likelyWordDecoded)) >= 0) {
        wordUndecoded = secretContent.substring(patternIndex, patternIndex + words[index].length());


        for (int j = 0; j < wordUndecoded.length(); j++) {
          if (map.containsKey(wordUndecoded.charAt(j)) && map.get(wordUndecoded.charAt(j)) != words[index].charAt(j)) {
            isWordOK = false;
            break;
          }
        }

        if (!isWordOK) {
          return buildMap(map, index + 1, words);
        }

        for (int j = 0; j < wordUndecoded.length(); j++) {
          if (!trans1.containsKey(wordUndecoded.charAt(j))) {
            trans1.put(wordUndecoded.charAt(j), words[index].charAt(j));
          }
        }
      }

      matches1 = buildMap(trans1, index + 1, words);
      matches2 = buildMap(trans2, index + 1, words);

      if (matches1 > matches2) {
        map.putAll(trans1);
        return matches1;
      } else {
        map.putAll(trans2);
        return matches2;
      }

    } else {
      return buildMap(map, index + 1, words);
    }
  }

  /**
   * Main method.
   * 
   * @param args
   */
  public static void main(String[] args) {
    int maxFrequent;
    String threeChars;

    if (args.length != 2) {
      System.out.println("Usage: java CodeBreaker <file1> <file2>");
      System.exit(1);
    }

    try {
      ArrayList<Character> charListSecret;
      ArrayList<Character> charListBook;

      HashMap<Character, Character> trans1, trans2, trans3, trans4, trans5, trans6;

      readFile(args[0]);
      // System.out.println(secretContent.length());
      countWords(secretContent);
      maxFrequent = maxOccurence(wordOccurence);

      threeChars = getKeyByValue(wordOccurence, maxFrequent);

      trans.put(threeChars.charAt(0), 't');
      trans.put(threeChars.charAt(1), 'h');
      trans.put(threeChars.charAt(2), 'e');

      // trans = buildMap(trans, 0, likelyWords);
      buildMap(trans, 0, likelyWords);

      System.out.println(CodeUtil.decode(secretContent, trans));
      System.out.println();

      countSymbol(secretContent);
      charListSecret = getCharArrayByfrequency(sortByValue(sysmbolOccurence));

      readFile(args[1]);
      countSymbol(secretContent);
      charListBook = getCharArrayByfrequency(sortByValue(sysmbolOccurence));
      charListBook = cleanList(charListBook);


      trans1 = getMapByWordFrequency(charListSecret, charListBook, 1);
      trans2 = getMapByWordFrequency(charListSecret, charListBook, 0);

      trans3 = new HashMap<>(trans1);
      trans4 = new HashMap<>(trans2);
      trans5 = new HashMap<>(trans);
      trans6 = new HashMap<>(trans);

      readFile(args[0]);

      System.out.println(CodeUtil.decode(secretContent, trans1));

      System.out.println();

      System.out.println(CodeUtil.decode(secretContent, trans2));

      System.out.println();

      trans1.putAll(trans);
      System.out.println(CodeUtil.decode(secretContent, trans1));

      System.out.println();

      trans2.putAll(trans);
      System.out.println(CodeUtil.decode(secretContent, trans2));

      System.out.println();

      trans5.putAll(trans3);
      System.out.println(CodeUtil.decode(secretContent, trans5));

      System.out.println();

      trans6.putAll(trans4);
      System.out.println(CodeUtil.decode(secretContent, trans6));

      System.out.println();
      
      trans3 = getMapByWordFrequency(charListSecret, charListBook, 2);
      trans4 = getMapByWordFrequency(charListSecret, charListSecret, 3);
      
      System.out.println(CodeUtil.decode(secretContent, trans3));
      System.out.println();
      

     
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }

}
