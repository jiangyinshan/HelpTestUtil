/**
 * Copyright 2021 json.cn
 */
package StickerBean;
import java.util.List;

/**
 * Auto-generated: 2021-04-26 18:55:43
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Data {

    private List<Resource> resource;
    private int pageSize;
    private int pageNum;
    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }
    public List<Resource> getResource() {
        return resource;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageNum() {
        return pageNum;
    }

}