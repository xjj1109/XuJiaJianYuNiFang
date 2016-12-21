package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asus on 2016/12/6.
 */
public class StreamUtils {
    public  static  String Stream(InputStream inputStream){
        try {
            int len=0;
            byte [] b=new byte[1024];
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            while((len=inputStream.read(b))!=-1){
                out.write(b,0,len);
            }
            return  out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
