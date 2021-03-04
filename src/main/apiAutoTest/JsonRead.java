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

public class JsonRead {

    /**
     * 传入json文件，解析成json字符串
     **/
    public String getJson() throws IOException {
        API api = new API();
        String jsonStr = api.getRequest();
        return jsonStr;
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
        JsonRead jsonRead = new JsonRead();
        String json = "";
        try {
            json = jsonRead.getJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fieldName = "gifLocalCDNUrl";
        String regex = "(?<=(\"" + fieldName + "\":\")).*?(?=(\"))";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        ArrayList<String> urlList = new ArrayList<String>();
        while (matcher.find()) {
            String ret = matcher.group();
            urlList.add(ret);
        }
        Iterator iterator = urlList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
