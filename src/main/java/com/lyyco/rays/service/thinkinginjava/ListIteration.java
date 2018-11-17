package com.lyyco.rays.service.thinkinginjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Author liyangyang
 * 2018/11/17
 */
public class ListIteration {
    public static void main(String[] args){
        List<String> pets = new ArrayList<>(Arrays.asList("dog","cat","pig"));
        ListIterator<String> it = pets.listIterator();
        while (it.hasNext()){
            System.out.println(it.next()+","+it.nextIndex()+","+it.previousIndex()+";");

        }
        it = pets.listIterator(2);
        while (it.hasNext()){
            it.next();
        }

    }
}
