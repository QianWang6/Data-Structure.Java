package sort;

import java.util.Arrays;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr={101,34,119,1,15,0};
        insertSort(arr);
    }
    //插入排序
    public static void insertSort(int[] arr){
        /*//使用逐步推导的方式来讲
        //第一轮{101,34,119,1}==>{34,101,119,1}

        //定义待插入的数
        int insertVal = arr[1];//下标为1的那个数,即34,这时候默认101在新的数组里的第一个
        int insertIndex = 0;//arr[1]前的下标，

        //给insertVal找到插入的位置
        //1.insertVal<arr[insertIndex]说明待插入的数，还没找到位置
        //2.就需要将arr[insertIndex]后移
        while (insertIndex>=0&&insertVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];//把101后移
            insertIndex--;
        }
        //当退出while循环时，说明插入位置找到，位置是insertIndex+1；
        arr[insertIndex+1]=insertVal;
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];//下标为2的那个数,即119,
        insertIndex = 1;//arr[2]前的下标
        while (insertIndex>=0&&insertVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex+1]=insertVal;
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];//下标为2的那个数,即119,
        insertIndex = 2;//arr[2]前的下标
        while (insertIndex>=0&&insertVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex+1]=insertVal;
        System.out.println(Arrays.toString(arr));*/

        //使用for循环用代码简化
        for (int i = 1; i <arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i-1;
            while (insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            //加一句判断：如果insertVal不需要换位置就不赋值了
            if(insertIndex +1 != i){
                arr[insertIndex+1]=insertVal;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
