### 2-7 动态数组
当数组data容量不够用的时候，需要进行扩容  
思路：  
1. 再创建一个数组newData，容量为原来的2倍  
2. 将data中的所有元素挨个复制到新的数组newData中  
3. size不用变化，容量为newData的length（只需要将原有数组指向新的数组newData即可）