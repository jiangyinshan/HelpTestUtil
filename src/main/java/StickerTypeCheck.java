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
 * 根据sticker item资源的后缀名为gif或jpg判断sticker pack的type为1或0
 **/
public class StickerTypeCheck {

    public final static String stickerSearchApi_dev = "http://api-dev.kikakeyboard.com/v1/open/stickers/search";
    public final static String stickerSearchApi_release = "https://api.kika.kikakeyboard.com/v1/open/stickers/search";
    public final static String PNG = "png";
    public final static String GIF = "gif";
    public final static int pngStickerPack = 0;
    public final static int gifStickerPack = 1;

    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("sign", "c2f6de6e3f0e93334db2aeb44a0098cb")
                .addQueryParameter("pageSize", "20")
                .addQueryParameter("pageNum", "1")
                .addQueryParameter("language", "en")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", "com.qisiemoji.inputmethod/6201 (86ccda21661746c19b19e8644057dd55/73750b399064a5eb43afc338cd5cad25) Country/US Language/en System/android Version/16 Screen/480")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .build();
        return request;
    }


    public static void main(String[] args) throws IOException {
        ArrayList actualTypeList = new ArrayList(1100);
        ArrayList TypeList = new ArrayList(1100);
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(getSticekrBean(stickerSearchApi_release)).execute();
        String json = response.body().string();
        StickerJsonRoot stickerJsonRoot = JSONObject.parseObject(json, StickerJsonRoot.class);
        for (Resource r : stickerJsonRoot.getData().getResource()) {
            TypeList.add(r.getStickerType());
        }
        actualTypeList.addAll(TypeList);
        int i = 0;
        for (Resource r : stickerJsonRoot.getData().getResource()) {
            for (Stickers s : r.getStickers()) {
                Boolean flag = true;
                String image_url = s.getImage().getUrl();
                String image_type = image_url.substring(image_url.length() - 3);//获取url最后三个字符，为png或gif
                if (flag == true) {
                    if (image_type.equals(GIF)) {
                        actualTypeList.set(i, gifStickerPack);
                        flag = false;
                        break;
                    } else {
                        actualTypeList.set(i, pngStickerPack);
                    }
                }
            }
            i++;
        }
        for (int j = 0; j < TypeList.size(); j++) {
            System.out.println(actualTypeList.get(j).equals(TypeList.get(j)));
        }

    }
}
