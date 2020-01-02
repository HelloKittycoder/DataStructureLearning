package com.kittycoder.stack;

/**
 * Created by shucheng on 2019/12/31 14:44
 * 课程中用栈简单实现的计算器
 */
public class Calculator2 {

    private ArrayStack numberStack; // 数栈
    private ArrayStack symbolStack; // 符号栈

    public Calculator2() {
        this.numberStack = new ArrayStack();
        this.symbolStack = new ArrayStack();
    }

    // 计算符号表达式
    public void calculate(String calcStr) {
        String keepNum = "";
        for (int i = 0; i < calcStr.length(); i++) {
            char c = calcStr.charAt(i);
            if (isNumber(c)) {
                // 分析思路
                // 1.当处理多位数时，不能发现是一个数就直接入栈，因为它可能是多位数
                // 2.在处理数时，需要向表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                // 3.因此我们需要定义一个变量字符串，用于拼接
                // 处理多位数
                keepNum += c;

                if (i == calcStr.length() - 1) {
                    numberStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，就继续扫描；如果是运算符，则入栈
                    // 注意是看后一位，不是index++
                    if (isOperator(calcStr.charAt(i + 1))) {
                        // 如果后一位是运算符，则入栈 keepNum = "1"或者"123"
                        numberStack.push(Integer.parseInt(keepNum));
                        // important!! keepNum清空
                        keepNum = "";
                    }
                }
            }
            // 如果有符号
            if (isOperator(c)) {
                if (symbolStack.isEmpty()) {
                    symbolStack.push(c);
                } else {
                    // 如果需要放到栈里的符号优先级更低
                    if (getPriority(c) <= getPriority(symbolStack.peek())) {
                        int b = numberStack.pop();
                        int a = numberStack.pop();
                        int topSymbol = symbolStack.pop();
                        int result = operate(a, b, topSymbol);

                        numberStack.push(result);
                        symbolStack.push(c);
                    } else {
                        symbolStack.push(c);
                    }
                }
            }
        }
        while (!symbolStack.isEmpty()) {
            int b = numberStack.pop();
            int a = numberStack.pop();
            int topSymbol = symbolStack.pop();
            int result = operate(a, b, topSymbol);

            numberStack.push(result);
        }
        System.out.printf("%s=%s\n",calcStr, numberStack.pop());
    }

    // 计算方法
    public static int operate(int a, int b, int operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                break;
        }
        return 0;
    }

    // 返回运算符的优先级，优先级是由程序员来确定，优先级使用数字表示
    // 数字越大，则优先级越高
    public static int getPriority(int operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            throw new RuntimeException("无效运算符");
        }
    }

    public static int getNumber(char c) {
        // '1'对应整数49
        return c - 48;
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
        Calculator2 c = new Calculator2();
        c.calculate("3+2*6-2"); // 13
        c.calculate("7*2*2-5+1-5+3-4"); // 18
        c.calculate("70+2*6-4"); // 这里算出来的是8，显然是错的，正确的是78
    }

    class ArrayStack {
        private int[] stack; // 数组，数组模拟栈，数据就放在该数组
        private int top; // top表示栈顶，初始化为-1

        public ArrayStack() {
            this(10);
        }

        // 构造器
        public ArrayStack(int capacity) {
            stack = new int[capacity];
            top = -1;
        }

        // 栈的容量
        public int getCapacity() {
            return stack.length;
        }

        // 栈满
        public boolean isFull() {
            return top == getCapacity() - 1;
        }

        // 栈空
        public boolean isEmpty() {
            return top == -1;
        }

        // 入栈
        public void push(int e) {
            if (isFull()) {
                System.out.println("栈满，不能放入数据");
                return;
            }
            top++;
            stack[top] = e;
        }

        // 查看栈顶元素
        public int peek() {
            if (isEmpty()) {
                // 抛出异常
                throw new RuntimeException("栈空，没有数据");
            }
            return stack[top];
        }

        // 出栈，将栈顶的元素返回
        public int pop() {
            if (isEmpty()) {
                // 抛出异常
                throw new RuntimeException("栈空，不能取出数据");
            }
            int value = stack[top];
            top--;
            return value;
        }

        // 课程中的打印栈内元素
        public void list() {
            if (isEmpty()) {
                System.out.println("栈空，没有数据~");
                return;
            }
            // 需要从栈顶开始显示数据
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d] = %d\n", i, stack[i]);
            }
        }

        // 打印栈内元素（自己的写法，直接利用toString来构造打印格式）
        // 注意：遍历时，需要从栈顶开始显示数据
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("top:");
            for (int i = top; i >= 0; i--) {
                sb.append(stack[i]);
                if (i != 0) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }
}
