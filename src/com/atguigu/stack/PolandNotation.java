package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        //（3+4）*5-6==》3 4 + 5 * 6 -
        //为了方便，逆波兰表达式中的数字和符号用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //1.先将3 4 + 5 * 6 -放到一个array list中，字符串一个个扫描效率低
        //2.将array list传递给一个方法，方法配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println("结果是："+ res);
    }
    //1.将逆波兰表达式放入到array list中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }
    //2.完成对逆波兰的运算
    public static int calculate(List<String> list){
        //创建栈（只需要一个栈即可）
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item: list){
            //使用正则表达式来取数
            if (item.matches("\\d+")){//这是一个正则表达式，表示多位数
                //入栈
                stack.push(item);
            }else {//pop出两个数，然后运算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1+num2;
                } else if (item.equals("-")) {
                    res = num2-num1;
                } else if (item.equals("*")) {
                    res = num1*num2;
                } else if (item.equals("/")) {
                    res = num2/num1;
                }else {
                    throw new RuntimeException("符号错误");
                }
                //把res入栈
                stack.push(res+"");//把整数转为字符串
            }
        }
        //最后留在stack中的数据就是结果
        return Integer.parseInt(stack.pop());
    }
}
