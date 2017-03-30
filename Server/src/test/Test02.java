package test;

import java.io.FileInputStream;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Test02 {
    public static void main(String[] args) {
        try {
            System.out.print(MyUtil.CommandProcess(new FileInputStream("C:\\Users\\Administrator\\Desktop\\010101.txt"),"GBK"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
