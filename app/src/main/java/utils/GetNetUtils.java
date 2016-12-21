package utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asus on 2016/12/6.
 */
public class GetNetUtils  {

    private static String stream;

    public  static  String getNets(String path){
        try {
            URL url=new URL(path);
           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = urlConnection.getInputStream();
                stream = StreamUtils.Stream(inputStream);
            }
            return  stream;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
