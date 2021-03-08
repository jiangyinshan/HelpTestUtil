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
    public String getJson(String[] parms) throws IOException, CsvException {
        String[] time;
        apiFactory api = new apiFactory();
        String jsonStr = api.morphSearch(parms);
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
        String[] parms = csvData.get(0);

        MorphSeachResultJsonReader morphSeachResultJsonReader = new MorphSeachResultJsonReader();
        String json = "";
        try {
            json = morphSeachResultJsonReader.getJson(parms);
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
