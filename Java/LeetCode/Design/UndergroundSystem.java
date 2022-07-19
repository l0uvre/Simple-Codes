/** LC 1396 -- Design, Hash Table. **/
import java.util.*;
import javafx.util.*;

/** Use a hashmap to keep track of the checkin times of customers;
 * another hashmap to record the number of station to station travels and
 * the total traveling time so that we can calculate the average. **/
public class UndergroundSystem {

    /** <id, <start_station, checkin time >. **/
    Map<Integer, Pair<String, Integer>> checkIn;
    /** <start_station->end_station, <count, total_travelingtime>> . **/
    Map<String, Pair<Integer, Integer>> travelTime;
    public UndergroundSystem() {
        checkIn = new HashMap<>();
        travelTime = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkIn.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        int departTime = checkIn.get(id).getValue();
        int start = checkIn.get(id).getKey();
        /** check out the customer. **/ 
        checkIn.remove(id);

        String travel = start + "->" + stationName;
        travelTime.putIfAbsent(travel, new Pair<>(0, 0));
        int count = travelTime.get(travel).getKey() + 1;
        int totalTime = travelTime.get(travel).getValue() + (t - departTime);
        travelTime.put(travel, new Pair<>(count, totalTime));
        
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String travel = startStation + "->" + endStation;
        int count = travelTime.get(travel).getKey();
        int totalTime = travelTime.get(travel).getValue();
        return totalTime / (double) count;
    }

}
