package com.lyyco.rays.service.leetcode;

/**
 * Author liyangyang
 * 2018/7/9
 */
public class StringSolution {
    public int numJewelsInStones(String J, String S) {
       String[] slist = S.split("");
       int i = 0;
       for(String s : slist){
            if(J.indexOf(s) > -1){
                i++;
            }
       }
       return i;
    }

}
