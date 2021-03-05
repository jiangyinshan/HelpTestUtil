import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class MorphTaskResultDownload {

    public static void main(String[] args) throws IOException, CsvException {
        int TestCase = 0;//第一列测试集
        String[] time;
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        String[] parms = csvData.get(TestCase);


        MorphSourceUrlReader morphSourceUrlReader = new MorphSourceUrlReader();
        ArrayList<String> urlList = morphSourceUrlReader.getUrlList();
        ArrayList<String> sortedUrlList = morphSourceUrlReader.getSortedUrlList(urlList);
        final String command_1 = "curl -o /Users/xm20190901/Downloads/AutoDownload/测试照片处理结果/s1/";
        final String command_2 = ".gif ";
        int fileNum = 1;
        String shellCommand = "";
        String url = "";
        /**
         拼接curl命令，存入commandList
         **/
        List<String> commandList = new ArrayList<String>();
        for (int i = 0; i < sortedUrlList.size(); i++) {
            url = sortedUrlList.get(i);
            shellCommand = command_1 + fileNum + command_2 + url;
            if (url != "") {
                fileNum++;
            }
            commandList.add(shellCommand);
        }
        /**
         *exec执行commandList中所有命令
         **/
        for (String s : commandList) {
            try {
                Process p = Runtime.getRuntime().exec(s);
                InputStreamReader ir = new InputStreamReader(p.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);      //创建IO管道，准备输出命令执行后的显示内容
                String line;
                while ((line = input.readLine()) != null) {     //按行打印输出内容
                    System.out.println(line);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}