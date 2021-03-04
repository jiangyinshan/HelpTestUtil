

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class apiFactory {
    public static Log log = LogFactory.getLog(API.class.getName());
    private boolean isUploadSuccess;
    private boolean isVerifySuccess;
    private boolean isAllTaskDone;
    private boolean isAnyTaskDone;
    private boolean isAllTaskInProcessing;
    private boolean isAllTaskFailed;
    private long upLoadCostTime;
    private long uploadStartTime;
    private long uploadSuccessTime;
    private long anyTaskDoneTime;
    private long allTaskDoneTime;
    private long anyTaskDoneCostTime;
    private long allTaskDoneCostTime;

    public apiFactory() throws IOException, CsvException {

    }

    /**
     * 发送GET请求
     *
     * @return
     */
//    @Test
    public boolean isUploadSuccess() {
        return this.isUploadSuccess;
    }

    public boolean isAllTaskDone() {
        return this.isAllTaskDone;
    }

    public boolean isAnyTaskDone() {
        return this.isAnyTaskDone;
    }

    public String morphSearch(String[] parms) throws IOException, CsvException {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl httpUrl = HttpUrl.parse("http://gif-engine-test.kikakeyboard.com/v1/api/morph/kind/emotion/search")
                .newBuilder()
                .addQueryParameter("sign", parms[1])
                .addQueryParameter("limitSize", "30")
                .addQueryParameter("text", "all")
                .addQueryParameter("gifText", "2")
                .addQueryParameter("version", "1")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", parms[0])
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        ResponseBody body = response.body();
        String bodyData = body.string();
        JSONObject jsonObject = JSON.parseObject(bodyData);
        JSONObject dataObject = (JSONObject) jsonObject.get("data");
        String messageCode = dataObject.get("messageCode").toString();
        if (response.isSuccessful()) {
            log.info("morphSearch接口请求成功\n");
            switch (messageCode) {
                case "0":
                    log.info("所有任务全部处理完成");
                    allTaskDoneTime = System.currentTimeMillis();
                    allTaskDoneCostTime = anyTaskDoneTime - uploadSuccessTime;
                    log.info("所有任务处理完成耗时：" + allTaskDoneCostTime);
                    isAllTaskDone = true;
                    isAllTaskFailed = false;
                    isAnyTaskDone = true;
                    isAllTaskInProcessing = false;
                    break;
                case "4":
                    log.info("部分任务处理成功");
                    anyTaskDoneTime = System.currentTimeMillis();
                    anyTaskDoneCostTime = anyTaskDoneTime - uploadSuccessTime;
                    log.info("部分任务处理完成耗时：" + anyTaskDoneCostTime);
                    isAnyTaskDone = true;
                    isAllTaskFailed = false;
                    isAllTaskInProcessing = false;
                    break;
                case "3":
                    log.info("所有任务仍在进行中");
                    isAllTaskInProcessing = true;
                    isAllTaskFailed = false;
                    isAnyTaskDone = false;
                    isAllTaskDone = false;
                    break;
                case "2":
                    log.info("所有任务全部处理失败");
                    isAllTaskInProcessing = false;
                    isAllTaskFailed = true;
                    isAnyTaskDone = true;
                    isAllTaskDone = true;
                    break;
            }
        } else {
            log.error("morphSearch接口请求错误，响应码为：" + response.code() + "\n");
        }
        return bodyData;
    }

    /**
     * POST请求
     */
    public String uploadImage(String[] parms) throws Exception {
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = HttpUrl.parse("http://gif-engine-test.kikakeyboard.com/v1/api/morph/uploadImage");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("sign", parms[1])
                .addFormDataPart("file", "/Users/xm20190901/Downloads/表情编辑测试图片集/用户照片-2/s4.jpg", RequestBody.create(MediaType.parse("multipart/form-data"), new File(parms[2])))
                .build();

        Request request = new Request.Builder()
                .header("User-Agent", parms[0])
                .url(httpUrl.toString())
                .post(requestBody)
                .build();
        uploadStartTime = System.currentTimeMillis();
        Response response = client.newCall(request).execute();
        String bodyData = response.body().string();
        JSONObject jsonObject = JSON.parseObject(bodyData);
        if (response.isSuccessful()) {
            log.info("图片上传请求成功\n");
            uploadSuccessTime = System.currentTimeMillis();
            upLoadCostTime = uploadSuccessTime - uploadStartTime;
            log.info("图片上传耗时:" + upLoadCostTime);
            this.isUploadSuccess = true;
            if (jsonObject.get("errorCode").equals(0)) {
                log.info("图片验证通过\n");
                isVerifySuccess = true;
            } else {
                isVerifySuccess = false;
            }
        } else {
            this.isUploadSuccess = false;
            log.error("图片上传接口请求错误，响应码为：" + response.code() + "\n");
        }
        return bodyData;

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

//        RequestBody requestBody = RequestBody.create(data, JSON);

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
//                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }

/*    public static void main(String args[]) throws IOException, CsvException {
        apiFactory factory = new apiFactory();
        factory.getRequest();
    }*/
}

