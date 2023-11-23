package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个对象
        ArrayQueue arrayQueue = new ArrayQueue(3);
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

//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列
    //创建队列构造器

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，是数据头部的前一个位置
        rear = -1;//指向队列尾部，是指的数据尾部的那个数据
    }

    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize -1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加数据了");
            return;
        }
        rear++; //rear后移
        arr[rear] = n;
    }
    //出队列
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能去数据");
        }
        front++;
        return arr[front];
    }
    //显示队列的所有数据
    public void showQueue(){
        //遍历数组
        if(isEmpty()){
            System.out.println("队列为空，无数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }

    }
    //显示队列头部是多少，注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，无数据");
        }
        return arr[front+1];
    }
}
