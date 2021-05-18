import NewsBean.Ads;
import NewsBean.NewsRootBean;
import StickerBean.Resource;
import StickerBean.StickerJsonRoot;
import StickerBean.Stickers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.google.gson.Gson;
import com.sun.tools.javac.util.Log;
import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查新闻是否有重复的
 **/
public class NewsRepeatCheck {

    public final static String stickerSearchApi_dev = "https://api-dev.kikakeyboard.com/v1/open/stickers/search";
    public final static String stickerSearchApi_release = "https://innews.infohubnews.app/ad/mixnews";
    public final static String PNG = "png";
    public final static String GIF = "gif";
    public final static int pngStickerPack = 0;
    public final static int gifStickerPack = 1;

    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("lang", "en")
                .addQueryParameter("cc", "kika")
                .addQueryParameter("ts", "900739")
                .addQueryParameter("session", "v2_334121c24bb75b7b3258dd50048af74e_a555ef2a-0c94-477f-b9aa-5bd9fc37a72f_1621329123_1621329170_CIi3jgYQ0fdRGKaYyZzp-IaRkwEgASgFMG84lq0LQJu5C0jZ6tYDUML2F1gAYABo_-WF15qy4rkb")
                .addQueryParameter("cty", "US")
                .addQueryParameter("platform", "android")
                .addQueryParameter("limit", "20")
                .addQueryParameter("offset", "80")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("Connection", "keep-alive")
                .build();
        return request;
    }


    public static void main(String[] args) throws IOException {
        ArrayList arrayList = new ArrayList();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(getSticekrBean(stickerSearchApi_release)).execute();
        String json = response.body().string();
        System.out.println(json);
        Gson gson = new Gson();
        NewsRootBean newsRootBean = JSONObject.parseObject(json,NewsRootBean.class);
        HashMap titleMap = new HashMap();
        int i=1;
        for (Ads ads : newsRootBean.getAds()) {
            if (!titleMap.containsKey(ads.getTitle())) {
                titleMap.put(ads.getTitle(), i);
            }
            else {
                System.out.println(ads.getTitle()+"第一次出现位置:"+titleMap.get(ads.getTitle()));
                System.out.println(ads.getTitle()+"其它出现位置"+i);
            }
            i++;
        }

    }
}
