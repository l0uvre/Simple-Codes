class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        for (right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (!set.contains(ch)) {
                set.add(ch);
                res = Math.max(right - left + 1, res);
            } else {
                while (set.contains(ch)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(ch);
            }
        }
        return res;
    }
}
