class Solution {
    
    Map<String, List<String>> map = new HashMap<>();
    
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String str: allowed) {
            String pair = str.substring(0, 2);
            String val = str.substring(2);
            if (!map.containsKey(pair)) {
                map.put(pair, new LinkedList<>());
            }
            map.get(pair).add(val);
        }
        
        return backtrack(bottom, 0, "");
    }
    
    private boolean backtrack(String curr, int i, String next) {
        int len = curr.length();
        if (len == 2) {
            return map.containsKey(curr);
        }
        if (i == len - 1) {
            //System.out.println(next);
            //DFS step
            return backtrack(next, 0, "");
        }
        
        List<String> vals = map.getOrDefault(curr.substring(i, i + 2), null);
        //System.out.println(vals + ": " + next);
        if (vals == null) {
            return false;
        } else {
            for (String val: vals) {
                if (backtrack(curr, i + 1, next + val)) {
                    return true;
                }
            }
        }
        return false;
    }
}
