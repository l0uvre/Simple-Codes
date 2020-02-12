public class StrStr {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }        
        int i = 0, j = 0;
        for (; i < haystack.length(); i++) {
            while (j < needle.length() && i < haystack.length() 
                    && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }

            if (j >= needle.length()) {
                return i - needle.length();
            } else if (i >= haystack.length()) {
                return -1; 
            } else {
                i -= j;
                j = 0;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        StrStr ss = new StrStr();
        System.out.println(ss.strStr("hello", "ll"));
        System.out.println(ss.strStr("aaaaa", "bba"));
        System.out.println(ss.strStr("aaa", "aaaaa"));
        System.out.println(ss.strStr("mississippi", "issip"));
    }

}
