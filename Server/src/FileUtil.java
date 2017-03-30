import java.io.*;

/**
 * Created by Administrator on 2017/3/17.
 */
public class FileUtil {
    public static long checkFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }

    public static boolean push(String desFile, InputStream inputStream) {
        byte[] b = new byte[2048];
        FileOutputStream fileOutputStream = null;
        int len = 0;
        try {
            fileOutputStream = new FileOutputStream(desFile);
            while (-1 != (len= inputStream.read(b))) {
                fileOutputStream.write(b, 0, len);
            }
            fileOutputStream.close();
            inputStream.close();
            if (len == -1) {
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.print("无法创建文件");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean push(String fileName, InputStream inputStream, long fileLong) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
            int len = 0;
            byte[] bytes = new byte[2048];
            while (-1 != (len = inputStream.read(bytes))) {
                randomAccessFile.write(bytes,0,len);
            }
            randomAccessFile.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean pull(String srcFile, OutputStream outputStream) {
        File file = new File(srcFile);
        if (!file.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] b = new byte[2048];
            int len = 0;
            while (-1 != (len = fileInputStream.read(b))) {
                outputStream.write(b, 0, len);
            }
            outputStream.close();
            fileInputStream.close();
            if (-1 == len) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean pull(String fileName, long fileLong, OutputStream outputStream) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r");
            randomAccessFile.seek(fileLong);
            int len = 0;
            byte[] bytes = new byte[2048];
            while(-1 != (len = randomAccessFile.read(bytes))) {
                outputStream.write(bytes,0,len);
            }
            outputStream.close();
            randomAccessFile.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] read(String readFile) {
        File file = new File(readFile);
        return file.list();
    }

    public static boolean mkDirectory(String f) {
        File file = new File(f);
        if (file.exists()) {
            return true;
        }
        return file.mkdir();
    }

    public static boolean move(String srcFile, String desFile) {//移入文件夹必须存在，否则操作无效
        File file = new File(srcFile);
        if (file.exists()) {
            File des = new File(desFile);
            if (des.exists()) {
                return false;
            }
            return file.renameTo(des);
        }
        return false;
    }
    public static boolean delete(String file) {
        File file1 = new File(file);
        if (file1.exists() && file1.isFile()) {
            return file1.delete();
        } else if (file1.exists() && file1.isDirectory()) {
            return delete(file1);
        }
        return false;
    }

    private static boolean delete(File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                delete(file);
            }else {
                file.delete();
            }
        }
        return directory.delete();
    }
}
