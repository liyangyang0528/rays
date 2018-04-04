package com.lyyco.rays.service.lambda;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 实现自定义的可拆分迭代器
 * 开发一个简单的方法来数数一个String中的单词数
 * Created by lyy on 2018/3/5.
 */
public class WordCounterSpliterator implements Spliterator<Character> {


    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        //处理当前字符
        action.accept(string.charAt(currentChar++));
        //如果还有字符要处理，则返回true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            //返回null表示要解析的string已经足够小，可以顺序处理
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar;
            //试探拆分位置设定为要解析的string的中间
             splitPos < string.length(); splitPos++) {
            //让拆分位置前进直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {
                //创建一个新的WordCounter来解析string从开始到拆分位置的部分
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                //将这个wordCounter的起始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
