class AddString {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int a = 0, b = 0;
            if (i >= 0) {
                a = num1.charAt(i) - '0';
            }
            if (j >= 0) {
                b = num2.charAt(j) - '0';
            }
            
            int curr = (a + b + carry) % 10;
            sb.append(curr);
            carry = (a + b + carry) / 10;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        AddString sol = new AddString();
        System.out.println(sol.addStrings("9999999999999999999999999999", "1"));
    }
}
