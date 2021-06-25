import StickerBean.Resource;
import StickerBean.StickerJsonRoot;
import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.ArrayList;


/**
 * 输出kika 数量和名称
 **/
public class KikaStickerCount {

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
                .addQueryParameter("pageSize", "1000")
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
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(getSticekrBean(stickerSearchApi_release)).execute();
        String json = response.body().string();
        StickerJsonRoot stickerJsonRoot = JSONObject.parseObject(json, StickerJsonRoot.class);
        ArrayList<String> kikaStickerName = new ArrayList(1000);
        System.out.println(stickerJsonRoot.getData().getResource().size());
        int i = 0;
        for (Resource r : stickerJsonRoot.getData().getResource()) {
            if (r.getType().equals("kika")) {
                kikaStickerName.add(r.getName());
            }
        }
        System.out.println(kikaStickerName.size());
        for (String s:kikaStickerName
             ) {
            System.out.println(s);
        }

    }
}
