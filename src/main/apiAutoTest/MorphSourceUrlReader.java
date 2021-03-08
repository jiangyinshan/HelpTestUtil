import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MorphSourceUrlReader {
    final static String jsonPath = "/Users/xm20190901/Downloads/MprphSearch/1.json";
    final static String tem1 = "eacc12a93b724f23a9f9295edb3d3cf7";
    final static String tem2 = "e20516c9193e4372a3200126e6617daa";
    final static String tem3 = "3c7c5b0e3fbc44689819a5c2f4637f83";
    final static String tem4 = "dbb3a5c5e3e84f14a9185b9c1747e8cb";
    final static String tem5 = "e19036b442b042cb9cc3964ea0fa49d8";
    final static String tem6 = "c8dbf73a1a914b86a138314f3a946cc2";
    final static String tem7 = "267a08b845534c5c99614c7563a7d2bc";
    final static String tem8 = "e7a97cccc87d4b2db5553d594c5a7448";
    final static String tem9 = "c440ee5e527244499a051a3896657aaf";
    final static String tem10 = "ed5c7184b0b346f297d6f3e1d7bf1217";
    final static String tem11 = "ca1bef8ce0a344fd99ed0efb24ec2843";
    final static String tem12 = "c9a2d49539f24ba79c0592d53918e488";
    final static String tem13 = "d348e94eacd6477da4196b6389d4c3fc";
    final static String tem14 = "7a2c41367db145ad97b9cd5499a1eb87";
    final static String tem15 = "ba9024da94a64ca092b42a3b3470dd28";
    final static String tem16 = "13a9aa09d4d94159a7e062911b06176b";
    final static String tem17 = "54bfcc05bbae4894af76c09b1e2e2461";
    final static String tem18 = "a2418815a7ae4fe7aad5500aa6b1d870";
    final static String tem19 = "3466598e1c6a4c1b9be7b6bde2f98e5c";
    final static String tem20 = "77b9b49899c74083b68e7723db636b01";
    final static String tem21 = "995d185e974a41f5a787a1e5a0fe14be";
    final static String tem22 = "ba61cb9556f1455aa96ec176eef6771b";
    final static String tem23 = "3f6e6996e15047369b2d72352c4175c1";
    final static String tem24 = "1eea7725be5744b2a234aa668a0b95f3";
    final static String tem25 = "9249d1c7b5d04c659221ccef124e5da6";
    final static String tem26 = "768b7347d0c94a84bcca6587f32ae160";


    /**
     * 读取morph查询接口响应结果json文件，获取所有item的gifLocalCDNUrl信息，存入urlList
     **/
    public static ArrayList<String> getUrlList(String[] parms) throws IOException, CsvException {
        String jsonStr = new MorphSeachResultJsonReader().getJson(parms);
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
        String testFieldName = "pindasd/";
        String regex = "(?<=(" + testFieldName + ")).*?(?=(/))";
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
        ArrayList<String> sortedUrlList = new ArrayList<String>(40);
        sortedUrlList.addAll(urlList);
        sortedUrlList.add(0, "");
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
                case tem17:
                    sortedUrlList.set(16, urlList.get(i));
                    break;
                case tem18:
                    sortedUrlList.set(17, urlList.get(i));
                    break;
                case tem19:
                    sortedUrlList.set(18, urlList.get(i));
                    break;
                case tem20:
                    sortedUrlList.set(19, urlList.get(i));
                    break;
                case tem21:
                    sortedUrlList.set(20, urlList.get(i));
                    break;
                case tem22:
                    sortedUrlList.set(21, urlList.get(i));
                    break;
                case tem23:
                    sortedUrlList.set(22, urlList.get(i));
                    break;
                case tem24:
                    sortedUrlList.set(23, urlList.get(i));
                    break;
                case tem25:
                    sortedUrlList.set(24, urlList.get(i));
                    break;
                case tem26:
                    sortedUrlList.set(25, urlList.get(i));
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

    public static void main(String[] args) throws IOException, CsvException {
        final String command_1 = "curl -o /Users/xm20190901/Downloads/AutoDownload/近距离人像/";
        final String command_2 = ".gif ";
        int fileNum = 1;
        String shellCommand = "";
        String url = "";
        String command = "";
        MorphSourceUrlReader morphSourceUrlReader = new MorphSourceUrlReader();
        /*        ArrayList<String> urlList = morphSourceUrlReader.getUrlList();*/
        /*        ArrayList<String> sortedList = morphSourceUrlReader.getSortedUrlList(urlList);*/
        ArrayList<String> commandList = new ArrayList<String>();

    }
}
