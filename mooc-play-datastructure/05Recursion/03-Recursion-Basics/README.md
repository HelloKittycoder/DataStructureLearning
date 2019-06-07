### 5-3 递归基础与递归的宏观语义
```text
递归
1. 本质上，是将原来的问题，转化为更小的同一问题
2. 举例：数组求和
Sum(arr[0...n-1]) = arr[0] + Sum(arr[1...n-1]) 更小的同一问题
Sum(arr[1...n-1]) = arr[1] + Sum(arr[2...n-1]) 更小的同一问题
......
Sum(arr[n-1...n-1]) = arr[n-1] + Sum(arr[]) 最基本的问题

public static int sum(int[] arr, int l) {
    if (l == arr.length)
        return 0;
    return arr[l] + sum(arr, l + 1);
}
```