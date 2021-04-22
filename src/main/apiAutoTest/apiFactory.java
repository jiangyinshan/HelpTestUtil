

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
    public static Log log = LogFactory.getLog(API.class.getName());
    public String uploadImageUrl;
    public String morphSearchUrl;
    public int successTaskCount;//处理成功的任务数量
    public boolean isUploadSuccess;//图片上传是否成功
    public boolean isVerifySuccess;//图片认证是否成功，由于以去除认证步骤，所以暂时无用
    public boolean isAllTaskDone;//是否所有任务处理完成,messageCode=0
    public boolean isAnyTaskDone;//是否有任一任务处理完成,messageCode=4
    public boolean isAllTaskInProcessing;//是否所有任务仍在处理过程中,messageCode=3
    public boolean isAllTaskFailed;//是否所有任务处理失败,messageCode=2
    public long upLoadCostTime;//上传图片耗时
    public long uploadStartTime;//上传图片开始时间
    public long uploadSuccessTime;//上传图片成功时间
    public long anyTaskDoneTime;//任一任务处理完成时间
    public long allTaskDoneTime;//所有任务处理完成时间
    public long anyTaskDoneCostTime;//任一任务处理完成耗时
    public long allTaskDoneCostTime;//所有任务处理完成耗时
    public boolean flag = true;
    public List<String[]> paramsList;
    final public int ExecptResultSize = 64;

    public apiFactory(String environmentType) throws IOException, CsvException {
        if (environmentType.equals("测试环境")) {
            uploadImageUrl = "http://gif-engine-test.kikakeyboard.com/v1/api/morph/uploadImage";
            morphSearchUrl = "http://gif-engine-test.kikakeyboard.com/v1/api/morph/kind/emotion/search";
        } else if (environmentType.equals("正式环境")) {
            uploadImageUrl = "https://gif-engine.kikakeyboard.com/v1/api/morph/uploadImage";
            morphSearchUrl = "https://gif-engine.kikakeyboard.com/v1/api/morph/kind/emotion/search";
        } else {
            log.fatal("请选择测试环境或正式环境");
        }
        csvAction csvAction = new csvAction();
        paramsList = csvAction.getCSVDataList();
    }

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
     * 根据创建对象时给定的environmentType构造图片上传Request
     **/
    public Request uploadImageRequestCreate(String[] params) {
        HttpUrl httpUrl = HttpUrl.parse(uploadImageUrl);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("sign", params[1])
                .addFormDataPart("file", "测试图片", RequestBody.create(MediaType.parse("multipart/form-data"), new File(params[2])))
                .build();
        Request request = new Request.Builder()
                .header("User-Agent", params[0])
                .url(httpUrl.toString())
                .post(requestBody)
                .build();
        return request;
    }

    /**
     * 根据创建对象时给定的environmentType构造处理结果查询Request
     **/
    public Request morphSearchRequestCreate(String[] params) {
        HttpUrl httpUrl = HttpUrl.parse(morphSearchUrl)
                .newBuilder()
                .addQueryParameter("sign", params[1])
                .addQueryParameter("pageSize", "80")
                .addQueryParameter("pageNum", "1")
                .addQueryParameter("text", "all")
                .addQueryParameter("gifText", "2")
                .addQueryParameter("version", "1")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", params[0])
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .build();
        return request;
    }

    /**
     * 查询图片制作结果
     *
     * @param params 字符串数组，存放请求参数。依次为：User-Agent，sign
     */
    public String morphSearch(String[] params) throws IOException, CsvException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(morphSearchRequestCreate(params)).execute();
        ResponseBody body = response.body();
        String bodyData = body.string();
        JSONObject jsonObject = JSON.parseObject(bodyData);
        JSONObject dataObject = (JSONObject) jsonObject.get("data");
        JSONArray gifList = (JSONArray) dataObject.get("gifList");
        String messageCode = dataObject.get("messageCode").toString();
        if (response.isSuccessful()) {
            log.info("morphSearch接口请求成功\n");
            /**
             * 当morphSearch接口请求成功时根据响应中messageCode决定是否保存部分任务处理成功的时间和所有任务全部处理完成的时间
             * 当messageCode为0，代表所有任务处理完成，并且有成功的任务。代表所有任务处理完成（isAllTaskDone=true），所有任务处理失败（isAllTaskFailed=false），任一任务处理成功（isAnyTaskDone=true），所有任务仍在处理中（isAllTaskInProcessing=false）
             * 并且当返回的gifList.size等于任务数量的时候，即所有任务处理成功时，记录所有任务处理完成耗时（allTaskDoneCostTime），否则不记录时间
             * **/
            switch (messageCode) {
                case "0":
                    /**
                     * 只有所有26个任务全部处理成功才记录时间
                     * **/
                    if (gifList.size() == ExecptResultSize) {
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
     * @param params 字符串数组，存放请求参数。依次为：User-Agent，sign，和图片路径path
     */
    public String uploadImage(String[] params) throws Exception {
        OkHttpClient client = new OkHttpClient();
        uploadStartTime = System.currentTimeMillis();
        Response response = client.newCall(uploadImageRequestCreate(params)).execute();
        String bodyData = response.body().string();
        JSONObject jsonObject = JSON.parseObject(bodyData);
        if (response.isSuccessful()) {
            /**
             * 当图片上传成功时，记录上传图片耗时
             * 当图片上传失败时，记录响应码
             * **/
            log.info("图片上传请求成功\n");
            uploadSuccessTime = System.currentTimeMillis();
            upLoadCostTime = uploadSuccessTime - uploadStartTime;
            log.info("图片上传耗时:" + upLoadCostTime);
            isUploadSuccess = true;
            /**
             * 当errorCode为0时，即图片验证通过（isVerifySuccess=true），否则图片验证未通过（isVerifySuccess=false）
             *
             * **/
            if (jsonObject.get("errorCode").equals(0)) {
                log.info("图片验证通过\n");
                isVerifySuccess = true;
            } else {
                isVerifySuccess = false;
            }
        } else {
            isUploadSuccess = false;
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

