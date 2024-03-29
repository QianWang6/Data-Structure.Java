package com.atguigu.stack;

import java.util.Scanner;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //创建对象
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:从栈中取出数据");
            System.out.println("请输入选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    System.out.println("取出数字："+stack.pop());
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");


    }
}

//定义一个类，表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据存在数组中
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];//初始化后才能存放数据
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        //
        if (isFull()){
            System.out.println("栈满");
        }
        top++;
        stack[top] = value;
    }
    //出栈,将栈顶数据返回
    public int pop(){
        if (isEmpty()){
            System.out.println("栈空");
        }
        int temp = stack[top];
        top--;
        return temp;
    }
    //遍历栈,遍历时，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.println(stack[i]);

        }

    }
}