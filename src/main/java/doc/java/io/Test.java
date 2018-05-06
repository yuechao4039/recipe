package doc.java.io;



import lombok.experimental.var;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * @author yuechao 2018/5/5
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException {

        byte a = -128;
        System.out.println(a & 0xff);
        byte b = 127;
        System.out.println(b & 0xff);

        byte c = -1;
        // 0000 0001
        // 1111 1110
        // 1111 1111
        System.out.println(c & 0xff);
        System.out.println(Integer.toBinaryString(a & 0xff));

        System.out.println(Integer.toBinaryString(0xff));

        System.out.println("============");
        byte d = -17;
        System.out.println(d);
        System.out.println((int) d);
        System.out.println(d & 0xff);
        System.out.println(Integer.toBinaryString(d));
        System.out.println(Integer.toBinaryString(d & 0xff));
        // 8 位
        // 0001 0001 (17原码)
        // 1110 1110 (17反码)
        // 1110 1111 (17补码)计算中表示 -17
        // 24个零 0001 0001 (17原码)
        // 24个一 1110 1110 (17原码)
        // 24个一 1110 1111 (17原码)


    }
}

abstract class AA {

}
