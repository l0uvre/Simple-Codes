/** LC 1274 -- Divide and Conquer. **/
public class NumberShipsRectangle {
    /** Get the middle point of topRight and bottomLeft and then divide the
     * original problem into four subproblems; 
     *
     * the base case is when topRight and bottomLeft fall on a single point,
     * if Sea.hasShips(topRight, bottomLeft) then we know it's 1. **/
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        /** we have ships in the rectangle. **/
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            /** we have ships on a single point. **/
            return 1;
        }

        int midX = (topRight[0] + bottomLeft[0]) / 2;
        int midY = (topRight[1] + bottomLeft[1]) / 2;

        return countShips(sea, new int[]{midX, midY}, bottomLeft) + 
               countShips(sea, new int[]{topRight[0], midY}, new int[]{midX + 1, bottomLeft[1]}) +
               countShips(sea, new int[]{midX, topRight[1]}, new int[]{bottomLeft[0], midY + 1}) +
               countShips(sea, topRight, new int[]{midX + 1, midY + 1});
    }
}
