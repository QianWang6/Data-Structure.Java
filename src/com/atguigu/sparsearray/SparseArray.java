package com.atguigu.sparsearray;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1 [][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //原始二维数组输出
        for (int[]row :chessArr1){//遍历每一行
            for (int data : row){//遍历每一行的数据
                System.out.printf("%d\t",data);//格式化输出的方法
            }
            System.out.println();
        }
        //转稀疏数组
        //1.先遍历二维数组 得到非0数据个数
        int sum = 0;
        for(int i = 0;i<chessArr1.length;i++){
            for (int j = 0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println(sum);
        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组 ，将非零值存放到稀疏数组
        int count = 0;//用于记录是第几个非零数据
        for(int i = 0;i<chessArr1.length;i++){
            for (int j = 0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //数据稀疏数组形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);//输出所在行的第一列、第二列、第三列
        }

        //将稀疏数组恢复成原始二维数组
        System.out.println("还原二维数组：");
        //读取二维数组大小
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //把非零值填入数组
        for (int i = 1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印二维数组
        for (int i = 0; i < sparseArr[0][0]; i++) {
            for (int j = 0; j < sparseArr[0][1]; j++) {
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }
    }
}
