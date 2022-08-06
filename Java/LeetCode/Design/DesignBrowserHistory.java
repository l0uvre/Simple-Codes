/** LC 1472 -- Design, Doubly Linked List. **/
/** use a doubly linked list (or array list) to keep track of
 * urls visited and then move forward or backward a pointer. **/
import java.util.*;
public class DesignBrowserHistory {

    private List<String> urls;
    private int pos;
    private int size;

    public BrowserHistory(String homepage) {
        urls = new ArrayList<>();
        urls.add(homepage);
        pos = 0;
        size = 1;
    }
    
    public void visit(String url) {
        pos += 1;
        if (pos == urls.size()) {
            urls.add(url);
        } else {
            urls.set(pos, url);
        }
        size = pos + 1;
    }
    
    public String back(int steps) {
        pos = Math.max(0, pos - steps);
        return urls.get(pos);
    }
    
    public String forward(int steps) {
        pos = Math.min(size - 1, pos + steps);
        return urls.get(pos);
    }
}
