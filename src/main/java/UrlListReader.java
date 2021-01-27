import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UrlListReader {
    final static String jsonPath = "/Users/xm20190901/Downloads/MprphSearch/1.json";
    final static String tem1 = "08f90ad9db6f4681806ed9d53c8fe351";
    final static String tem2 = "20e65b86f30b47bbabaede2802c9f5bd";
    final static String tem3 = "47b4cdbb0d6e4c239d5cdbbec3941cd5";
    final static String tem4 = "d9da8e8a0df24aaf8f4d2ed02c29b523";
    final static String tem5 = "8251b02bd157474db0f3a76082038bbc";
    final static String tem6 = "6e13a7439b1c4decaa5ab58a9a69ea71";
    final static String tem7 = "6d38d439c38f411c9e11d39d0477a6c0";
    final static String tem8 = "c50203e01aee4df69b42eb6d337de688";
    final static String tem9 = "5aac169199ea408b95e6133550823328";
    final static String tem10 = "1d9be33f5f5e47eab9ed2a562df1b279";
    final static String tem11 = "6e977e0b65544ca5ae9379a9f9618b01";
    final static String tem12 = "4bfb1540e172479190362d5a792111d2";
    final static String tem13 = "f8bf5d09e98c4f5e93a9cf9225d5167d";
    final static String tem14 = "a35ea4a3372e458aa5e9f215cd721146";
    final static String tem15 = "c6467ace63fd4db28f202f900e9f68de";
    final static String tem16 = "7a07ca0123344417b3c3981ea001e7cf";


    /**
     * 读取morph查询接口响应结果json文件，获取所有item的gifLocalCDNUrl信息，存入urlList
     **/
    public static ArrayList<String> getUrlList(String path) {
        String jsonStr = new JsonReader().getJson(path);
        String fieldName = "gifLocalCDNUrl";
        String regex = "(?<=(\"" + fieldName + "\":\")).*?(?=(\"))";
        Matcher matcher = Pattern.compile(regex).matcher(jsonStr);
        ArrayList<String> urlList = new ArrayList<String>();
        while (matcher.find()) {
            String ret = matcher.group();
            urlList.add(ret);
        }
        return urlList;
    }

    /**
     * 正则表达式提取资源链接中素材图片ID
     **/
    public static ArrayList<String> getSourceID(ArrayList<String> urlList) {
        ArrayList<String> SourceIDList = new ArrayList<String>();
        String fieldName = "d5safasg/";
        String regex = "(?<=(" + fieldName + ")).*?(?=(/))";
        for (String s : urlList
        ) {
            Matcher matcher = Pattern.compile(regex).matcher(s);
            while (matcher.find()) {
                String ret = matcher.group();
                SourceIDList.add(ret);
            }
        }
        return SourceIDList;
    }

    /**
     * 将无规律排序的urlList根据素材id转换成按素材id排序的sortedUrlList
     **/
    public static ArrayList<String> getSortedUrlList(ArrayList<String> urlList) {
        ArrayList<String> sourceIDList = getSourceID(urlList);
        ArrayList<String> sortedUrlList = new ArrayList<String>(15);
        sortedUrlList.addAll(urlList);
        sortedUrlList.add(1, "");
        sortedUrlList.add(2, "");
        sortedUrlList.add(3, "");
        for (int i = 0; i < sourceIDList.size(); i++) {
            switch (sourceIDList.get(i)) {
                case tem1:
                    sortedUrlList.set(0, urlList.get(i));
                    break;
                case tem2:
                    sortedUrlList.set(1, urlList.get(i));
                    break;
                case tem3:
                    sortedUrlList.set(2, urlList.get(i));
                    break;
                case tem4:
                    sortedUrlList.set(3, urlList.get(i));
                    break;
                case tem5:
                    sortedUrlList.set(4, urlList.get(i));
                    break;
                case tem6:
                    sortedUrlList.set(5, urlList.get(i));
                    break;
                case tem7:
                    sortedUrlList.set(6, urlList.get(i));
                    break;
                case tem8:
                    sortedUrlList.set(7, urlList.get(i));
                    break;
                case tem9:
                    sortedUrlList.set(8, urlList.get(i));
                    break;
                case tem10:
                    sortedUrlList.set(9, urlList.get(i));
                    break;
                case tem11:
                    sortedUrlList.set(10, urlList.get(i));
                    break;
                case tem12:
                    sortedUrlList.set(11, urlList.get(i));
                    break;
                case tem13:
                    sortedUrlList.set(12, urlList.get(i));
                    break;
                case tem14:
                    sortedUrlList.set(13, urlList.get(i));
                    break;
                case tem15:
                    sortedUrlList.set(14, urlList.get(i));
                    break;
                case tem16:
                    sortedUrlList.set(15, urlList.get(i));
                    break;
            }
        }
        removeDuplicateWithOrder(sortedUrlList);
        for (String s : sortedUrlList
        ) {
            System.out.println(s);
        }
        return sortedUrlList;
    }

    // 删除ArrayList中重复元素，保持顺序
    public static void removeDuplicateWithOrder(ArrayList list) {
        Set set = new HashSet();
        ArrayList newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
    }

    public static void main(String[] args) {
        final String command_1 = "curl -o /Users/xm20190901/Downloads/AutoDownload/近距离人像/";
        final String command_2 = ".gif ";
        int fileNum = 1;
        String shellCommand = "";
        String url = "";
        String command = "";
        UrlListReader urlListReader = new UrlListReader();
        ArrayList<String> urlList = urlListReader.getUrlList(jsonPath);
        ArrayList<String> sortedList = urlListReader.getSortedUrlList(urlList);
        ArrayList<String> commandList = new ArrayList<String>();

    }
}
