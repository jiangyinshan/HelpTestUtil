import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculateMorphCostTime {
    private final String TestingEnvironment = "测试环境";
    private final String ReleaseEnvironment = "正式环境";

    public CalculateMorphCostTime() throws IOException, CsvException {

    }

    public void calculateTime(String environmentType) throws Exception {
        csvAction csvAction = new csvAction();
        apiFactory factory = new apiFactory(environmentType);
        List<String[]> csvData = factory.paramsList;
        ArrayList<String[]> resultList = new ArrayList<>();
        String[] desc = {"上传耗时", "部分任务处理完成耗时", "所有任务处理完成耗时", "成功处理的任务数量"};
        resultList.add(desc);
        Integer ti = 0;
        /**
         *遍历csvData，每次遍历时获取用户参数数组 params，读取参数并上传图片(uploadImage)，上传之后查询图片处理结果(morphSearch)，当所有任务全部处理完成时结束查询
         * 将上传耗时(uploadCostTIme),有任一结果处理完成耗时(anyTaskCostTime),所有任务处理成功耗时(allTaskDoneCostTime)放入时间记录的数组（timeSet）
         * 再将每次遍历得到的时间记录数组存入resultList，用于最终写入csv文件和计算平均时长
         */
        for (int i = 0; i < csvData.size(); i++) {
            String[] params = csvData.get(i);
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
        }
        /**
         计算平均时长
         **/
        for (int i = 1; i < resultList.size(); i++) {
            ti = ti + Integer.valueOf(resultList.get(i)[2]);
        }
        ti = ti / (resultList.size() - 1);
        String[] timeSet = {"", "", "", ti.toString()};
        resultList.add(timeSet);
        /**
         将结果写入csv文件
         **/
        csvAction.writeCSV(resultList);
    }

    public static void main(String args[]) throws Exception {
        CalculateMorphCostTime calculateMorphCostTime = new CalculateMorphCostTime();
        calculateMorphCostTime.calculateTime(calculateMorphCostTime.ReleaseEnvironment);
    }
}
