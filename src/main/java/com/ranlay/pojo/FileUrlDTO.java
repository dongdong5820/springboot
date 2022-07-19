package com.ranlay.pojo;

import java.io.Serializable;

/**
 * @author: Ranlay.su
 * @date: 2022-07-13 18:10
 * @description: 反馈附件
 * @version: 1.0.0
 */
public class FileUrlDTO implements Serializable {
    private static final long serialVersionUID = 8639070973966423911L;

    /**
     * ocsKey
     */
    private String ocsKey;

    /**
     * 附件地址
     */
    private String url;

    /**
     * 文件名
     */
    private String fileName;

    public String getOcsKey() {
        return ocsKey;
    }

    public void setOcsKey(String ocsKey) {
        this.ocsKey = ocsKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileUrlDTO{" +
                "ocsKey='" + ocsKey + '\'' +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}