import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
}
