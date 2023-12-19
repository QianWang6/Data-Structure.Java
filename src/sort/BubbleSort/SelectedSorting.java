package sort.BubbleSort;

import java.util.Arrays;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class SelectedSorting {
    public static void main(String[] args) {
        //选择排序
        int arr[]={101,34,5,87,2,119,6,99,1};
        selectedSort(arr);

    }
    public static void selectedSort(int arr[]){
        int temp=0 ;
        for (int i = 0;i< arr.length-1;i++){
            int min = arr[i];
            for (int j = i+1;j<= arr.length-1;j++){
                if (arr[j]<min){
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                    min=arr[i];

                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
