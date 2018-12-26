package com.lyyco.rays.service.swordsman;

/**
 * Author liyangyang
 * 2018/12/20
 */
public class ReplaceSpaces {
    // 面试题5：替换空格
    // 题目：请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，
    // 则输出“We%20are%20happy.”
    public static void replaceBlank(char[] str) {
        if (null == str || str.length <= 0) {
            return;
        }
        //字符串str的实际长度
        int originalLength = 0;
        int numberOfBlank = 0;
        int i = 0;
        while (i < str.length) {
            ++originalLength;
            if (' ' == (str[i])) {
                ++numberOfBlank;
            }
            ++i;
        }
        //把空格替换就成 %20之后的长度
        int newLength = originalLength + numberOfBlank * 2;
        if (newLength > str.length) {
            return;
        }
        int indexOfOriginal = originalLength;
        int indexOfNew = newLength;

        while (indexOfOriginal >= 0 && indexOfNew > indexOfOriginal) {
            if (' ' == (str[indexOfOriginal])) {
                str[indexOfNew--] = '0';
                str[indexOfNew--] = '2';
                str[indexOfNew--] = '%';
            } else {
                str[indexOfNew--] = str[indexOfOriginal];
            }
            --indexOfOriginal;
        }
    }
}
