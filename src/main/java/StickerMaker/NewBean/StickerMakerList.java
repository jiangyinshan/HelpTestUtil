/**
 * Copyright 2021 json.cn
 */
package StickerMaker.NewBean;
import java.util.List;

/**
 * Auto-generated: 2021-05-25 14:6:1
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class StickerMakerList {

    private String imageFile;
    private String packKey;
    private List<String> emojis;
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
    public String getImageFile() {
        return imageFile;
    }

    public void setPackKey(String packKey) {
        this.packKey = packKey;
    }
    public String getPackKey() {
        return packKey;
    }

    public void setEmojis(List<String> emojis) {
        this.emojis = emojis;
    }
    public List<String> getEmojis() {
        return emojis;
    }

}