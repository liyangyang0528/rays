package com.lyyco.rays.service.thinkinginjava;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * page 561
 * 交换相邻字符，以对CharBuffer中的字符进行编码和译码
 * @Author li yang yang
 * 2018/12/4
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer){
        while (buffer.hasRemaining()){
            //将mark设置成position的当前值
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            //把position的值设为mark的值
            buffer.reset();
            //交换c1,c2的位置
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        System.out.println(cb.rewind());
        symmetricScramble(cb);
        /*
        在while循环的最后，position指向缓冲器的末尾
        如果要打印缓冲器，只能打印position和limit之间的字符
        因此，如果想显示缓冲器的全部内容
        必须使用rewind()把position设置到缓冲器的开始位置
         */
        System.out.println(cb.rewind());
        symmetricScramble(cb);
        System.out.println(cb.rewind());
    }
}
