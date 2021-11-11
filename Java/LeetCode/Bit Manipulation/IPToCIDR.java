//Leetcode 751
import java.util.*;
public class IPToCIDR {
    private long getIpValue(String ip) {
        String[] strs = ip.split("\\.");
        long value = 0;
        int shift = 32 - 8;
        for (int i = 0; i < strs.length; i++) {
            value += (long) Integer.valueOf(strs[i]) << shift;
            shift -= 8;
        }
        return value;
    }

    private String ipValueToString(long ip) {
        StringBuilder sb = new StringBuilder();
        long mask = 255;
        for (int i = 3; i >= 0; i--) {
            long block = ip & (mask << i * 8);
            block = block >> i * 8;
            sb.append(block); 
            sb.append("."); 
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public List<String> ipTocidr(String ip, int n) {
        List<String> res = new ArrayList<>();
        long currIp = getIpValue(ip);
        while (n > 0) {
           long step = currIp & (~(currIp - 1)); // get the vale of only rightmost bit 1 and its tail bits.
           if (step == 0) {
              step = n; // for 0.0.0.0
           }
           step = Math.min(step, n);
           int mask = (int) (Math.log(step) / Math.log(2)); // get the lower bound for net work mask.
           step = (int) Math.pow(2, mask);
           res.add(ipValueToString(currIp) + "/" + (32 - mask));
           currIp += step;
           n -= step;
        }
        return res;
    }

    public static void main (String[] args) {
		String ip = "255.0.0.7";
		int n = 10;
		IPToCIDR sol = new IPToCIDR();
		System.out.println(sol.ipTocidr(ip, n));
		ip = "127.0.8.3";
		n = 8;
		System.out.println(sol.ipTocidr(ip, n));
        ip = "127.0.8.4";
		n = 3;
		System.out.println(sol.ipTocidr(ip, n));
        ip = "0.0.0.0";
		n = 3;
		System.out.println(sol.ipTocidr(ip, n));

        ip = "127.0.8.3";
		n = 8;
		System.out.println("Starting ip: " + ip + "; range: " + n + " " + sol.ipTocidr(ip, n));
        ip = "127.0.8.4";
		n = 3;
		System.out.println(sol.ipTocidr(ip, n));
		System.out.println("Starting ip: " + ip + "; range: " + n + " " + sol.ipTocidr(ip, n));
        ip = "0.0.0.0";
		n = 3;
		System.out.println(sol.ipTocidr(ip, n));
		System.out.println("Starting ip: " + ip + "; range: " + n + " " + sol.ipTocidr(ip, n));

        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(rd.nextInt(256)).append(".");
        }
        sb.setLength(sb.length() - 1);
        ip = sb.toString();
        n = rd.nextInt(25);
		System.out.println("Starting ip: " + ip + "; range: " + n + " " + sol.ipTocidr(ip, n));
        


    }

}
