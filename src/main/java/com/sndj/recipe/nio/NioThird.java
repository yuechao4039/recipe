package com.sndj.recipe.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author yuechao 2018/5/6
 */
public class NioThird {
    public static void main(String[] args) throws IOException {

//        ByteBuffer buffer = ByteBuffer.allocate(100);
////  获取缓冲区的视图，但与ByteBuffer的mark、position、limit互相独立
//        CharBuffer charBuff = buffer.asCharBuffer();
////  更容易进行字符操作
//        charBuff.put("Hello, World!");
////  创建输出通道
//        WritableByteChannel outChannel = Channels.newChannel(System.out);
////  写入缓冲区数据
//        outChannel.write(buffer);
//        outChannel.close();

        //  创建输出通道
//        WritableByteChannel outChannel = Channels.newChannel(System.out);
//        String text = "你好,JAVA!";
//        byte[] arr = text.getBytes("UTF-8");
//        ByteBuffer byteBuf = ByteBuffer.wrap(arr);
//        outChannel.write(byteBuf);
//        outChannel.close();


//        FileInputStream fis = new FileInputStream(file);
//        FileChannel fc = fis.getChannel();
//        ByteBuffer bbuf = ByteBuffer.allocateDirect(1024);
//        long offset = 0;
//        long nums = 0;
//        while((nums = fc.read(bbuf, offset)) > 0) {
//            //  一定要反转回到正确位置，CharBuffer视图是基于当前位置创建的
//            bbuf.flip();
//            //  以字符数据方式进行读取
//            CharBuffer cbuf = bbuf.asCharBuffer();
//            bbuf.clear();
//            offset = offset + nums;
//        }
//        fc.close();
//        fis.close();

    }
}
