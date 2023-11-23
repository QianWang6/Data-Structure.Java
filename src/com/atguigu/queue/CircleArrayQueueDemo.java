package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试环形数组的案例
        CircleArray arrayQueue = new CircleArray(4);//有效数据最大是3，因为rear有个约定空间
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据");
                    int num = scanner.nextInt();
                    arrayQueue.addQueue(num);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出数据为"+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());//如果取不到就抛异常
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("队列头数据为"+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头，初始值0：第一个数据
    private int rear;//队列尾，初始值0:注意这里是最后一个数据的下一位
    private int[] arr;//该数组用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满了
    public boolean isFull() {
        return (rear+1)%maxSize==front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加数据了");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模(超过一圈），否则数组越界
        rear = (rear+1)%maxSize;
    }

    //出队列
    public int getQueue() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能去数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到一个临时变量
        int value = arr[front];
        //2.将front后移,考虑取模。直接这样front++;会报数据越界
        front = (front+1)%maxSize;
        //3.将临时变量返回
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历数组
        if (isEmpty()) {
            System.out.println("队列为空，无数据");
            return;
        }
        //从front开始遍历，遍历多少个元素
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //求出当前队列的有效数据个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列头部是多少，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无数据");
        }
        return arr[front];
    }
}