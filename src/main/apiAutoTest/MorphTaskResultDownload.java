import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class MorphTaskResultDownload {
    public static int fileNum = 1;
    public static int resultFileNum = 1;

    /**
     * csvData为用户User-Agent，sign和上传图片路径这三个参数组成的字符串数组的List，csv文件中每一列为一个用户参数集合
     *
     * @param coulnm 作为下标获取csvData中指定位置的字符串数组来控制选择哪个用户。为用户参数在csv文件所在行数-1
     **/
    public String[] getparms(int coulnm) throws IOException, CsvException {
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        String[] parms = csvData.get(coulnm);
        return parms;
    }

    /**
     * command_1，command_2，command_3和resultFileNum以及FileNum用于控制下载图片名和图片文件夹名
     * 每个用户所有图片下载完成后resultFileNum+1，为下一个用户下载图片的文件夹名
     * 每张图片下载完成后FileNum+1，为下一张图片的文件名
     **/
    public void download(String[] parms) throws IOException, CsvException {
        MorphSourceUrlReader morphSourceUrlReader = new MorphSourceUrlReader();
        ArrayList<String> urlList = morphSourceUrlReader.getUrlList(parms);
        ArrayList<String> sortedUrlList = morphSourceUrlReader.getSortedUrlList(urlList);
        final String command_1 = "curl -o /Users/xm20190901/Downloads/AutoDownload/全自动下载/s";
        String command_2 = command_1 + resultFileNum + "/";
        final String command_3 = ".gif ";
        String shellCommand = "";
        String url = "";
        /**
         拼接curl命令，存入commandList
         **/
        List<String> commandList = new ArrayList<String>();
        for (int i = 0; i < sortedUrlList.size(); i++) {
            url = sortedUrlList.get(i);
            shellCommand = command_2 + fileNum + command_3 + url;
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

    /**
     * 下载单个用户的图片处理结果至s1文件夹
     *
     * @param UserParmsLocationInCsvFile 为用户参数在csv文件所处行数减1
     **/
    public void singleUserResultDownload(int UserParmsLocationInCsvFile) throws IOException, CsvException {
        String[] parms = getparms(UserParmsLocationInCsvFile);
        download(parms);
    }

    /**
     * 自动根据csv文件获取用户参数，查询morphSearch接口，下载图片
     **/
    public void multiUserResultDownload() throws IOException, CsvException {
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        for (int i = 0; i < csvData.size(); i++) {
            download(csvData.get(i));
            reCount();
            resultFileNum++;
        }


    }

    /**
     * 每个用户所有图片下载完成后调用此方法将fileNum重置为1
     **/
    public void reCount() {
        fileNum = 1;
    }


    public static void main(String[] args) throws IOException, CsvException {
        MorphTaskResultDownload morphTaskResultDownload = new MorphTaskResultDownload();
        morphTaskResultDownload.multiUserResultDownload();

    }
}