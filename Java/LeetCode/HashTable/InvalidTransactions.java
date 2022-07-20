/** LC 1169 -- Design, Array, String. **/
import java.util.*;
public class InvalidTransactions {
    /** We build a class of transaction and check if it's valid 
     * itself and if it has conflicts with others. **/
    class Transaction {
        String name;
        int time;
        int amount;
        String city;
        boolean valid;
        Transaction(String n, int t, int a, String c) {
            name = n;
            time = t;
            amount = a;
            city = c;
            valid = (amount <= 1000) ? true : false;
        }
        boolean isValid() {
            return valid;
        }

        public String toString() {
            return name + "," + String.valueOf(time) +
                "," + String.valueOf(amount) + "," + city;
        }
        void markIfHasConflitWith(Transaction another) {
            if (name.equals(another.name) && !city.equals(another.city)) {
                if (Math.abs(this.time - another.time) <= 60) {
                    this.valid = false;
                    another.valid = false;
                }
            }
        }
    }

    public List<String> invalidTransactions(String[] transactions) {
        List<String> res = new ArrayList<>();
        /** hold transactions vaild themselves. **/
        List<Transaction> trans = new ArrayList<>();
        for (String tran : transactions) {
            String[] splits = tran.split(",");
            Transaction curr = new Transaction(splits[0], Integer.valueOf(splits[1]),
                    Integer.valueOf(splits[2]), splits[3]);
            /** an invalid trans can have a conflict with another. **/
            trans.add(curr);
        }
        for (int i = 0; i < trans.size(); i++) {
            Transaction curr = trans.get(i);
            for (int j = 0; j < trans.size() && j != i; j++) {
                Transaction next = trans.get(j);
                curr.markIfHasConflitWith(next);
            }
        }
        for (Transaction tran : trans) {
            if (!tran.isValid()) {
                res.add(tran.toString());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        InvalidTransactions sol = new InvalidTransactions();
        String[] trans = new String[]{
            "alice,20,800,mtv","alice,50,100,beijing"
        };
        System.out.println(sol.invalidTransactions(trans)); 

        trans = new String[]{
            "alice,20,800,mtv","alice,50,1200,mtv"
        };
        System.out.println(sol.invalidTransactions(trans)); 
            

    }
}
