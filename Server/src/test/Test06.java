package test;

import java.io.IOException;
import static java.lang.System.out;
/**
 * Created by qiuguomeng on 2017/3/28.
 */
public class Test06 {
    public static void main(String[] args) {
        try {
            Process chromeProcess = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            long current = System.currentTimeMillis();
            out.print(current);
            while (true) {
                if (System.currentTimeMillis() - current > 3000) {
                    break;
                }
            }
            chromeProcess.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
