/** LC 1656 -- Design, Hash Table. **/
/** Basic idea, return a contiguous chunk starting at pointer;
 * increment pointer for every insertion. **/
public class OrderedStream {

    int ptr;
    String[] values;

    public OrderedStream(int n) {
        ptr = 0;
        values = new String[n];
        
    }
    
    public List<String> insert(int idKey, String value) {
        List<String> chunk = new ArrayList<>();
        values[idKey - 1] = value;

        while (ptr < values.length && values[ptr] != null) {
            chunk.add(values[ptr]);
            ptr += 1;
        }
        return chunk;
    }
}
