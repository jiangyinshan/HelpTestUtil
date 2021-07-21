package Bean;

/**
 * Copyright 2021 json.cn
 */
import java.util.List;

/**
 * Auto-generated: 2021-07-19 16:34:59
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Data {

    private int pageSize;
    private String pageName;
    private int pageNum;
    private List<ResourceList> resourceList;
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    public String getPageName() {
        return pageName;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageNum() {
        return pageNum;
    }

    public void setResourceList(List<ResourceList> resourceList) {
        this.resourceList = resourceList;
    }
    public List<ResourceList> getResourceList() {
        return resourceList;
    }

}
