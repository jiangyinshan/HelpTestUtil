import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.sun.tools.javac.util.Log;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据sticker item资源的后缀名为gif或jpg判断sticker pack的type为1或0
 **/
public class StickerTypeCheck {
    final static String jsonPath = "/Users/xm20190901/Desktop/kikaMapLocal/StickerType.json";

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


    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        String json = jsonReader.getJson(jsonPath);
        JSONObject response = JSON.parseObject(json);
        JSONObject data = response.getJSONObject("data");
        JSONArray resourceList = data.getJSONArray("resource");
        ArrayList<String> stickerTypeList = new ArrayList();
        ArrayList<String> trueTypeList = new ArrayList();
        ArrayList<JSONArray> stickersList = new ArrayList();
        for (Object ob : resourceList) {
            JSONObject stickerpack = (JSONObject) ob;
            stickerTypeList.add(stickerpack.get("stickerType").toString());
            JSONArray array = stickerpack.getJSONArray("stickers");
            stickersList.add(array);
        }
        trueTypeList.addAll(stickerTypeList);
/*        for (JSONArray jsonArray : stickersList) {
            for (Object ob : jsonArray) {
                JSONObject jsonObject = (JSONObject) ob;
                JSONObject image = jsonObject.getJSONObject("image");
                String url = image.getString("url");
                String type = url.substring(url.length() - 3);
            }
        }*/
        for (int i = 0; i < stickersList.size(); i++) {
            JSONArray jsonArray = stickersList.get(i);
            for (int j = 0; j < jsonArray.size(); j++) {
                Boolean flag = true;
                Object ob = jsonArray.get(j);
                JSONObject jsonObject = (JSONObject) ob;
                JSONObject image = jsonObject.getJSONObject("image");
                String url = image.getString("url");
                String type = url.substring(url.length() - 3);
                if (flag == true) {
                    if (type.equals("gif")) {
                        trueTypeList.set(i, "1");
                        flag = false;
                    } else {
                        trueTypeList.set(i, "0");
                    }
                }
            }
        }
        for (int i = 0; i < stickerTypeList.size(); i++) {
            System.out.println(trueTypeList.equals(stickerTypeList));
        }
    }
}
