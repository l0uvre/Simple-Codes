class BestTimetoBuySellStock {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            if (price < cost) {
                cost = price;
            } else if (price - cost > profit) {
                profit = price - cost;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        BestTimetoBuySellStock sol = new BestTimetoBuySellStock();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(sol.maxProfit(prices));
    }
}
