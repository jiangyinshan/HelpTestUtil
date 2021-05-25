package StickerMaker.NewBean;

/**
 * Copyright 2021 json.cn
 */
import java.util.List;

/**
 * Auto-generated: 2021-05-25 14:6:1
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class ResourceList {

    private String key;
    private String name;
    private String pkgName;
    private String lockType;
    private int priority;
    private String trayImageFile;
    private String tag;
    private List<StickerMakerList> stickerMakerList;
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

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
    public String getPkgName() {
        return pkgName;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }
    public String getLockType() {
        return lockType;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }

    public void setTrayImageFile(String trayImageFile) {
        this.trayImageFile = trayImageFile;
    }
    public String getTrayImageFile() {
        return trayImageFile;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

    public void setStickerMakerList(List<StickerMakerList> stickerMakerList) {
        this.stickerMakerList = stickerMakerList;
    }
    public List<StickerMakerList> getStickerMakerList() {
        return stickerMakerList;
    }

}
