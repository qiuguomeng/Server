package test;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/18.
 */
public class RandomAccessFileTest02 {
    private static final String HOST = "localhost";
    private static final String LOCALFILENAME = "D:\\dell\\文档\\中文版 - v1.1.pdf";
    //    private static final String HOST = "118.89.50.173";
    public static void main(String[] args) {
        try {
            File fileIn = new File(LOCALFILENAME);
            long localFileLength = 0;
            if (fileIn.exists()){
                localFileLength = fileIn.length();
            }
            String command = "1<start>pull:D:\\dell\\文档\\Material Design 中文版 - v1.1.pdf,"+String.valueOf(localFileLength)+"<end>";
            Socket socket01 = new Socket(HOST, 3000);
            OutputStream outputStream01 = socket01.getOutputStream();
            outputStream01.write(command.getBytes());
            Socket socket02 = new Socket(HOST,3001);
            InputStream inputStream02 = socket02.getInputStream();
            int len = 0;
            byte[] bytes01 = new byte[2048];
            if (localFileLength == 0) {
                FileOutputStream fileOutputStream = new FileOutputStream(fileIn);
                long current = System.currentTimeMillis();
                while(-1 != (len = inputStream02.read(bytes01)) && System.currentTimeMillis() - current < 100){
                    long currentTime = System.currentTimeMillis();
                    fileOutputStream.write(bytes01,0,len);
                    System.out.println(((System.currentTimeMillis() - currentTime)/1000) / (len/1024)+"kb\\s");
                }
                fileOutputStream.close();
            }else if (localFileLength > 0 ){
                System.out.println("localFileLength:"+localFileLength);
                RandomAccessFile randomAccessFile = new RandomAccessFile(fileIn,"rw");
                randomAccessFile.seek(localFileLength);
                while (-1 != (len = inputStream02.read(bytes01))) {
                    long currentTime = System.currentTimeMillis();
                    randomAccessFile.write(bytes01,0,len);
                    System.out.println(((System.currentTimeMillis() - currentTime)/1000) / (len/1024)+"kb\\s");
                }
                randomAccessFile.close();
                inputStream02.close();
            }
            socket01.close();
            socket02.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
