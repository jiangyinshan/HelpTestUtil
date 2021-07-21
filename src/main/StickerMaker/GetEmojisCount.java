import Bean.JsonRootBean;
import Bean.ResourceList;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.Request;
import java.io.*;
import java.util.ArrayList;


/**
 * 输出StickerMaker首页两个tab中所有emoji内容大于3个的StickerPack的名称
 **/
public class GetEmojisCount {

    public static void main(String[] args) throws IOException {
        String NewFilePath = "/Users/xm20190901/Desktop/kikaMapLocal/AllNewSticker.json";
        String HotFilePath = "/Users/xm20190901/Desktop/kikaMapLocal/AllHotSticker.json";
        Gson gson = new Gson();
        JsonRootBean hotRootBean = gson.fromJson(ReaderForGson.CreateReader(HotFilePath), JsonRootBean.class);
        JsonRootBean newRootBean = gson.fromJson(ReaderForGson.CreateReader(NewFilePath), JsonRootBean.class);
        ArrayList<String> resultList = new ArrayList<String>(500);
        for (ResourceList resource : hotRootBean.getData().getResourceList()
        ) {
            for (int i = 0; i < hotRootBean.getData().getResourceList().size(); i++) {
                if (resource.getStickerMakerList().get(0).getEmojis().size() > 3) {
                    resultList.add(resource.getName());
                    break;
                }
            }
        }
        for (ResourceList resource : newRootBean.getData().getResourceList()
        ) {
            for (int i = 0; i < newRootBean.getData().getResourceList().size(); i++) {
                if (resource.getStickerMakerList().get(0).getEmojis().size() > 3) {
                    resultList.add(resource.getName());
                    break;
                }
            }
        }
        for (String s : resultList
        ) {
            System.out.println(s);
        }
    }
}

