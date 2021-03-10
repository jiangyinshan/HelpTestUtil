import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculateMorphCostTime {

    public CalculateMorphCostTime() throws IOException, CsvException {

    }

    public void calculateTime() throws Exception {
        boolean flag = true;
        int TestCase = 0;//第一列测试集
        String[] time;
        csvAction csvAction = new csvAction();
        List<String[]> csvData = csvAction.getCSVDataList();
        String[] params = csvData.get(TestCase);
        CalculateMorphCostTime calculateMorphCostTime = new CalculateMorphCostTime();
        apiFactory factory = new apiFactory();
        ArrayList<String[]> resultList = new ArrayList<>();
        String[] desc = {"上传耗时", "部分任务处理完成耗时", "所有任务处理完成耗时", "成功处理的任务数量"};
        resultList.add(desc);
        Integer ti = 0;
        for (int i = 0; i < 4; i++) {
            params = csvData.get(TestCase);
            factory.uploadImage(params);
            Thread.sleep(500);
            while (factory.isAllTaskDone() != true) {
                Thread.sleep(500);
                factory.morphSearch(params);
            }
            String[] timeSet = {String.valueOf(factory.upLoadCostTime), String.valueOf(factory.anyTaskDoneCostTime), String.valueOf(factory.allTaskDoneCostTime), String.valueOf(factory.successTaskCount)};
            resultList.add(timeSet);
            factory.tearDown();
            System.out.println(timeSet[2]);
            TestCase++;
        }
        for (int i = 1; i < resultList.size(); i++) {
            ti = ti + Integer.valueOf(resultList.get(i)[2]);
        }
        ti = ti / (resultList.size() - 1);
        String[] timeSet = {"", "", "", ti.toString()};
        resultList.add(timeSet);
        csvAction.writeCSV(resultList);
    }

    public static void main(String args[]) throws Exception {
        CalculateMorphCostTime calculateMorphCostTime = new CalculateMorphCostTime();
        calculateMorphCostTime.calculateTime();
/*        boolean flag = true;
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
        Integer ti = 0;
        for (int i = 0; i < 5; i++) {
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
        for (int i = 1; i < resultList.size(); i++) {
            ti = ti + Integer.valueOf(resultList.get(i)[2]);
        }
        ti = ti / resultList.size() - 1;
        String[] timeSet = {"", "", "", ti.toString()};
        resultList.add(timeSet);
        csvAction.writeCSV(resultList);*/
    }
}
