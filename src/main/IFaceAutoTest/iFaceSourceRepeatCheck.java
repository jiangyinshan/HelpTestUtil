import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iFaceSourceRepeatCheck {
    private final String TestingEnvironment = "测试环境";
    private final String ReleaseEnvironment = "正式环境";
    final static String jsonPath1 = "/Users/xm20190901/Downloads/iFaceOne.json";
    final static String jsonPath2 = "/Users/xm20190901/Downloads/iFaceTwo.json";
    public static int fileNum = 1;
    public static int resultFileNum = 1;

    /**
     * csvData为用户User-Agent，sign和上传图片路径这三个参数组成的字符串数组的List，csv文件中每一列为一个用户参数集合
     *
     * @param index 作为下标获取csvData中指定位置的字符串数组来控制选择哪个用户。为用户参数在csv文件所在行数-1
     **/
    public String[] getparms(int index) throws IOException, CsvException {
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        String[] params = csvData.get(index);
        return params;
    }

    /**
        检查资源是否重复
     **/
    public void RepeatCheack(String[] params, String environmentType) throws IOException, CsvException {
        MorphSourceUrlReader morphSourceUrlReader = new MorphSourceUrlReader();
        ArrayList<String> urlListOne = getUrlList(jsonPath1);
        ArrayList<String> sourceIdOneList = morphSourceUrlReader.getSourceID(urlListOne);
        ArrayList<String> urlListTwo = getUrlList(jsonPath2);
        ArrayList<String> sourceIdTwoList = morphSourceUrlReader.getSourceID(urlListTwo);
        HashMap map = new HashMap();
        for (String s : sourceIdOneList) {
            if (!map.containsKey(s)) {
                map.put(s, "1");
            } else {
                System.out.println("xxxx:" + s);
            }
        }
        for (String s : sourceIdTwoList) {
            if (!map.containsKey(s)) {
                map.put(s, "1");
            } else {
                System.out.println("xxxx:" + s);
            }
        }

    }

    /**
     * 读取morph查询接口响应结果json文件，获取所有item的gifLocalCDNUrl信息，存入urlList
     **/
    public ArrayList<String> getUrlList(String path) throws IOException, CsvException {
        JsonReader jsonReader = new JsonReader();
        String iFaceOneJson = jsonReader.getJson(path);
        String fieldName = "gifLocalCDNUrl";
        String regex = "(?<=(\"" + fieldName + "\":\")).*?(?=(\"))";
        Matcher matcher = Pattern.compile(regex).matcher(iFaceOneJson);
        ArrayList<String> urlList = new ArrayList<String>();
        while (matcher.find()) {
            String ret = matcher.group();
            System.out.println(ret);
            urlList.add(ret);
        }
        return urlList;
    }

    public static void main(String[] args) throws IOException, CsvException {
        iFaceSourceRepeatCheck iface = new iFaceSourceRepeatCheck();
        String[] param = iface.getparms(0);
        iface.RepeatCheack(param, iface.ReleaseEnvironment);

    }
}