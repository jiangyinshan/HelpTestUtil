import StickerBean.Resource;
import StickerBean.StickerJsonRoot;
import StickerBean.Stickers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * 对kika sticker pack的webp资源进行check，是否所有kika sticker 资源都有webp资源，且资源路径正确
 **/
public class StickerResourceCheck {
    final static String jsonPath = "/Users/xm20190901/Downloads/stickerResourceCheck.json";
    final static String stickerSearchApi_dev = "http://api-dev.kikakeyboard.com/v1/open/stickers/search";
    final static String stickerSearchApi_release = "https://api.kika.kikakeyboard.com/v1/open/stickers/search";


    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("sign", "c2f6de6e3f0e93334db2aeb44a0098cb")
                .addQueryParameter("pageSize", "1000")
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

    public static void main(String[] args) throws NullPointerException, IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(getSticekrBean(stickerSearchApi_release)).execute();
        String json = response.body().string();
        StickerJsonRoot stickerJsonRoot = JSONObject.parseObject(json, StickerJsonRoot.class);
        List<Resource> resourceList = stickerJsonRoot.getData().getResource();
        HashMap hashMap = new HashMap();
        for (Resource r : resourceList) {
            if (r.getType().equals("mojitok")) {
            } else if (r.getType().equals("kika")) {
                List<Stickers> stickersList = r.getStickers();
                for (Stickers s : stickersList) {
                    String imageUrlPath = s.getImage().getUrl().substring(7, s.getImage().getUrl().length() - 4);//截取png/gif格式资源路径
                    try {
                        String webpUrlPath = s.getIm_webp().getUrl().substring(8, s.getIm_webp().getUrl().length() - 5);//截取webp格式资源路径
                        //当png/gif路径和webp资源路径不一致时，输入该sticker资源的key
                        if (!imageUrlPath.equals(webpUrlPath)) {
                            System.out.println(s.getKey() + ":错误");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(s.getKey());//搜集im_webp字段为null，即无webp资源的kika sticker pack的key
                        hashMap.put(r.getKey(), "1");
                        throw e;
                    } finally {
                        continue;
                    }
                }
            }
        }
        System.out.println(hashMap.keySet());
    }
}
