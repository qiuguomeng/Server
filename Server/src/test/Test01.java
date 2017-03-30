package test;

import com.sun.media.jfxmedia.track.Track;

import java.io.*;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Test01 {
    private static String fileName = "C:\\Users\\Administrator\\Desktop\\010101.txt";
    public static void main(String args[]) {
        try {
//            System.out.print(MyUtil.codeString(fileName));
            FileInputStream fileInputStream = new FileInputStream(fileName);
            byte[] b = new byte[2];
            fileInputStream.read(b);
            System.out.println(new String(b, MyUtil.codeString(fileName))/*.toCharArray()[0]*/ + "000");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
