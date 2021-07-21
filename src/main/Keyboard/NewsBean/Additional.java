/**
 * Copyright 2021 json.cn
 */
package NewsBean;
public class Additional {

    private String Type;
    private String contentSourceDisplay;
    private String contentSourceLogo;
    private String publishedAt;
    public void setType(String Type) {
        this.Type = Type;
    }
    public String getType() {
        return Type;
    }

    public void setContentSourceDisplay(String contentSourceDisplay) {
        this.contentSourceDisplay = contentSourceDisplay;
    }
    public String getContentSourceDisplay() {
        return contentSourceDisplay;
    }

    public void setContentSourceLogo(String contentSourceLogo) {
        this.contentSourceLogo = contentSourceLogo;
    }
    public String getContentSourceLogo() {
        return contentSourceLogo;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getPublishedAt() {
        return publishedAt;
    }

}