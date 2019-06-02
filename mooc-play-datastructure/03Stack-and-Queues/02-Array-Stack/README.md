### 栈的实现
```text
Stack<E>
void push(E) 向栈中添加一个元素（入栈）
E pop() 从栈中拿出栈顶的元素（出栈）
E peek() 看下栈顶的元素是什么
int getSize() 一共有多少个元素
boolean isEmpty() 判断栈是否为空
```

从用户的角度看，支持这些操作就好  
具体底层实现，用户不关心  
实际底层有多种实现方式