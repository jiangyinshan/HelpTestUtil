import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class csvAction {
    /**
     * 读取csv文件，每一列数据为字符串数组String[]，返回数组列表List<String[]>
     **/
    public List<String[]> getCSVDataList() throws IOException, CsvException {
        InputStreamReader in = new InputStreamReader(new FileInputStream("/Users/xm20190901/Downloads/表情编辑图片上传接口参数文档.csv"), Charset.forName("UTF-8"));
        CSVReader reader = new CSVReader(in);
        List<String[]> allRecords = reader.readAll();
        reader.close();
        return allRecords;
    }

    public void writeCSV(List<String[]> csvData) {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(
                    new OutputStreamWriter(new FileOutputStream("/Users/xm20190901/Downloads/iFace处理时间.csv"), Charset.forName("utf-8")));
            writer.writeAll(csvData);
            writer.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
