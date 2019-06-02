/**
 * Created by shucheng on 2019-6-2 下午 22:31
 * 用自己实现的ArrayStack来求解Leetcode问题
 */
class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) // 输入的String为" "
                    return false;

                char topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("()[]{}")); // true
        System.out.println(s.isValid("[(]]")); // false
        System.out.println(s.isValid(" ")); // false
    }
}