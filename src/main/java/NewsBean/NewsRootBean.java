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
public class NewsRootBean {

    private List<Ads> ads;
    private String session;
    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }
    public List<Ads> getAds() {
        return ads;
    }

    public void setSession(String session) {
        this.session = session;
    }
    public String getSession() {
        return session;
    }

}