package com.lyyco.rays.service.pattern.factory;

/**
 * Author liyangyang
 * 2019/1/13
 */
public class BudgetKnifeStore extends KnifeStore{
    @Override
    protected Knife createKnife(String knifeType) {
        if("default".equals(knifeType)){
            return new Knife();
        }
        return null;
    }
}
