import StickerBean.Resource;
import StickerBean.StickerJsonRoot;
import StickerBean.Stickers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.sun.tools.javac.util.Log;
import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输出mojitok 的免费sticker pack所在位置
 **/
public class FreeMojitokStickerCount {

    public final static String stickerSearchApi_dev = "https://api-dev.kikakeyboard.com/v1/open/stickers/search";
    public final static String stickerSearchApi_release = "https://api.kika.kikakeyboard.com/v1/open/stickers/search";
    public final static String PNG = "png";
    public final static String GIF = "gif";
    public final static int pngStickerPack = 0;
    public final static int gifStickerPack = 1;

    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("sign", "be58932191c893cce3488b574f14b5b0")
                .addQueryParameter("pageSize", "20")
                .addQueryParameter("pageNum", "1")
                .addQueryParameter("language", "en")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", "com.emoji.coolkeyboard/3028 (9c29fab748c144d8bbabe530e57a317a/4e5ab3a6d2140457e0423a28a094b1fd) Country/US Language/en System/android Version/23 Screen/320")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .build();
        return request;
    }


    public static void main(String[] args) throws IOException {
        ArrayList arrayList=new ArrayList();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(getSticekrBean(stickerSearchApi_release)).execute();
        String json = response.body().string();
        StickerJsonRoot stickerJsonRoot = JSONObject.parseObject(json, StickerJsonRoot.class);
        int Count = 0;
        System.out.println(stickerJsonRoot.getData().getResource().size());
        for (Resource r : stickerJsonRoot.getData().getResource()) {
            Count++;
            if (r.getType().equals("mojitok") && r.getPriceTag().equals("FREE")) {
                arrayList.add(Count);
            }
        }
        System.out.println(arrayList);

    }
}
