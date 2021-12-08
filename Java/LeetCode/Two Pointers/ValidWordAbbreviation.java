/** LC 408 -- Two Pointers, String **/
public class ValidWordAbbreviation {

    /** use two pointers, jump the number in abbr for the pointer in word
     * */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < abbr.length()) {
            int count = 0;
            /*** first, consume the digits in abbr if any**/
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                /** String.valueOf is the wrong way to parse character as int **/
                int value = Character.getNumericValue(abbr.charAt(j));

                /** leading zeros are invalid **/
                if (count == 0 && value == 0) {
                    return false;
                } else {
                    count = count * 10 + value;
                    j++;
                }
            }


            if (count != 0) {
                i += count;
            } else if (word.charAt(i) != abbr.charAt(j)) {
                return false;
            } else {
                i++;
                j++;
            }
        }

        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        String word = "internationalization";
        String abbr = "i12iz4n";
        ValidWordAbbreviation sol = new ValidWordAbbreviation(); 
        System.out.println(sol.validWordAbbreviation(word, abbr));

        word = "apple";
        abbr = "a2e";
        System.out.println(sol.validWordAbbreviation(word, abbr));
    }
}
