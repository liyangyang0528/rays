package com.lyyco.rays.service.lambda;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 开发一个简单的方法来数数一个String中的单词数
 * 实现自定义的可拆分迭代器
 * Author liyangyang
 * 2018/4/3
 */
public class MySpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public MySpliterator(String string) {
        this.string = string;
    }

    public static void main(String... args) {

        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        MySpliterator my = new MySpliterator(SENTENCE);
        //首先你需要把String转换成一个流。
        // 不幸的是，原始类型的流仅限于int、 long和double，
        //所以你只能用Stream<Character>
        Stream<Character> stream = IntStream.range(0,SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found_"+my.countWordsIteratively(SENTENCE));
//        System.out.println("Found_"+my.countWords(stream)+"words");
        System.out.println("Found_"+my.countWords(stream.parallel())+"words");
        /*
        在这里使用并行流计算的结果是错的
        因为原始的String在任意
        位置拆分，所以有时一个词会被分为两个词，然后数了两次。这就说明，拆分流会影响结果，而
        把顺序流换成并行流就可能使结果出错
         */
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> streamNew = StreamSupport.stream(spliterator,true);
        System.out.println("Found"+my.countWords(streamNew) + "words");
    }

    /**
     * 1.使用顺序迭代
     * @param s
     * @return
     */
    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                /*
                上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
                 */
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    /*********************以函数式风格重写单词计数器****************************************/
    /**
     * 类WordCounter来把这个状态封装起来
     */
    class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            } else {
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }
        //合并两个wordcounter，把两个计数器加起来
        public WordCounter combine(WordCounter wordCounter){
            return new WordCounter(counter + wordCounter.counter,
                    wordCounter.lastSpace);
        }

        public int getCounter(){
            return counter;
        }
    }
    /***************实现可拆分迭代器**************************************/

    /**
     * @param stream
     * @return
     */
    public int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0,true),
                WordCounter::accumulate,WordCounter::combine);
        return wordCounter.getCounter();
    }

        /**
         * tryAdvance方法把String中当前位置的Character传给了Consumer，并让位置加1
         * @param action
         * @return
         */
        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            //处理当前字符
            action.accept(string.charAt(currentChar++));
            //是否还有要处理的字符
            return currentChar < string.length();
        }

        /**
         * trySplit方法是Spliterator中最重要的一个方法，因为它定义了
         * 拆分要遍历的数据结构的逻辑
         *
         * @return
         */
        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize < 10) {
                //返回Null表示要解析的string已经足够小，可以顺序处理
                return null;
            }
            for (int splitPos = currentSize / 2 + currentChar;
                //将试探拆分位置设定为要解析的string的中间
                 splitPos < string.length(); splitPos++) {
                //让拆分位置前进直到下一个空格
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    //创建一个新的，解析string从开始到拆分位置的部分
                    Spliterator<Character> spliterator =
                            new MySpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }

            }
            return null;
        }

        /**
         * estimatedSize就是这个Spliterator解析的String的总长度和
         * 当前遍历的位置的差
         *
         * @return
         */
        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        /**
         * characteristic方法告诉框架这个Spliterator是ORDERED（顺序就是String
         * 中 各 个Character 的 次 序 ）、 SIZED （estimatedSize 方 法 的 返 回 值 是 精 确 的 ）、
         * SUBSIZED （trySplit方法创建的其他Spliterator也有确切大小）、 NONNULL （String
         * 中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
         * Character，因为String本身是一个不可变类）
         *
         * @return
         */
        @Override
        public int characteristics() {

            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
