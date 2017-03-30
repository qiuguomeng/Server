import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Main {
    private static ServerSocket serverSocket = null;
    private static final byte TRANSFER_UNSUCCESSFUL = 0;
    private static final byte TRANSFER_SUCCESSFUL = 1;
    private static final byte FILE_ALREADY_EXIST = 2;
    private static final byte PUSH = 3;
    private static final byte PULL = 4;
    public static void main(String args[]){
//        new File("");
//        Ra
        try {
            serverSocket = new ServerSocket(3000);
            while (true) {
                new ServerThread(serverSocket.accept()).start();//开启命令交互线程
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerThread extends Thread implements FileTransfer.Feedback{
        private Socket socket;
        InputStream inputStream;
        OutputStream outputStream;
        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("run");
            try {
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                byte[] b1 = new byte[1];
                inputStream.read(b1);
                System.out.println(b1[0]);
                String command = null;
                if (b1[0] == 48) {
                    while (true) {
                        command = MyUtil.commandProcess(inputStream, "GBK");//返回<start><end>中的内容
                        if (null == command) {
                            break;
                        }
                        System.out.println(command);
                        execute(command);
                    }
                } else if (b1[0] == 49) {
                    while (true) {
                        command = MyUtil.commandProcess(inputStream, "UTF-8");//返回<start><end>中的命令
                        if (null == command) {
                            break;
                        }
                        System.out.println(command);
                        execute(command);
                    }
                }

            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void execute(String command) {
                if (command.contains("push")) {
                    String[] command01 = command.split(",");
                    String desFileName = command01[0].substring(command01[0].indexOf("push:")+5).trim();
                    long fileLength = Long.parseLong(command01[1]);
                    System.out.println("fileLength:"+fileLength);
                    long localFileLength = FileUtil.checkFile(desFileName);
                    if (fileLength == localFileLength) {
                        feedback1(true);
                    } else{
                        /*StringBuilder response = new StringBuilder();
                        response.append("<start>");
                        response.append(localFileLength+"<end>");*/
                        try {
//                            outputStream.write(response.toString().getBytes());
                            System.out.println("StringLength:"+String.valueOf(localFileLength).getBytes().length);
                            outputStream.write(String.valueOf(localFileLength).getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FileTransfer.getInstance().execute(desFileName,PUSH,fileLength,this);
                    }
                } else if (command.contains("pull")) {
                    String[] command01 = command.split(",");
                    String desFileName = command01[0].substring(command01[0].indexOf("pull:")+5).trim();
                    long startTranferLength = Long.parseLong(command01[1]);
                    FileTransfer.getInstance().execute(desFileName, PULL,startTranferLength, this);//开启子线程传输文件
                } else if (command.contains("read")) {//返回读取的目录的文件列表，为String类型，用“，”分割
                    String desFileName = command.substring(command.indexOf("read:")+5).trim();
                    String[] fileList1 = FileUtil.read(desFileName);
                    StringBuilder fileList2 = new StringBuilder();
                    fileList2.append("<start>");
                    for (int i = 0; i < fileList1.length; i++) {
                        fileList2.append(fileList1[i]);
                        if (fileList1.length != i + 1) {
                            fileList2.append(',');
                        }
                    }
                    fileList2.append("<end>");
                    try {
                        outputStream.write(fileList2.toString().getBytes());
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (command.contains("move")) {
                    String srcPath = command.substring(command.indexOf("move:") + 5, command.indexOf(',')).trim();
                    String desPath = command.substring(command.indexOf(',')+1).trim();
                    feedback1(FileUtil.move(srcPath, desPath));
                } else if (command.contains("delete")) {
                    String desFileName = command.substring(command.indexOf("delete:")+7).trim();
                    feedback1(FileUtil.delete(desFileName));
                } else if (command.contains("mkDir")) {
                    String desFileName = command.substring(command.indexOf("mkDir:")+6).trim();
                    feedback1(FileUtil.mkDirectory(desFileName));
                }
        }

        public void feedback1(boolean isSucessful) {
            String status = "";
            if (isSucessful) {
                status = "<start>" + TRANSFER_SUCCESSFUL + "<end>";
            }else{
                status = "<start>" + TRANSFER_UNSUCCESSFUL + "<end>";
            }
            try {
                outputStream.write(status.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void feedback(boolean isSuccessful) {//子线程完成文件传输后回调该方法
            System.out.println("feedback:"+isSuccessful);
            feedback1(isSuccessful);
        }
    }
}
