package com.kittycoder.stack;

/**
 * Created by shucheng on 2019/12/31 14:44
 * 结合课程思路用栈简单实现的计算器
 */
public class Calculator {

    private ArrayStack<Integer> numberStack; // 数栈
    private ArrayStack<String> symbolStack; // 符号栈

    public Calculator() {
        resetCalc();
    }

    // 计算符号表达式
    public void calculate(String calcStr) {
        for (int i = 0; i < calcStr.length(); i++) {
            char c = calcStr.charAt(i);
            if (isNumber(c)) {
                numberStack.push(getNumber(c));
            }
            // 如果有符号
            if (isOperator(c)) {
                if (symbolStack.isEmpty()) {
                    symbolStack.push(String.valueOf(c));
                } else {
                    String topSymbol = symbolStack.peek(); // 栈顶的符号
                    String pushSymbol = String.valueOf(c); // 要放入栈的符号
                    // 如果需要放到栈里的符号优先级更低
                    if (comparePriority(pushSymbol, topSymbol) <= 0) {
                        int b = numberStack.pop();
                        int a = numberStack.pop();
                        symbolStack.pop();
                        int result = operate(a, b, topSymbol);

                        numberStack.push(result);
                        symbolStack.push(pushSymbol);
                    } else {
                        symbolStack.push(String.valueOf(c));
                    }
                }
            }
        }
        while (!symbolStack.isEmpty()) {
            int b = numberStack.pop();
            int a = numberStack.pop();
            String topSymbol = symbolStack.pop();
            int result = operate(a, b, topSymbol);

            numberStack.push(result);
        }
        System.out.println(numberStack.peek());
        resetCalc();
    }

    // 重置计算器
    private void resetCalc() {
        this.numberStack = new ArrayStack<>();
        this.symbolStack = new ArrayStack<>();
    }

    public static int operate(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new RuntimeException("无效运算符");
        }
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

    public static int getNumber(char c) {
        String str = String.valueOf(c);
        return Integer.parseInt(str);
    }

    // 判断是否为数字
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    // 判断是否为运算符
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        c.calculate("3+2*6-2"); // 13
        c.calculate("7*2*2-5+1-5+3-4"); // 18
    }
}
