### 数组队列
队列也是一种线性结构  
相比数组，队列对应的操作是数组的子集  
只能从一端（队尾）添加元素，只能从另一端（队首）取出元素  

队列是一种先进先出的数据结构（先到先得）  
First In First Out（FIFO）

```text
Stack<E>
void enqueue(E) 向队尾中添加一个元素（入队）
E dequeue() 从队首拿出栈顶的元素（出队）
E getFront() 看下队首的元素是什么
int getSize() 一共有多少个元素
boolean isEmpty() 判断队列是否为空
```