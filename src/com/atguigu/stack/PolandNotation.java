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
        //完成一个中最表达式转换为后缀表达式对功能
        //1.1+（（2+3）*4）-5=》123+4*+5-
        //2.因为直接对字符串进行操作不方便，所以先将字符串转成一个中缀表达式对应的list
        //即把字符串转成一个Array list[1,+,（,2,+,3,）,*,4,）,-,5]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("后缀表达式对应的list:"+infixExpressionList);
        //3.将得到的中缀表达式对应的list转成后缀表达的list
        //即把[1,+,（,2,+,3,）,*,4,）,-,5]===》ArrayList[1,2,3,+,4,*,+,5,-]
        System.out.println("后缀表达式对应的list:"+parseSuffixExpressionList(infixExpressionList));

       /* //定义一个逆波兰表达式
        //（3+4）*5-6==》3 4 + 5 * 6 -
        //为了方便，逆波兰表达式中的数字和符号用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //1.先将3 4 + 5 * 6 -放到一个array list中，字符串一个个扫描效率低
        //2.将array list传递给一个方法，方法配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println("结果是："+ res);*/
    }
    //方法：将得到的中缀表达式对应的list转成后缀表达的list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//存放运算符的，符号栈
        //因为s2栈栈转换过程中没有pop操作，而且后面还需要逆序输出，改为List更方便
        List<String> s2 = new ArrayList<String>();//存储中间结果的List
        //遍历ls
        for (String item:ls){
            //如果是数直接入栈s2
            if(item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);//入符号栈
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号位置，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出消掉
            }else {
                //当item优先级小于等于s1栈顶运算符的优先级，弹出s1运算符，压入list中
                while (s1.size()!=0&& Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入s1
                s1.push(item);
            }
        }
        //将s1中剩余的运算符弹出加入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放在list中，因此存放顺序就是要的逆波兰表达式顺序
    }

    //方法：将中缀表达式转成对应对list
    public static List<String> toInfixExpressionList(String s){
        //定义一个list，存放中缀表达式对应的数据
        List<String> ls = new ArrayList<String>();
        int i = 0;//指针，用于遍历字符串s
        String str;//用于对多位数拼接
        char c;//每遍历一个字符，就放入
        do {
            //如果c是非数字，就需要加入到ls中
            if ((c = s.charAt(i))<48||(c = s.charAt(i))>57){
                ls.add(""+c);
                i++;
            }else {//如果是一个数，要考虑多位数的问题
                str = "";//先将str制成“”
                while (i<s.length()&&(c = s.charAt(i))>=48&&(c = s.charAt(i))<=57){
                    str +=c;
                    i++;
                }
                ls.add(str);
            }
        }while(i<s.length());
        return ls;

    }
    /*//1.将逆波兰表达式放入到array list中
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
    }*/
}

//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    //写一个方法返回对应的优先级
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}