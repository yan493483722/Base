package com.yan.network.download;

import java.util.List;

/**
 * Created by YanZi on 2017/8/21.
 * describe：
 * modify:
 * modify date:
 */
public class DownloadEntity {
    //资源id 同时下载多个资源时设置
    private int sourceId;
    // 资源下载地址
    private String sourceUrl;
    //多线程下载时候结束的长度
    private long endLength;
    //多线程下载时候开始的长度
    private long startLength;
    //已经下载的长度
    private long downloadLength;
    //资源总长度
    private long totalLength;
    //文件名称
    private String fileName;
    //多文件下载时，文件的list
    private List<DownloadEntity> downloadEntityList;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public long getEndLength() {
        return endLength;
    }

    public void setEndLength(long endLength) {
        this.endLength = endLength;
    }

    public long getStartLength() {
        return startLength;
    }

    public void setStartLength(long startLength) {
        this.startLength = startLength;
    }

    public long getDownloadLength() {
        return downloadLength;
    }

    public void setDownloadLength(long downloadLength) {
        this.downloadLength = downloadLength;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<DownloadEntity> getDownloadEntityList() {
        return downloadEntityList;
    }

    public void setDownloadEntityList(List<DownloadEntity> downloadEntityList) {
        this.downloadEntityList = downloadEntityList;
    }

    @Override
    public String toString() {
        return "DownLoadEntity{" +
                "sourceId=" + sourceId +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", endLength=" + endLength +
                ", startLength=" + startLength +
                ", downloadLength=" + downloadLength +
                ", totalLength=" + totalLength +
                ", fileName='" + fileName + '\'' +
                ", downLoadEntityList=" + downloadEntityList +
                '}';
    }
}
