package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/18.
 */
public class TestPush {
    private static final String HOST = "localhost";
//    private static final String HOST = "118.89.50.173";
    private static String command = "1<start>push:C:\\Users\\qiuguomeng\\Desktop\\临时\\海报0108.jpg,"+String.valueOf(35725312)+"<end>";//String类型“1”的编码为49
//    private static String command1 = "<start>delete:C:\\010101.txt<end>";
//    private static String command = "1<start>push:C:\\eclipse-java-neon-2-win32-x86_64.zip<end>";
    public static void main(String[] args) {
        try {
//            Socket socket1 = new Socket("localhost", 3000);
            Socket socket1 = new Socket(HOST, 3000);
            socket1.setKeepAlive(true);
            OutputStream outputStream1 = socket1.getOutputStream();
            InputStream inputStream1 = socket1.getInputStream();
            outputStream1.write(command.getBytes());
            outputStream1.flush();
//            outputStream1.write(command1.getBytes());
//            socket1.close();
//            outputStream1 = null;
//            socket1 = null;
//            Socket socket2 = new Socket("localhost", 3001);
            Socket socket2 = new Socket(HOST, 3001);
            OutputStream outputStream2 = socket2.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\qiuguomeng\\Desktop\\临时\\01\\海报0108.jpg");
            int len = 0;
            byte[] bytes = new byte[2048];
            while (-1 != (len = fileInputStream.read(bytes))) {
                outputStream2.write(bytes, 0, len);
                outputStream2.flush();
            }
            outputStream2.close();
            fileInputStream.close();
            socket2.close();
            System.out.println(MyUtil.CommandProcess(inputStream1,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
