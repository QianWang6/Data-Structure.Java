package sort;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class BubbleSorting {
    public static void main(String[] args) {
        int arr[]= {1,2,3,7,5,6};
        //冒泡排序优化
        int temp = 0;
        boolean flag = false;//表示是否进行过交换，如果没有交换说明完成排序
        for (int i = 0;i <= arr.length; i++){
            for (int j = 0;j<=arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    flag=true;
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if (flag==false){
                //说明没有发生交换
                break;
            }else {
                flag=false;//如果进入了，flag会变成true，那要重置为false重新判断
            }
        }

    }
}
