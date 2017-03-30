package test;

import java.io.*;

/**
 * Created by qiuguomeng on 2017/3/29.
 */
public class Test07 {
    public static String fileInName = "C:\\Users\\qiuguomeng\\Desktop\\临时\\01\\海报0108.jpg";
    public static String fileOutName = "C:\\Users\\qiuguomeng\\Desktop\\临时\\0101.jpg";
    public static void main(String[] args) {
        long fileLength = 0;
        try {
            File fileOut = new File(fileOutName);
            if (fileOut.exists()) {
                fileLength = fileOut.length();
            }else{
                fileOut.createNewFile();
            }
            RandomAccessFile rafIn = new RandomAccessFile(fileInName,"r");
            RandomAccessFile rafOut = new RandomAccessFile(fileOut,"rw");
            if (fileLength != 0) {
                rafIn.seek(fileLength);
                rafOut.seek(fileLength);
            }
            byte[] bytes02 = new byte[1024];
            int len = 0;
            while ( -1 != (len = rafIn.read(bytes02))){
                rafOut.write(bytes02,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(true && false);
//        try {
//            FileInputStream fileInputStream = new FileInputStream(fileIn);
//            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\qiuguomeng\\Desktop\\临时\\0101.jpg");
//            int len = 0;
//            long current = System.currentTimeMillis();
//            byte[] bytes01 = new byte[1024];
//            while (-1 != (len = fileInputStream.read(bytes01)) && System.currentTimeMillis() - current < 100) {
//                fileOutputStream.write(bytes01,0,len);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
