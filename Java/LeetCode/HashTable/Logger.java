/** LC 359 -- HashTable */
import java.util.*;
public class Logger {

    Map<String, Integer> messageToLastPrintedTime;
    public Logger() {
        messageToLastPrintedTime = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!messageToLastPrintedTime.containsKey(message)) {
            messageToLastPrintedTime.put(message, timestamp);
            return true;
        } else {
            if (timestamp - messageToLastPrintedTime.get(message) >= 10) {
                messageToLastPrintedTime.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        }
    }
}