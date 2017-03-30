package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Test03 {
    static String fileName = "C:\\Users\\Administrator\\Desktop\\";
    static String command = "push:C:\\Users\\Administrator\\Desktop\\02\\020202.txt";
//    static String command = "move:C:\\Users\\Administrator\\Desktop\\02\\新建文本文档.txt,C:\\Users\\Administrator\\Desktop\\02\\02\\新建文本文档.txt";
//    static String command = "mkDir:C:\\Users\\Administrator\\Desktop\\04";
//    static String commandString = "move:c:\\010101.txt,c:\\abc\\010101.txt";
//    static String commandString = "push:c:\\010101.txt";

    public static void main(String[] args) {
        System.out.println(command.indexOf("push:"));
//        System.out.println(command.substring(command.indexOf("push:"+5)).trim());
//        System.out.println(command.substring(command.indexOf("move:") + 5, command.indexOf(',')));
//        System.out.println(command.substring(command.indexOf(",")+1));
//        System.out.println(command.substring(command.indexOf("mkDir:")+6));
//        System.out.println(commandString.substring(commandString.indexOf("move:")+5,commandString.indexOf(',')));
//        System.out.println(commandString.substring(commandString.indexOf(',')+1));
//        System.out.println(commandString.substring(commandString.indexOf(':')));
//        FileUtil.move(fileName + "02\\02\\010101.txt", fileName + "03\\010101.txt");
//        FileUtil.delete(fileName+"02");
//        System.out.print(true&&false);
//        FileUtil.move(fileName + "01", fileName + "02");
//        FileUtil.mkDirectory(fileName + "01");
//        try {
//            FileUtil.push(fileName + "\\01\\020202.txt", new FileInputStream(fileName + "010101.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            FileUtil.pull("F:\\新建文件夹", new FileOutputStream("F:\\新建文件夹01"));
//            FileUtil.pull(fileName + "010101.txt", new FileOutputStream("C:\\Users\\Administrator\\Desktop\\020202.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        for (String name : FileUtil.read("C:\\Users\\Administrator\\Desktop\\010101.txt")) {
//            System.out.println(name);
//        }
    }
}
