package com.sndj.recipe.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yuechao 2018/5/4
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:\\tmp\\en.java", "rw");
        FileChannel inChannel = aFile.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(20);
        buf.limit(10);

        System.out.println(buf);

        int bytesRead = inChannel.read(buf); //read into buffer.
        System.out.println(buf);
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            System.out.println(buf);
            StringBuilder sb = new StringBuilder();
            while(buf.hasRemaining()){
                sb.append((char) buf.get()); // read 1 byte at a time
            }
            System.out.println(sb.toString());

            buf.clear(); //make buffer ready for writing
            System.out.println(buf);
            bytesRead = inChannel.read(buf);
            System.out.println(buf);
        }
        aFile.close();
    }
}
