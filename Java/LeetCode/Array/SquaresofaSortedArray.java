public class SquaresofaSortedArray {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        } else {
            int[] res = new int[A.length];
            int i = 0;
            int j = A.length - 1;
            for (int k = res.length - 1; k >= 0; k--) {
                int squareI = A[i] * A[i];
                int squareJ = A[j] * A[j];
                if (squareI > squareJ) {
                    res[k] = squareI;
                    i++;
                } else {
                    res[k] = squareJ;
                    j--;
                }
            }
            return res;
        }
    }

    private static void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SquaresofaSortedArray ssa = new SquaresofaSortedArray();
        int[] arr = new int[]{-4, -1, 0, 3, 10};
        print(arr);
        print(ssa.sortedSquares(arr));

        arr = new int[]{-7, -3, 2, 3, 11};
        print(arr);
        print(ssa.sortedSquares(arr));
    }
}

