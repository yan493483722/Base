package com.yan.basedemo.manager;

/**
 * Created by YanZi on 2018/12/26.
 * describe：
 * modify:
 * modify date:
 */
public class DownloadEntity {

    private String taskName;
    private Long taskTime;

    public DownloadEntity(String taskName, Long taskTime) {
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Long taskTime) {
        this.taskTime = taskTime;
    }


    @Override
    public String toString() {
        return "DownloadEntity{" +
                "taskName='" + taskName + '\'' +
                ", taskTime=" + taskTime +
                '}';
    }
}
