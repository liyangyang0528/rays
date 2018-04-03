package com.lyyco.rays.service.lambda;

import java.util.Optional;

/**
 * optional使用测试
 * Author liyangyang
 * 2018/4/3
 */
public class OptionalTest {
    //传统的方式判空，与！==null如出一辙
    public Optional<String> find(Optional<String> person,Optional<String> car){
        if(person.isPresent() && car.isPresent()){
            return Optional.of(find(person.get(),car.get()));
        }
        else {
            return Optional.empty();
        }
    }
    //optional方式
    public Optional<String> nullSafeFind(Optional<String> person,Optional<String> car){
        return person.flatMap(p -> car.map(c -> find(p,c)));
    }

    private <U> U find(String p, String c) {

        return null;
    }
}
