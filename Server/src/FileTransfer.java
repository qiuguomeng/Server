import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/17.
 */
public class FileTransfer {

    private static ServerSocket serverSocket = null;

    public static FileTransfer fileTransfer = null;

    private static final byte PUSH = 3;

    private static final byte PULL = 4;

    private FileTransfer(){}

    public void execute(String fileName, byte pushOrPull,long fileTranferLength,FileTransfer.Feedback feedback) {
        try {
            Socket socket = serverSocket.accept();
            new FileTransferThread(socket,fileName,pushOrPull,fileTranferLength,feedback).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FileTransfer getInstance() {
        if (fileTransfer == null) {
            fileTransfer = new FileTransfer();
        }
        if (serverSocket == null) {
            try {
                serverSocket = new ServerSocket(3001);
            } catch (IOException e) {
                System.out.println("serverSocket创建失败");
                e.printStackTrace();
            }
        }
        return fileTransfer;
    }

    private class FileTransferThread extends Thread {

        private Socket socket = null;
        
        private byte pushOrPull = 3;

        private long fileLong;
        
        private String fileName = "";

        private FileTransfer.Feedback feedback = null;
        
        public FileTransferThread(Socket socket,String fileName,byte pushOrPull,long fileLong,FileTransfer.Feedback feedback) {
            this.socket = socket;
            this.pushOrPull = pushOrPull;
            this.fileName = fileName;
            this.feedback = feedback;
            this.fileLong = fileLong;
        }
        
        @Override
        public void run() {
            super.run();
            try {
                if (pushOrPull == PUSH && fileLong != 0){
                    feedback.feedback(FileUtil.push(fileName, socket.getInputStream(),fileLong));
                }else if (pushOrPull == PUSH && fileLong == 0) {
                    feedback.feedback(FileUtil.push(fileName, socket.getInputStream()));
                    socket.close();
                } else if (pushOrPull == PULL) {
                    if (fileLong == 0) {
                        feedback.feedback(FileUtil.pull(fileName, socket.getOutputStream()));
                    } else if (fileLong != 0) {
                        feedback.feedback(FileUtil.pull(fileName,fileLong, socket.getOutputStream()));
//                        System.out.println("FileTransfer:FileUtil.pull Run"+fileLong);
                    }
                    socket.close();
                } else {
                        socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    public interface Feedback {
        public void feedback(boolean isSuccessful);
    }
}
