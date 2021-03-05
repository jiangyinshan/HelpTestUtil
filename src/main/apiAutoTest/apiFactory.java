

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
    private String morphSearchTestUrl = "http://gif-engine-test.kikakeyboard.com/v1/api/morph/kind/emotion/search";
    private String uploadTestUrl = "http://gif-engine-test.kikakeyboard.com/v1/api/morph/uploadImage";
    private String morphSearchUrl = "https://gif-engine.kikakeyboard.com/v1/api/morph/kind/emotion/search";
    private String uploadUrl = "https://gif-engine.kikakeyboard.com/v1/api/morph/uploadImage";

    public static Log log = LogFactory.getLog(API.class.getName());
    public int successTaskCount;
    public boolean isUploadSuccess;
    public boolean isVerifySuccess;
    public boolean isAllTaskDone;
    public boolean isAnyTaskDone;
    public boolean isAllTaskInProcessing;
    public boolean isAllTaskFailed;
    public long upLoadCostTime;
    public long uploadStartTime;
    public long uploadSuccessTime;
    public long anyTaskDoneTime;
    public long allTaskDoneTime;
    public long anyTaskDoneCostTime;
    public long allTaskDoneCostTime;
    public boolean flag = true;

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

    /**
     * 查询图片制作结果
     *
     * @param parms 字符串数组，存放请求参数。依次为：User-Agent，sign
     */
    public String morphSearch(String[] parms) throws IOException, CsvException {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl httpUrl = HttpUrl.parse(morphSearchTestUrl)
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
        JSONArray gifList = (JSONArray) dataObject.get("gifList");
        String messageCode = dataObject.get("messageCode").toString();
        if (response.isSuccessful()) {
            log.info("morphSearch接口请求成功\n");
            /**
             *根据响应中messageCode决定是否保存部分任务处理成功的时间和所有任务全部处理完成的时间
             * **/
            switch (messageCode) {
                case "0":
                    /**
                     * 只有所有26个任务全部处理成功才记录时间
                     * **/
                    if (gifList.size() == 26) {
                        log.info("所有任务全部处理完成且成功，成功任务数量为:" + gifList.size());
                        allTaskDoneTime = System.currentTimeMillis();
                        allTaskDoneCostTime = allTaskDoneTime - uploadSuccessTime;
                        log.info("所有任务处理完成耗时：" + allTaskDoneCostTime);
                    } else {
                        log.info("所有任务全部处理完成，部分任务失败，成功任务数量为" + gifList.size());
                    }
                    successTaskCount = gifList.size();
                    isAllTaskDone = true;
                    isAllTaskFailed = false;
                    isAnyTaskDone = true;
                    isAllTaskInProcessing = false;
                    break;
                case "4":
                    if (isAnyTaskDone() != true) {
                        log.info("部分任务处理成功");
                        if (flag = true) {
                            anyTaskDoneTime = System.currentTimeMillis();
                            anyTaskDoneCostTime = anyTaskDoneTime - uploadSuccessTime;
                            flag = false;
                        }
                        log.info("部分任务处理完成耗时：" + anyTaskDoneCostTime);
                    }
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
     * 上传图片，开始制作
     *
     * @param parms 字符串数组，存放请求参数。依次为：User-Agent，sign，和图片路径path
     */
    public String uploadImage(String[] parms) throws Exception {
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = HttpUrl.parse(uploadTestUrl);
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

    public void tearDown() {
        isUploadSuccess = false;
        isVerifySuccess = false;
        isAllTaskDone = false;
        isAnyTaskDone = false;
        isAllTaskInProcessing = false;
        isAllTaskFailed = false;
        upLoadCostTime = 0;
        uploadStartTime = 0;
        uploadSuccessTime = 0;
        anyTaskDoneTime = 0;
        allTaskDoneTime = 0;
        anyTaskDoneCostTime = 0;
        allTaskDoneCostTime = 0;
        successTaskCount = 0;
        flag = true;
    }
/*    public static void main(String args[]) throws IOException, CsvException {
        apiFactory factory = new apiFactory();
        factory.getRequest();
    }*/
}

