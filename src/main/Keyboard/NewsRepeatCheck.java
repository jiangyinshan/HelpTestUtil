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
    public static final String json2 = "";

    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("lang", "en")
                .addQueryParameter("cc", "kika")
                .addQueryParameter("ts", "900782")
                .addQueryParameter("session", "v2_1860f6176edcdc152fea56eb97a4814d_1458994b-c5fc-4f44-9205-e4cfe2e08043_1621409260_1621409292_CIi3jgYQ0fdRGLOuqO2v5IHOECABKAUw4QE4kaQOQIuwDkj62tgDUKcGWABgAGj_5YXXmrLiuRs")
                .addQueryParameter("cty", "US")
                .addQueryParameter("platform", "android")
                .addQueryParameter("limit", "20")
                .addQueryParameter("offset", "40")
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
        Gson gson = new Gson();
        NewsRootBean newsRootBean = JSONObject.parseObject(json, NewsRootBean.class);
        System.out.println(newsRootBean.getAds().size());
        NewsRootBean newsRootBean2 = JSONObject.parseObject(json, NewsRootBean.class);
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
