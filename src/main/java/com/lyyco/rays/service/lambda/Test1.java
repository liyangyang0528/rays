package com.lyyco.rays.service.lambda;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lyy on 2018/1/15.
 */
public class Test1 {
    public static void main(String[]args){
        List<String> names = Lists.newArrayList();
        names.add("LYY");
        names.add("TTT");
//        List<String> lower1 = FluentIterable.from(names).transform(new Function<String, T>() {
//            @Nullable
//            @Override
//            public T apply(@Nullable String s) {
//                return s.toLowerCase();
//            }
//        });
        List<String> lower2 = names.stream().map((name)-> {return name.toLowerCase();}).collect(Collectors.toList());

    }


}
