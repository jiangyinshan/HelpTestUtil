package Bean;

/**
 * Copyright 2021 json.cn
 */

import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2021-07-19 16:34:59
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
    private boolean animated;
    private String editor;
    private Date uploadTime;
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

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }
    public boolean getAnimated() {
        return animated;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
    public String getEditor() {
        return editor;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    public Date getUploadTime() {
        return uploadTime;
    }

}
