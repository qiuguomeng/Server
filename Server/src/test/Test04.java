package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/18.
 */
public class Test04 {
    public static String command = Byte.toString((byte)1)+"<start>read:C:\\Users\\Administrator\\Desktop\\04<end>";
//    public static String command = Byte.toString((byte)1)+"<start>mkDir:C:\\Users\\Administrator\\Desktop\\04<end><start>delete:C:\\Users\\Administrator\\Desktop\\02\\delete<end><start>move:C:\\Users\\Administrator\\Desktop\\02\\新建文本文档.txt,C:\\Users\\Administrator\\Desktop\\02\\02\\新建文本文档.txt<end>";
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            OutputStream outputStream = socket.getOutputStream();
//            System.out.println(command.getBytes()[0]);
            outputStream.write(command.getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            String file = MyUtil.CommandProcess(inputStream, "UTF-8");
            System.out.println(file);
            String[] fileList = file.split(",");
            for (String a : fileList) {
                System.out.println(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
