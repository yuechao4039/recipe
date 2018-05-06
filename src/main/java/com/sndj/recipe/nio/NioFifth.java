package com.sndj.recipe.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yuechao 2018/5/6
 */
public class NioFifth {

    public static void main(String[] args) {
        try {
            map();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void fis() {
        long start = System.currentTimeMillis();
        // 4.5 G = 4 * 1024 M = 4.5 * 1024 * 1024 KB =
        String str = "a";
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1024; i++) {
            sb.append(str);
        }
        System.out.println(sb.toString().getBytes().length);

        byte[] bytes1024 = sb.toString().getBytes();
        String str1024 = sb.toString();

        int times = (int)(1024 * 1024 * 1);

        try (FileOutputStream fos = new FileOutputStream("D://tmp//fos.txt")) {
            for (int i = 0; i < times; i++) {
                fos.write(bytes1024);
            }
        } catch (Exception e) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // 5509 ms
    }

    public static void nio() {
        long start = System.currentTimeMillis();
        // 4.5 G = 4 * 1024 M = 4.5 * 1024 * 1024 KB =
        String str = "a";
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1024; i++) {
            sb.append(str);
        }
        System.out.println(sb.toString().getBytes().length);

        byte[] bytes1024 = sb.toString().getBytes();
        String str1024 = sb.toString();

        int times = (int)(1024 * 10 * 1);

        try (FileOutputStream fos = new FileOutputStream("D://tmp//br.txt")) {
            FileChannel fc = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 100);
            for (int i = 0; i < times; i++) {
                for (int j = 0; j < 100; j++) {
                    byteBuffer.put(bytes1024);
                }
                byteBuffer.flip();
                fc.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (Exception e) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // 5298 ms  1182ms
    }


    public static void br() {
        long start = System.currentTimeMillis();
        // 4.5 G = 4 * 1024 M = 4.5 * 1024 * 1024 KB =
        String str = "a";
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1024; i++) {
            sb.append(str);
        }
        System.out.println(sb.toString().getBytes().length);

        byte[] bytes1024 = sb.toString().getBytes();
        String str1024 = sb.toString();

        int times = (int)(1024 * 1024 * 1);

        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://tmp//br.txt")))) {
            for (int i = 0; i < times; i++) {
                br.write(str1024);
            }
        } catch (Exception e) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // 2063 ms
    }


    public static void map() throws IOException {
        long start = System.currentTimeMillis();
        String str = "a";
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1024; i++) {
            sb.append(str);
        }
        System.out.println(sb.toString().getBytes().length);

        byte[] bs = sb.toString().getBytes();

        RandomAccessFile acf = new RandomAccessFile(new File("D://tmp//br.txt"), "rw");
        FileChannel fc = acf.getChannel();

        int len = bs.length * 1000;
        long offset = 0;
        int i = 2000000;
        while(i > 0) {
            MappedByteBuffer mbuf = fc.map(FileChannel.MapMode.READ_WRITE, offset, len );
            for(int j = 0; j < 1000; j ++) {
                mbuf.put(bs);
            }
            offset = offset + len;
            i = i - 1000;
        }
        fc.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
