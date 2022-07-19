/** LC 1244 - Design, Hash Table, Heap. **/
/** Use a hash map to keep track of players' scores;
 * Use a k sized minHeap to calculate topK every time we call top(K).
 * */
public class Leaderboard {

    Map<Integer, Integer> board;
    public Leaderboard() {
        board = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) {
        board.put(playerId, board.getOrDefault(playerId, 0) + score);
    }
    
    public int top(int K) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int score : board.values()) {
            minHeap.offer(score);
            if (minHeap.size() > K) {
                minHeap.poll();
            }
        }
        int res = 0;
        while (!minHeap.isEmpty()) {
            res += minHeap.poll();
        }
        return res;
        
    }
    
    public void reset(int playerId) {
        board.remove(playerId);
    }
    

    /** another more efficient approach:
     * we can use a TreeMap<Score, counts> to keep tracks of scores and their 
     * occurences while maintaining the scores in the descending order. **/
}
