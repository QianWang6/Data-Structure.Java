package com.atguigu.stack;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class Calculator {
    public static void main(String[] args) {
    //完成表达式的运算
        String expression = "300+2*6-2";
        //创建数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char存入
        String keepNum = "";//用于拼接
        //开始循环扫描
        while (true){
            //依次得到expression的每个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是数字还是运算符
            if (operStack.isoper(ch)){
                //判断符号栈是否为空
                if(!operStack.isEmpty()){
                    if (operStack.priority(ch)<= operStack.priority(operStack.peek())){
                        num1 =numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }
            else {
                //numStack.push(ch-48);//因为string中的1对应的是int的49
                //当处理多位数时，不能发现是一个数就立即入栈
                //在处理数时，要像expression表达式的index后再看一位，如果是数字，就继续扫描，如果是符号，再入栈
                //因此需要定义一个变量（字符串变量）用于拼接
                keepNum +=ch;
                //如果ch已经是表达式的最后一位
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断是否为数字
                    if (operStack.isoper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //说明后一位是操作符
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum要清空！
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描完成
            index++;
            if (index==expression.length()){
                break;
            }
        }
        //完成后，就顺序从数栈和符号栈中pop出相应的数和符号，并运行
        while (true){
            //如果符号栈是空的，数栈中只有一个数字就是结果
            if (operStack.isEmpty()){
                break;
            }
            num1 =numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println("表达式结果为："+numStack.pop());
    }
}

//创建栈，扩展功能
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据存在数组中
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];//初始化后才能存放数据
    }
    //查阅（不是取出）栈顶的值
    public int peek(){
        return stack[top];
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
    //返回运算符优先级。优先级使用数字表示，数字越大，优先级越高
    public int priority(int oper){
        if (oper=='*'||oper=='/'){
            return 1;
        }else if (oper == '+'||oper=='-'){
            return 0;
        }else {
            return -1;//假定目前表达式中只有加减乘除
        }
    }
    //判断是不是一个运算符
    public boolean isoper(char val){
        return val=='+'||val=='-'||val=='*'||val =='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res  = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;//后出来的数先算
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }


}