package com.kittycoder.leetcode.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by shucheng on 2021/8/26 22:28
 */
public class FileUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 将文件内容加载成字符串
     * @param filePath 相对于target/classes或target/test-classes的路径
     *                 这里写/返回的是null，另外也不能以/开头
     * @return
     */
    public static String loadFiletoString(String filePath) {
        URL resource = FileUtil.class.getClassLoader().getResource(filePath);
        FileInputStream fis = null;
        try {
            File file = new File(resource.toURI());
            fis = new FileInputStream(file);
            byte[] data = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (fis.read(data) != -1) {
                sb.append(new String(data));
            }
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // largeArray.txt能找到
        String s = loadFiletoString("largeArray.txt");
        // testFile.txt找不到（当前不是从test/java启动的，没有加载test/resources）
        // String s = loadFiletoString("testFile.txt");
        System.out.println(s);
    }
}
