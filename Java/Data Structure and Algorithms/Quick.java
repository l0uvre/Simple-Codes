import java.util.Arrays;
import java.util.List;

public class Quick {

  public void sort(int[] arr) {
    sort(arr, 0, arr.length - 1);
  }

  public void sort(int[] arr, int begin, int end) {
    if (begin >= end) {
      return;
    }
    int mid = partition(arr, begin, end);
    sort(arr, begin, mid - 1);
    sort(arr, mid + 1, end);
  }

  public int partition(int[] arr, int begin, int end) {
    int pivot = arr[begin];
    int i = begin;
    int j = end + 1;
    while (true) {
      do {
        i++;
      } while (i <= end && arr[i] < pivot);

      do {
        j--;
      } while (arr[j] > pivot);

      if (i >= j) {
        break;
      }

      exchange(arr, i, j);
    }

    exchange(arr, begin, j);
    return j;
  }

  private void exchange(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public void show(int[] arr) {
    if (arr != null && arr.length > 0) {
      for (int i = 0; i < arr.length; i++) {
        System.out.print(arr[i]);
        if (i == arr.length - 1) {
          System.out.println();
        } else {
          System.out.print("  ");
        }
      }
    }
  }

  public static void main(String[] args) {
    Quick quickSort = new Quick();
    int[] a = {0, 0, 0, 0, 0, 0, 0, 0};
    quickSort.sort(a);
    quickSort.show(a);
    int[] b = {0, 2, 6, 8, 7, 3, 9, 4, 5, 6, 8, 1, 2, 3, 1, 52, 34, 15, 17, 35, 97};
    quickSort.sort(b);
    quickSort.show(b);
  }

}
