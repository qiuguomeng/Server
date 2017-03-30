package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/18.
 */
public class Test05 {
    public static String command = "1<start>delete:C:\\010101.txt<end>";
//    public static String command = "1<start>delete:C:\\Users\\Administrator\\Desktop\\04<end>";
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("118.89.50.173", 3000);
//            Socket socket = new Socket("localhost", 3000);
            OutputStream outputStream = socket.getOutputStream();
            System.out.println(command.getBytes()[0]);
            outputStream.write(command.getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            String file = MyUtil.CommandProcess(inputStream, "UTF-8");
            System.out.println(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
