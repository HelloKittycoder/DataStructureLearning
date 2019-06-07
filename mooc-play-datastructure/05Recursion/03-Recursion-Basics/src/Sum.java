/**
 * Created by shucheng on 2019-6-7 下午 19:55
 */
public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    // 计算arr[l..n)这个区间内所有数字的和
    private static int sum(int[] arr, int l) {
        if (l == arr.length)
            return 0;
        // return arr[l] + sum(arr, l + 1);
        // 为方便理解，把上面一行拆成两句话
        int x = sum(arr, l + 1);
        int res = arr[l] + x;
        return res;
    }

    public static void main(String[] args) {
        /*System.out.println(sum(new int[]{1, 2, 3, 4, 5}));*/
        System.out.println(sum(new int[]{6, 10}));
    }
}
