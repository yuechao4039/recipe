package com.sndj.recipe.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author yuechao 2018/5/6
 */
public class NioSecond {

    /**
     * 在JAVA中，ByteBuffer采用大头字节顺序进行存储，即最低字节保存在低位地址中，最高字节保存在高位地址中，证明的过程如下：
     1. 首先存入汉字“你好”，UTF-8编码为“\u4F60\u597D”；
     2. 依次读出每个字节，并输出16进制形式；
     3. 比较16进制与UTF-8编码；
     */
    public static void main(String[] args) {

        //  字符缓冲区
        CharBuffer charBuf = CharBuffer.allocate(10);
//  '你'等价于'\u4F60'，'好'等价于'\u597D'
        charBuf.put('你');
        charBuf.put('好');
//  复位
        charBuf.flip();

//  二进制缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        int index = 0;
        while(index < charBuf.limit()) {
            byteBuffer.putChar(charBuf.get());
            index ++;
        }
//  复位
        byteBuffer.flip();

//  输出结果
        while(byteBuffer.hasRemaining()) {
            System.out.print(Integer.toHexString(byteBuffer.get()) + "\t");
        }
    }
}
