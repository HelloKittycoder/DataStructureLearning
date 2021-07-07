package com.kittycoder.datastructure.stack;

/**
 * Created by shucheng on 2019/12/31 14:44
 * 结合课程思路用栈简单实现的计算器
 */
public class Calculator {

    private ArrayStack<Integer> numberStack; // 数栈
    private ArrayStack<String> symbolStack; // 符号栈
    private ArrayStack<String> bracketStack; // 括号栈

    public Calculator() {
        this.numberStack = new ArrayStack<>();
        this.symbolStack = new ArrayStack<>();
        this.bracketStack = new ArrayStack<>();
    }

    // 计算符号表达式
    public void calculate(String calcStr) {
        for (int i = 0; i < calcStr.length(); i++) {
            char c = calcStr.charAt(i);
            if (isNumber(c)) {
                String str = String.valueOf(c);
                // 如果是数字，不能直接入栈，应该向后多看一位
                while (i < calcStr.length() - 1 && isNumber(calcStr.charAt(i + 1))) {
                    i++;
                    // str += String.valueOf(calcStr.charAt(i));
                    str += calcStr.charAt(i);
                }
                numberStack.push(Integer.parseInt(str));
            }
            // 如果有符号
            if (isOperator(c)) {
                // 处理括号
                if (c == '(') {
                    // 如果遇到(，就直接往bracketStack里放
                    bracketStack.push(String.valueOf(c));
                    continue;
                }
                if (c == ')') {
                    // 如果遇到)，就先让bracketStack出栈（如果pop出错，说明右括号多了，这里左括号多了不会报错）
                    // 然后让数栈弹出两个数字，符号栈弹出一个符号，进行计算，并把结果放到数栈中
                    bracketStack.pop();
                    int b = numberStack.pop();
                    int a = numberStack.pop();
                    String topSymbol = symbolStack.pop();
                    int result = operate(a, b, topSymbol);
                    numberStack.push(result);
                    continue;
                }
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
        System.out.printf("%s=%s\n",calcStr, numberStack.pop());
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
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        c.calculate("3+2*6-2"); // 13
        c.calculate("7*2*2-5+1-5+3-4"); // 18
        c.calculate("70+2*6-4");  // 这里算出来的是8，显然是错的，正确的是78
        // 带括号的情形
        c.calculate("(3+2)*6-2"); // 28
        c.calculate("(((3+2)*6)+6)/6"); // 6
        c.calculate("((1+2*3+5)+6)/6"); // 3
    }
}
