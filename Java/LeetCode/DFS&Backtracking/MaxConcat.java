import java.util.*;

class MaxConcat {
    public int maxLength(List<String> arr) {
        int res = 0;
        if (arr == null || arr.size() == 0) {
            return res;
        }    
        res = dfs("", arr, 0, res);
        return res;
    }

    private int dfs(String curr, List<String> arr, int index, int res) {
        if (duplicate(curr)) {
            return res;
        }
        res = Math.max(res, curr.length());
        //System.out.println(curr);
        if (index < arr.size()) {
            int max1 = dfs(curr, arr, index + 1, res);
            int max2 = dfs(curr + arr.get(index), arr, index + 1, res);
            res = Math.max(res, Math.max(max1, max2));
        }
        return res;
    }

    private boolean duplicate(String sb) {
        Set<Character> set = new HashSet<>();
        for (char c : sb.toCharArray()) {
            if (set.contains(c)) {
                return true;
            } else {
                set.add(c);
            }
        }
        return false;
    }



    public static void main(String[] args) {
        MaxConcat mx = new MaxConcat();
        List<String> arr = Arrays.asList("a", "b", "c", "d");
        System.out.println(mx.maxLength(arr));

        arr = Arrays.asList("cha", "r", "act", "ers");
        System.out.println(mx.maxLength(arr));

        arr = Arrays.asList("un", "iq``", "ue");
        System.out.println(mx.maxLength(arr));

        arr = Arrays.asList("abcdefghijklmnopqrstuvwxyz");
        System.out.println(mx.maxLength(arr));
    }
}
