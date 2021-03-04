import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public class UploadCase {

    public UploadCase() throws IOException, CsvException {
    }

    public static void main(String args[]) throws Exception {
        List<String[]> csvData = new csvAction().getCSVDataList();
        UploadCase uploadCase = new UploadCase();
        apiFactory factory = new apiFactory();
        String uploadImage = factory.uploadImage(csvData.get(0));
        String morphSearch = factory.morphSearch(csvData.get(0));




    }
}
