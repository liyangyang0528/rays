package com.lyyco.rays.service.leetcode;

/**
 * Author liyangyang
 * 2018/7/9
 */
public class StringSolution {
    public int numJewelsInStones(String J, String S) {
        String[] slist = S.split("");
        int i = 0;
        for (String s : slist) {
            if (J.indexOf(s) > -1) {
                i++;
            }
        }
        return i;
    }

    //Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
    public String toLowerCase1(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i) | 32);
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        StringSolution stringSolution = new StringSolution();
        stringSolution.toLowerCase1("ABCDEFG");
    }
}
