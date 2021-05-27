package com.kittycoder.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by shucheng on 2020/1/3 17:33
 * 逆波兰计算器
 */
public class PolandNotation {

    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (3+4)*5-6 => 3 4 + 5 * 6 - => 29
        // (30+4)*5-6 => 30 4 + 5 * 6 - => 164
        // 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / + =>76
        String s = "3 4 + 5 * 6 -";
        System.out.println(calculate(getListString(s)));

        String s2 = "1+((2+3)*4)-5";
        List<String> ls = PolandNotation.toInfixExpressionList(s2);
        List<String> resultList = PolandNotation.parseSuffixExpressionList(ls);
        System.out.println(resultList);
        System.out.println(calculate(resultList)); // 16
    }

    // 中缀表达式转后缀表达式：先调用toInfixExpressionList，然后调用parseSuffixExpressionList
    // 将得到的中缀表达式对应List=>后缀表达式对应的List
    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  => ArrayList [1,2,3,+,4,*,+,5,–]
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>(); // 符号栈
        // 这里没有再用栈s2，因为栈s2只是单纯地往里放数据，最后一次性把数据弹出来，取逆序的结果
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的List s2

        for (String item : ls) {
            if (item.matches("\\d+")) {
                // 如果是操作数，直接压入s2中
                s2.add(item);
            } else if ("(".equals(item)) {
                // 如果是左括号“(”，直接压入s1
                s1.push(item);
            } else if (")".equals(item)) {
                // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2中，直到遇到左括号为止，
                // 此时将这一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                // 如果是运算符，比较其与s1栈顶运算符的优先级：
                // 1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈
                // 2）否则，若优先级比栈顶运算符的高，也将运算符压入s1
                // 3）否则，将s1栈顶的运算符弹出并压入s2中，再次转到1）与s1中新的栈顶运算符相互比较
                while (s1.size() != 0 && !"(".equals(s1.peek()) && Operation.getPriority(item) <= Operation.getPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
        return s2;
    }

    // 将中缀表达式转换成对应的list
    // s="1+((2+3)×4)-5";
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0; // 这是一个指针，用于遍历中缀表达式字符串
        String str; // 用于拼接多位数
        while (i < s.length()) {
            char c = s.charAt(i);
            // 数字的ascii码范围是[48,57]，依次对应[0,9]
            if (!isNumber(c)) { // 非数字
                ls.add(String.valueOf(c));
            } else { // 如果是一个数，需要考虑多位数
                str = String.valueOf(c);
                while (i < s.length() -1 && isNumber(s.charAt(i + 1))) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
            i++;
        }
        return ls;
    }

    // 判断是否为数字
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    // 将一个逆波兰表达式的所有数据和运算符，依次放入ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式的运算
    /**
     * 1）从左至右扫描，将3和4压入堆栈
     * 2）遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算3+4的值，得7，再将7入栈
     * 3）将5入栈
     * 4）接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈
     * 5）将6入栈
     * 6）最后是-运算符，计算35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        // 创建一个栈（只需要一个栈）
        Stack<String> stack = new Stack<>();
        // 遍历ls
        for (String item : ls) {
            // 这里使用正则表达式来取出数
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符无效");
                }
                // 把res入栈
                stack.push("" + res);
            }
        }
        // 最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

    // 定义了运算符的优先级
    static class Operation {
        private static int ADD = 1;
        private static int SUB = 1;
        private static int MUL = 2;
        private static int DIV = 2;

        // 写一个方法，返回对应运算符的优先级数字
        public static int getPriority(String operator) {
            int result = 0;
            switch (operator) {
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
                    System.out.println("不存在该运算符" + operator);
                    break;
            }
            return result;
        }
    }
}
