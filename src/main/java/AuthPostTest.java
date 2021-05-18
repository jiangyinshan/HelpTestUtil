import StickerBean.Resource;
import StickerBean.StickerJsonRoot;
import StickerBean.Stickers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.sun.tools.javac.util.Log;
import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输出mojitok 的免费sticker pack所在位置
 **/
public class AuthPostTest {

    public final static String stickerSearchApi_dev = "https://api-dev.kikakeyboard.com/v1/open/stickers/search";
    public final static String stickerSearchApi_release = "https://api.pro.kikakeyboard.com/v1/points/clockIn";
    public final static String PNG = "png";
    public final static String GIF = "gif";
    public final static int pngStickerPack = 0;
    public final static int gifStickerPack = 1;

    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("sign", "671a09ad40b8b5cbf792ff4a0decadc2")
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("localDate","2021-04-21").build();
        RequestBody body=builder.build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", "com.emoji.coolkeyboard/3043 (721e8c6a01954b7ba8732f0f2d70e0d2/4e5ab3a6d2140457e0423a28a094b1fd) Country/US Language/en System/android Version/23 Screen/320")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();
        System.out.println(request);
        return request;
    }


    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(getSticekrBean(stickerSearchApi_release)).execute();
        System.out.println(response.body().string());

    }
}
