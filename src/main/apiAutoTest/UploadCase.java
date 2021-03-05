import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadCase {

    public UploadCase() throws IOException, CsvException {

    }

    public static void main(String args[]) throws Exception {
        boolean flag = true;
        int TestCase = 0;//第一列测试集
        String[] time;
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        String[] parms = csvData.get(TestCase);
        UploadCase uploadCase = new UploadCase();
        apiFactory factory = new apiFactory();
        ArrayList<String[]> resultList = new ArrayList<>();
        String[] desc = {"上传耗时", "部分任务处理完成耗时", "所有任务处理完成耗时"};
        resultList.add(desc);
        for (int i = 0; i < 4; i++) {
            factory.uploadImage(parms);
            Thread.sleep(500);
            while (factory.isAllTaskDone() != true) {
                Thread.sleep(500);
                factory.morphSearch(parms);
            }
            String[] timeSet = {String.valueOf(factory.upLoadCostTime), String.valueOf(factory.anyTaskDoneCostTime), String.valueOf(factory.allTaskDoneCostTime)};
            resultList.add(timeSet);
            factory.tearDown();
        }
//        while (factory.isAllTaskDone() != true) {
//            Thread.sleep(500);
//            factory.morphSearch(parms);
//
//        }

//        String[] timeSet = {String.valueOf(factory.upLoadCostTime), String.valueOf(factory.anyTaskDoneCostTime), String.valueOf(factory.allTaskDoneCostTime)};
/*        ArrayList<String[]> resultList = new ArrayList<>();
        resultList.add(desc);
        resultList.add(timeSet);*/

        csvAction.writeCSV(resultList);

    }
}
