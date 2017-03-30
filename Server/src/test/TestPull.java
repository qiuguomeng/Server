package test;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/18.
 */
public class TestPull {
    private static String command = "1<start>pull:C:\\Users\\qiuguomeng\\Desktop\\临时\\01\\海报0108.jpg<end>";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream1 = socket.getInputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
            Socket socket1 = new Socket("localhost", 3001);
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\qiuguomeng\\Desktop\\临时\\海报0108.jpg");
            InputStream inputStream = socket1.getInputStream();
            int len = 0;
            byte[] bytes = new byte[2048];
            while (-1 != (len = inputStream.read(bytes))) {
                fileOutputStream.write(bytes, 0, len);
                fileOutputStream.flush();
            }
            System.out.println(MyUtil.CommandProcess(inputStream1,"UTF-8"));//接受反馈应在文件传输完成之后，否则会阻塞线程
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
