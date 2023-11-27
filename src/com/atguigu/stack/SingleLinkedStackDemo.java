package com.atguigu.stack;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class SingleLinkedStackDemo {
    public static void main(String[] args) {

    }
}

class LinkedStack{
    private int maxSize;//栈的大小
    private SingleLinkedList stack;//单链表模拟栈，数据存在节点中
    private int top = -1;//栈顶，初始化为-1

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
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
    public void push(SingleNode singleNode){
        //
        if (isFull()){
            System.out.println("栈满");
        }
        top++;
        stack.add(singleNode);
    }
    //出栈,将栈顶数据返回
    public SingleNode pop(){
        if (isEmpty()){
            System.out.println("栈空");
        }
        SingleNode temp = stack.remove();
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
            System.out.println(stack);
        }

    }
}



//创建一个单链表
class SingleLinkedList{
    private SingleNode head = new SingleNode(0);

    public SingleNode getHead() {
        return head;
    }

    //是否为空
    public boolean isEmpty(){
        return head.next== null;
    }

    //添加数据
    public void add(SingleNode singleNode){
        SingleNode temp = head.next;
        boolean loop = true;
        while (loop){
            if (temp.next==null){
                loop=false;
            }
            temp = temp.next;
        }
        temp.next = singleNode;
    }

    public SingleNode remove(){
        SingleNode temp = head.next;
        boolean loop = true;
        while (loop){
            if (temp.next.next==null){
                System.out.println("链表已空");
                loop=false;
            }
            temp = temp.next;
        }
        SingleNode lastone = temp.next;
        temp.next = null;
        return lastone;
    }

    public void list(){
        SingleNode temp = head.next;
        boolean loop = true;
        while (loop){
            if (temp.next==null){
                loop=false;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

//创建节点
class SingleNode{
    public int no;
    public SingleNode next;
    public SingleNode(int no) {
        this.no = no;
    }



    @Override
    public String toString() {
        return "singleNode{" +
                "no=" + no ;
    }
}