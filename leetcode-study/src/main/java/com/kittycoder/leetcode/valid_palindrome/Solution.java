/**
 * Leetcode - valid_palindrome
 */
package com.kittycoder.leetcode.valid_palindrome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    boolean isPalindrome(String s);
}
