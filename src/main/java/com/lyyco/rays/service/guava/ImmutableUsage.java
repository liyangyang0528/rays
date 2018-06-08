package com.lyyco.rays.service.guava;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * Author liyangyang
 * 2018/6/8
 */
public class ImmutableUsage {
    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red", "orange", "yellow"
    );
    public static final ImmutableSet<String> GOOGLE_COLOR =
            ImmutableSet.<String>builder()
                    .addAll(COLOR_NAMES)
                    .add("blue")
                    .build();


    final ImmutableSet<String> bars;

    public ImmutableUsage(Set<String> bars) {
        this.bars = ImmutableSet.copyOf(bars);
    }

    public static void main(String... args) {
        /*
        All immutable collections provide an ImmutableList view via asList(),
        so -- for example -- even if you have data stored as an ImmutableSortedSet,
        you can get the kth smallest element with sortedSet.asList().get(k).
         */
        String red = COLOR_NAMES.asList().get(0);
        System.out.println(red);
    }


}
