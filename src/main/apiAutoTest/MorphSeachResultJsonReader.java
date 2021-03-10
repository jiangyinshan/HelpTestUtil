import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MorphSeachResultJsonReader {

    /**
     * 传入json文件，解析成json字符串
     **/
    public String getJson(String[] params, String environmentType) throws IOException, CsvException {
        String[] time;
        apiFactory api = new apiFactory(environmentType);
        String jsonStr = api.morphSearch(params);
        return jsonStr;
    }

    public static String creatFile(String path, String fileName) {
        try {
            File file = new File(path + "/" + fileName);
            FileOutputStream out = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, CsvException {
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        String[] params = csvData.get(0);

        MorphSeachResultJsonReader morphSeachResultJsonReader = new MorphSeachResultJsonReader();
        String json = "";
        try {
            json = morphSeachResultJsonReader.getJson(params, "测试环境");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        String fieldName = "gifLocalCDNUrl";
        String regex = "(?<=(\"" + fieldName + "\":\")).*?(?=(\"))";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        ArrayList<String> urlList = new ArrayList<String>();
        while (matcher.find()) {
            String ret = matcher.group();
            urlList.add(ret);
        }
        Iterator iterator = urlList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
