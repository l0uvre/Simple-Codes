public class Merge {

    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    public void sort(int[] arr, int[] aux, int lo, int hi) {
        if (lo >= hi) {
          return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    public void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = arr[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j];
                j++;
            } else if (j > hi) {
                arr[k] = aux[i];
                i++;
            } else {
                if (aux[i] < aux[j]) {
                    arr[k] = aux[i];
                    i++;
                } else {
                    arr[k] = aux[j];
                    j++;
                }
            }
        }
    }

    public static void show(int[] arr) {
      for (int ele : arr) {
          System.out.print(ele + " ");
      }
      System.out.println();
    }

    public static void main(String[] args) {
        Merge mergeSort = new Merge();
        int[] arr = {1,-2,3,-4,-5,1,9,8,7,6,5,8,9,3,2,0,10,10};
        mergeSort.sort(arr);
        show(arr);
        int[] zeroNum = {};
        mergeSort.sort(zeroNum);
        show(zeroNum);
        int[] nums = {0,0,0,0,0,0,0,0,0};
        mergeSort.sort(nums);
        show(nums);

    }

}