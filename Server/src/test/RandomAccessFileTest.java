package test;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/18.
 */
public class RandomAccessFileTest {
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
            byte[] bytes01 = new byte[8];
            int bytesLength = inputStream1.read(bytes01);
            long serverFileLength = new Long(new String(bytes01, 0, bytesLength));
//            outputStream1.write(command1.getBytes());
//            socket1.close();
//            outputStream1 = null;
//            socket1 = null;
//            Socket socket2 = new Socket("localhost", 3001);
            Socket socket2 = new Socket(HOST, 3001);
            OutputStream outputStream2 = socket2.getOutputStream();
           if (serverFileLength == 0){
               FileInputStream fileInputStream = new FileInputStream("C:\\Users\\qiuguomeng\\Desktop\\临时\\01\\海报0108.jpg");
               int len = 0;
               byte[] bytes02 = new byte[2048];
               long current = System.currentTimeMillis();
               while (-1 != (len = fileInputStream.read(bytes02)) && System.currentTimeMillis() - current < 100) {
                   outputStream2.write(bytes02, 0, len);
                   outputStream2.flush();
               }
               fileInputStream.close();
           }else {
               RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\qiuguomeng\\Desktop\\临时\\01\\海报0108.jpg", "r");
               int len = 0;
               byte[] bytes03 = new byte[2048];
               while (-1 != (len = randomAccessFile.read(bytes03))) {
                   outputStream2.write(bytes03,0,len);
               }
               randomAccessFile.close();
           }
            outputStream2.close();
            socket2.close();
            System.out.println(MyUtil.CommandProcess(inputStream1,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
