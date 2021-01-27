import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EnumTest {
    final static String jsonPath = "/Users/xm20190901/Downloads/MprphSearch/1.json";
    final static String tem1 = "521b4dfcdadb456497b8a43929aa8744";
    final static String tem2 = "a63cf777694649678f1699bfa7be7f8d";
    final static String tem3 = "3a92a4997c7d4531b0a089e6d0aed778";
    final static String tem4 = "755eccd883ce4c8ba40626074af8bff6";
    final static String tem5 = "678acf4254d0469c8fc418fb113719a8";
    final static String tem6 = "89286361070d48279e41a01ef53fba7d";
    final static String testtem1 = "bfaec";
    final static String testtem2 = "f21d0";
    final static String testtem3 = "3c47d";
    final static String testtem4 = "78110";
    final static String testtem5 = "36b24";
    final static String testtem6 = "aa9f9";
    final static String testtem7 = "9be03";
    final static String testtem8 = "3f4f4";

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
        String fieldName = "503958d5/";
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
        ArrayList<String> sortedURLList = getSortedUrlList(urlList);


    }
}
