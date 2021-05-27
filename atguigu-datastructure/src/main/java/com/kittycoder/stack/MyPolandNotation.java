package com.kittycoder.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by shucheng on 2020/1/5 10:52
 * 逆波兰计算器相关代码（根据课程思路写的）
 */
public class MyPolandNotation {

    public static void main(String[] args) {
        List<String> list = toSuffixExpression("4*5-8+60+8/2");
        System.out.println(list);
    }

    // 中缀转后缀表达式（自己按课程思路写的）
    /**
     * 这里和PolandNotation（课程里的代码）相比，差异在：
     * 1.多用了一个栈
     * 2.判断逻辑，代码写的相对麻烦点
     */
    public static List<String> toSuffixExpression(String middleExpression) {
        Stack<String> resultStack = new Stack<>(); // 结果栈
        Stack<String> symbolStack = new Stack<>(); // 符号栈
        for (int i = 0; i < middleExpression.length(); i++) {
            char c = middleExpression.charAt(i);
            // 如果是数字
            if (isNumber(c)) {
                String str = String.valueOf(c);
                // 如果是数字，不能直接入栈，应该向后多看一位
                while (i < middleExpression.length() - 1 && isNumber(middleExpression.charAt(i + 1))) {
                    i++;
                    str += middleExpression.charAt(i);
                }
                resultStack.push(str);
            }
            // 如果是符号
            if (isOperator(c)) {
                String pushSymbol = String.valueOf(c); // 要放入栈的符号

                // 处理括号
                if (c == '(') {
                    symbolStack.push(String.valueOf(c));
                    continue;
                }
                if (c == ')') {
                    String b = symbolStack.pop();
                    resultStack.push(b);
                    symbolStack.pop();
                    continue;
                }
                if (symbolStack.isEmpty()) {
                    symbolStack.push(String.valueOf(c));
                } else {
                    String topSymbol = symbolStack.peek(); // 栈顶的符号
                    if ("(".equals(topSymbol)) {
                        symbolStack.push(String.valueOf(c));
                    } else {
                        // 如果需要放到栈里的符号优先级更低
                        if (comparePriority(pushSymbol, topSymbol) <= 0) {
                            String b = symbolStack.pop();
                            resultStack.push(b);

                            symbolStack.push(pushSymbol);
                        } else {
                            symbolStack.push(pushSymbol);
                        }
                    }
                }
            }
        }

        // 把符号栈的剩余数据依次弹出放入结果栈中
        while (!symbolStack.isEmpty()) {
            resultStack.push(symbolStack.pop());
        }

        int size = resultStack.size();
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            resultList.add(0, resultStack.pop());
        }
        return resultList;
    }

    // 比较两个符号的优先级
    public static int comparePriority(String a, String b) {
        return getPriority(a) - getPriority(b);
    }

    public static int getPriority(String operator) {
        if ("+".equals(operator) || "-".equals(operator)) {
            return 1;
        } else if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        } else {
            throw new RuntimeException("无效运算符");
        }
    }

    // 判断是否为数字
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    // 判断是否为运算符
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
