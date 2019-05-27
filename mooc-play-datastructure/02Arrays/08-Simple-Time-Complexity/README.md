### 2-8 简单的复杂度分析
O(1), O(n), O(lgn), O(nlogn), O(n^2)  

大O描述的是算法的运行时间和输入数据之间的关系  
```java
public static int sum(int[] nums) {
    int sum = 0;
    for (int num:nums) sum += num;
    return sum;
}
```
O(n)中n是nums中的元素个数，O(n)表示算法和n呈线性关系  

T = 2*n + 2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O(n)  
T = 2000*n + 2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O(n)  
T = 1*n^2 + 2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O(n^2)  
T = 2*n^2 + 300 * n + 10 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O(n^2)  
O(n)准确的说，是指渐进时间复杂度，描述n趋近于无穷的情况

#### 分析动态数组的时间复杂度
1. 添加操作（整体上是O(n)，通常取最坏的情况）  
addLast(e)  &nbsp;&nbsp;O(1)  
addFirst(e)  &nbsp;&nbsp;O(n)  
add(index, e)  &nbsp;&nbsp;O(n/2)=O(n)  
情形三的计算过程如下：  
![](https://latex.codecogs.com/gif.latex?\int_{0}^{n}(\frac{1}{n}(n-x))dx=\int_{0}^{n}(1-\frac{x}{n})dx=\frac{n}{2})  
resize  &nbsp;&nbsp;O(n)  
2. 删除操作（整体上是O(n)）  
removeLast(e)  &nbsp;&nbsp;O(1)  
removeFirst(e)  &nbsp;&nbsp;O(n)  
remove(index, e)  &nbsp;&nbsp;O(n/2)=O(n)  
resize  &nbsp;&nbsp;O(n)  
3. 修改操作  
set(index, e)  &nbsp;&nbsp;O(1)  
4. 查询操作  
get(index)  &nbsp;&nbsp;O(1)  
contains(e)  &nbsp;&nbsp;O(n)  
find(e)  &nbsp;&nbsp;O(n)  
5. 总结  
增：O(n)  
删：O(n)  
改：已知索引O(1)，未知索引O(n)  
查：已知索引O(1)，未知索引O(n)  

上面编写数学公式的参考链接：  
https://www.jianshu.com/p/c169599726e1  
https://my.oschina.net/taadis/blog/2209642
