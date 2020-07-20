package leonc.cloud_disk.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class RenameFile
{
    //TODO 现在上传中文会报错，无法改名
    public static void rename(String filePath, String newPath) {
        BufferedReader bufR = null;
        BufferedWriter bufW = null;
        try {
            bufR = new BufferedReader(new FileReader(new File(filePath)));
            bufW = new BufferedWriter(new FileWriter(new File(newPath)));
            String line;
            while ((line = bufR.readLine()) != null) {
                bufW.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufW.close();
                bufR.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //判断是不是一个文件
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return false;
        } else if (file.isFile()) {
            return true;
        } else {
            return false;
        }
    }
    //切割生成新路径
    public static String newPath(String filePath) {
        int temp = filePath.lastIndexOf('.');
        int length = filePath.length();
        String newPath = filePath.substring(0, temp) + "2" + filePath.substring(temp, length);
        return newPath;
    }
    //不切割利用renameTo进行重命名
    public static void reName(String filePath) {
        String newPath = filePath + "2";
        boolean flag = new File(filePath).renameTo(new File(newPath));
        System.out.println(flag);
    }
}
