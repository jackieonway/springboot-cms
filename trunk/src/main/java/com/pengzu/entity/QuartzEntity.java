package com.pengzu.entity;

/**
 * 任务类
 */
public class QuartzEntity {

    private String jobName;//任务名称
    private String jobGroup;//任务分组
    private String description;//任务描述
    private String jobClassName;//执行类
    private String cronExpression;//执行时间
    private String triggerName;//执行时间
    private String triggerState;//任务状态

    private String oldJobName;//任务名称 用于修改
    private String oldJobGroup;//任务分组 用于修改

    public QuartzEntity() {
        super();
    }

    public QuartzEntity(String jobName, String jobGroup, String description, String jobClassName, String cronExpression, String triggerName) {
        super();
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.jobClassName = jobClassName;
        this.cronExpression = cronExpression;
        this.triggerName = triggerName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getOldJobName() {
        return oldJobName;
    }

    public void setOldJobName(String oldJobName) {
        this.oldJobName = oldJobName;
    }

    public String getOldJobGroup() {
        return oldJobGroup;
    }

    public void setOldJobGroup(String oldJobGroup) {
        this.oldJobGroup = oldJobGroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"jobName\":\"")
                .append(jobName).append('\"');
        sb.append(",\"jobGroup\":\"")
                .append(jobGroup).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"jobClassName\":\"")
                .append(jobClassName).append('\"');
        sb.append(",\"cronExpression\":\"")
                .append(cronExpression).append('\"');
        sb.append(",\"triggerName\":\"")
                .append(triggerName).append('\"');
        sb.append(",\"triggerState\":\"")
                .append(triggerState).append('\"');
        sb.append(",\"oldJobName\":\"")
                .append(oldJobName).append('\"');
        sb.append(",\"oldJobGroup\":\"")
                .append(oldJobGroup).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
