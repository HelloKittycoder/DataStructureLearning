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
        for (int i = 0; i < calcStr.length(); i++) {
            char c = calcStr.charAt(i);
            if (isNumber(c)) {
                numberStack.push(getNumber(c));
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
            int b = (int) numberStack.pop();
            int a = (int) numberStack.pop();
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
        // c.calculate("70+2*6-4"); // 这里算出来的是8，显然是错的
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
