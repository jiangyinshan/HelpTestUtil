/**
 * Copyright 2021 json.cn
 */
package NewsBean;
import java.util.List;

/**
 * Auto-generated: 2021-05-18 17:19:20
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Ads {

    private String type;
    private String link;
    private String title;
    private String description;
    private String source;
    private List<String> images;
    private Additional additional;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    public List<String> getImages() {
        return images;
    }

    public void setAdditional(Additional additional) {
        this.additional = additional;
    }
    public Additional getAdditional() {
        return additional;
    }

}