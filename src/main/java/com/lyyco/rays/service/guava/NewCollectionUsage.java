package com.lyyco.rays.service.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Guava introduces a number of new collection types
 * that are not in the JDK,
 * but that we have found to be broadly useful.
 * These are all designed to coexist happily with the JDK collections framework,
 * without shoehorning things into the JDK collection abstractions
 * Author liyangyang
 * 2018/6/8
 */
public class NewCollectionUsage {

    public static void main(String...args){
        /*
        New collection types introduced by Guava don't expose raw constructors,
        or have initializers in the utility classes.
        Instead, they expose static factory methods directly
         */
        Multiset<String> multiset = HashMultiset.create();

    }

}
