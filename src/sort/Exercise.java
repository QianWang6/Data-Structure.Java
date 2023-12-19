package sort;

import java.util.Arrays;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class Exercise {
    public static void main(String[] args) {
        int arr[]={1,44,67,2,3,87,23};
        insertSort(arr);
    }
    public static void insertSort(int[] arr){
        int insertVal= 0;
        int insertIndex = 0;
        for (int i = 1;i<arr.length;i++){
            insertVal=arr[i];
            insertIndex=i-1;
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }
}
