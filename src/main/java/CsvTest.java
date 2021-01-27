import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvTest {
    public static void main(String[] args) throws IOException {
        FileWriter fstream = new FileWriter("/Users/xm20190901/Downloads/response.csv",true);
        BufferedWriter out = new BufferedWriter(fstream);
        Date date  = new Date();
        //设置日期格式
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        //获取string类型日期
        String data=dateFormat.format(date);
        out.write(data);
        out.close();
        fstream.close();
    }
}
