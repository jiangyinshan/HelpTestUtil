

import com.google.gson.Gson;
import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class API {
    public static Log log = LogFactory.getLog(API.class.getName());
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 发送GET请求
     *
     * @return
     */

    public String getRequest() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse("http://gif-engine-test.kikakeyboard.com/v1/api/morph/kind/emotion/search")
                .newBuilder()
                .addQueryParameter("sign", "5e65c59cf0d1fc27d520e8e26d00d10b")
                .addQueryParameter("limitSize", "30")
                .addQueryParameter("text", "all")
                .addQueryParameter("gifText", "2")
                .addQueryParameter("version", "1")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", "com.qisiemoji.inputmethod/6562 (uwagdbasbdqwpindasd/78472ddd7528bcacc15725a16aeec190) Country/US Language/en System/android Version/21 Screen/480")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .build();
        String jsonStr = "";
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody body = response.body();

            if (response.isSuccessful()) {
                jsonStr = body.string();
            } else {
                jsonStr = body.string();
                log.error(response.code());
            }
        }
        return jsonStr;
    }

    /**
     * POST请求
     */
//    @Test
    public void postRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // 提交FormData
        FormBody.Builder form = new FormBody.Builder();
        form.add("name", "Tom");
        form.add("age", "23");

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(form.build())
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }

    /**
     * 提交Json
     */
//    @Test
    public void postJsonRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        Gson gson = new Gson();
        String data = gson.toJson(map);

        RequestBody requestBody = RequestBody.create(data, JSON);

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
}

