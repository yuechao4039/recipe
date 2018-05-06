package com.sndj.recipe.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author yuechao 2018/5/6
 */
public class Demo1 {

    public static void main(String[] args) {

        String originalPath =
                "d:\\data\\projects\\a-project\\..\\another-project";

        Path path1 = Paths.get(originalPath);
        System.out.println("path1 = " + path1);

        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
    }
}
