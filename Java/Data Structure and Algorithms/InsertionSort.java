public class InsertionSort{
  
  public void sort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int key = array[i];
      int j = i - 1;
      for (; j >= 0; j--) {
	if (array[j] > key) {
	  array[j + 1] = array[j];
	} else {
	  break;
	}
	/*** with more swap, more straightforward
      	if (array[j] > array[j + 1]) {
          swap(array, j + 1, j);	
	} else {
	  break;	
	}
	***/
      }
      array[j + 1] = key;
      
    }
  }
  
  private static void swap(int[] array, int i, int j) {
  	int key = array[i];
	array[i] = array[j];
	array[j] = key;
  }

  private static void print(int[] nums) {
    for (int num : nums) {
      System.out.print(num + " ");
    } 
    System.out.println();
  }

  public static void main(String[] args) {
    InsertionSort s = new InsertionSort();
    int[] nums = new int[]{8, 2, 4, 9, 3, 6};
    print(nums);
    s.sort(nums);
    print(nums);
    nums = new int[] {2, 3, 5, 2, 8, 4, 6};
    print(nums);
    s.sort(nums);
    print(nums);
    nums = new int[] {2, 2, 2, 2, 2, 2, 2};
    print(nums);
    s.sort(nums);
    print(nums);
  }

}
