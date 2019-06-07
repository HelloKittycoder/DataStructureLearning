### 链表的时间复杂度分析
```text
添加操作 O(n)
addLast(e) O(n)
addFirst(e) O(1)
add(index, e) O(n/2)=O(n)
```

```text
删除元素 O(n)
removeLast() O(n)
removeFirst() O(1)
remove(index) O(n/2)=O(n)
```

```text
修改操作 O(n)
set(index, e) O(n)
```

```text
查找操作 O(n)
get(index) O(n)
contans(e) O(n)
另外，链表不提供像动态数组那样的find(e)方法（根据元素返回索引，O(n)）；
因为即使实现了，链表拿到索引后依然需要遍历，无法像动态数组那样进行快速的随机访问
```