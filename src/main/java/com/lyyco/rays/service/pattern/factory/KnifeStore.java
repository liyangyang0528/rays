package com.lyyco.rays.service.pattern.factory;

/**
 * Author liyangyang
 * 2019/1/13
 */
public abstract class KnifeStore {

    public Knife orderKnife(String knifeType){
        Knife knife;
        //now creating a knife is a method in the class knife = createKnife(knifeType);
        knife = createKnife(knifeType);
        knife.sharpen();
        knife.polish();
        return knife;
    }

    protected abstract Knife createKnife(String knifeType);
}
