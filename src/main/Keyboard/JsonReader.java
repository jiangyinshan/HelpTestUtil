import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.util.Log;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonReader {
    final static String jsonPath = "/Users/xm20190901/Downloads/MprphSearch/1.json";

    /**
     * 传入json文件，解析成json字符串
     **/
    public String getJson(String path) {
        String jsonStr = "";
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            return null;
        }
    }

    public static String creatFile(String path, String fileName) {
        try {
            File file = new File(path + "/" + fileName);
            FileOutputStream out = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        String json = jsonReader.getJson(jsonPath);
        String fieldName = "gifLocalCDNUrl";
        String regex = "(?<=(\"" + fieldName + "\":\")).*?(?=(\"))";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        ArrayList<String> urlList = new ArrayList<String>();
        while (matcher.find()) {
            String ret = matcher.group();
            urlList.add(ret);
        }
        creatFile("/Users/xm20190901/Downloads/MprphSearch", "test.txt");
        Iterator iterator = urlList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
