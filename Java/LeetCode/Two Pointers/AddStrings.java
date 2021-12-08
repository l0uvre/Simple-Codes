/*** LC 415 -- Two Pointers **/
public class AddStrings {

    /** maintain two pointers and a carry bit **/
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int sum = 0;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';     
                i--;
            }

            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }
            int digit = (sum + carry) % 10;
            carry = (sum + carry) / 10;
            sb.append(digit);
        }
        if (carry > 0) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddStrings sol = new AddStrings(); 
        String num1 = "11", num2 = "123";
        System.out.println(sol.addStrings(num1, num2));

        num1 = "1";
        num2 = "999";
        System.out.println(sol.addStrings(num1, num2));

        num1 = "0";
        num2 = "999";
        System.out.println(sol.addStrings(num1, num2));

        num1 = "0";
        num2 = "0";
        System.out.println(sol.addStrings(num1, num2));

        num1 = "456";
        num2 = "77";
        System.out.println(sol.addStrings(num1, num2));
    }
}
