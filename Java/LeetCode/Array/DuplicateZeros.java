// LC 1089 -- Array
public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        int numOfZero = 0;
        for (int i : arr) {
            if (i == 0) {
                numOfZero++;
            }
        }
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            if (i + numOfZero < n) { 
                arr[i + numOfZero] = arr[i];  
            }

            if (arr[i] == 0) {
                numOfZero--;
                if (i + numOfZero < n) {
                    arr[i + numOfZero] = 0;
                }
            } 
        }
    }

}
