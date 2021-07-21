import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * CSV操作(读取和写入)
 */
public class CsvUtil {

    /**
     * 读取
     *
     * @param path     csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public  boolean exportCsv(String path, List<String> dataList) {
        boolean isSucess = false;
        File file = new File(path);
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    /**
     * 写入
     *
     * @param path csv文件(路径+文件)
     * @return
     */
    public  List<String> importCsv(String path) {
        File file = new File(path);
        List<String> dataList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        CsvUtil csvUtil = new CsvUtil();
        List<String> dataList = new ArrayList<String>();
        dataList.add("响应,状态码");
        dataList.add("1,张三");
        dataList.add("2,李四");
        dataList.add("3,小红");
        boolean isSuccess = csvUtil.exportCsv("/Users/xm20190901/Downloads/response.csv", dataList);
        System.out.println(isSuccess);

        List<String> data = csvUtil.importCsv("/Users/xm20190901/Downloads/response.csv");
        for (int i = 1; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }
}