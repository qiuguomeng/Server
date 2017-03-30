import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public class MyUtil {
    public static String commandProcess(InputStream inputStream, String charsetName) throws Exception {
        List<Byte> byteList = new ArrayList<Byte>();
        out:
        while (true) {
            byte[] b0 = new byte[1];
            if (-1 == inputStream.read(b0)) {
                break;
            } else if ('<' == b0[0]) {
                byte[] b00 = new byte[6];
                inputStream.read(b00);
                if (new String(b00/*, charsetName*/).contains("start>")) {
//                    for (byte b000 : b00) {
//                        byteList.add(b000);
//                    }
                    while (true) {
                        inputStream.read(b0);
                        if (b0[0] == '<') {
                            inputStream.read(b00,0,4);
                            if (new String(b00, 0, 4/*, charsetName*/).contains("end>")) {
                                break out;
                            } else {
                                for (int i = 0;i < 4;i++) {
                                    byteList.add(b00[i]);
                                }
                            }
                        }
                        byteList.add(b0[0]);
                    }
                }
            }

        }
//        String charsetName = codeString(inputStream);
//        String charsetName = "UTF-8";
//        byte[] b1 = new byte[1];
//        char c = 'a';
//        StringBuilder result = new StringBuilder();
//        while (true) {
//            try {
//                if (-1 == inputStream.read(b1)) {
//                    System.out.println("break");
//                    break;
//                }
//                if ('<' == new String(b1, charsetName).toCharArray()[0]) {
//                    byte[] b2 = new byte[6];
//                    if (-1 == inputStream.read(b2)) {
//                        break;
//                    }
//                    System.out.println(new String(b2,charsetName));
//                    if (new String(b2, charsetName).contains("start>")) {
//                        while (true) {
//                            inputStream.read(b1);
//                            c = new String(b1,charsetName).toCharArray()[0];
//                            if (c != '<'){
//                                result.append(c);
//                            } else {
//                                byte[] b3 = new byte[4];
//                                if (new String(b3,charsetName).contains("end>")){
//                                    return result.toString();
//                                }else {
//                                    result.append(new String(b3,charsetName));
//                                }
//                            }
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if (byteList.size() <= 0) {
            return null;
        }
        byte[] b = new byte[byteList.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = byteList.get(i);
        }
        return new String(b,charsetName);
    }

    public static String codeString(String fileName) throws Exception {
        return codeString(new FileInputStream(fileName));
    }

    public static String codeString(InputStream inputStream) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(inputStream);
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
//        bin.close();
        return code;
    }
}
