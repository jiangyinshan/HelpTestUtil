import StickerBean.Resource;
import StickerBean.StickerJsonRoot;
import StickerBean.Stickers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * 对kika sticker pack的webp资源进行check，是否所有kika sticker 资源都有webp资源，且资源路径正确
 **/
public class StickerResourceCheck {
    final static String jsonPath = "/Users/xm20190901/Downloads/stickerResourceCheck.json";

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


    public static void main(String[] args)throws NullPointerException {
        JsonReader jsonReader = new JsonReader();
        String json = jsonReader.getJson(jsonPath);
        JSONObject response = JSON.parseObject(json);
        StickerJsonRoot stickerJsonRoot = JSONObject.parseObject(json, StickerJsonRoot.class);
        List<Resource> resourceList = stickerJsonRoot.getData().getResource();
        HashMap hashMap=new HashMap();
        for (Resource r : resourceList) {
            if (r.getType().equals("mojitok")) {
            } else if (r.getType().equals("kika")) {
                List<Stickers> stickersList = r.getStickers();
                for (Stickers s : stickersList) {
                    String imageUrlPath = s.getImage().getUrl().substring(7, s.getImage().getUrl().length() - 4);
                    try {
                        System.out.println(s.getIm_webp().getUrl());
                        String webpUrlPath = s.getIm_webp().getUrl().substring(8, s.getIm_webp().getUrl().length() - 5);
                        if (!imageUrlPath.equals(webpUrlPath)) {
                            System.out.println(s.getKey() + ":错误");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(s.getKey());
                        hashMap.put(r.getKey(),"1");
                        throw e;
                    }
                    finally {
                        continue;
                    }
                }
            }
        }
        System.out.println(hashMap.keySet());


    }
}
