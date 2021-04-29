/**
 * Copyright 2021 json.cn
 */
package Bean;
import java.util.List;

/**
 * Auto-generated: 2021-04-26 17:33:27
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Resource {

    private String type;
    private String language;
    private String priceTag;
    private String key;
    private String name;
    private String icon;
    private String description;
    private List<Stickers> stickers;
    private int stickerType;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public String getLanguage() {
        return language;
    }

    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }
    public String getPriceTag() {
        return priceTag;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setStickers(List<Stickers> stickers) {
        this.stickers = stickers;
    }
    public List<Stickers> getStickers() {
        return stickers;
    }

    public void setStickerType(int stickerType) {
        this.stickerType = stickerType;
    }
    public int getStickerType() {
        return stickerType;
    }

}