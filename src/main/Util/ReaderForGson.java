import java.io.*;

/**
 * 当okhttp响应或字符串过长时，通过Gson().fromJson(Reader json, Class<T> classOfT) 方法从Reader对象构造一个Bean
 **/
public class ReaderForGson {
    public static Reader CreateReader(String path) throws FileNotFoundException {
        InputStream is = new FileInputStream(new File(path));
        Reader reader = new InputStreamReader(is);
        return reader;
    }
}
