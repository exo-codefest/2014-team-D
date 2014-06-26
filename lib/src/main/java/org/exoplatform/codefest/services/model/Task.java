package org.exoplatform.codefest.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class Task {

  private String name;
  private String summary;
  private String priority;
  private String status;
  private String type;
  private String labels;
  private Date dueDate;
  private Date startDate;
  private Date endDate;
  private String estimateTime;
  private String remainingTime;
  private String loggedTime;
  private List<Attachment> attachments = new ArrayList<Attachment>();
  private Attachment attachment;
  private String assignee;
  private TaskACL taskRoles;

  public Task() {
  }

  public String getName() {
       return name;
  }

  public void setName(String name) {
          this.name = name;
  }
  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  public String getLabels() {
    return labels;
  }

  public void setLabels(String labels) {
    this.labels = labels;
  }
  public String getEstimateTime() {
    return estimateTime;
  }

  public void setEstimateTime(String estimateTime) {
    this.estimateTime = estimateTime;
  }
  public String getRemainingTime() {
    return remainingTime;
  }

  public void setRemainingTime(String remainingTime) {
    this.remainingTime = remainingTime;
  }
  public String getLoggedTime() {
    return loggedTime;
  }

  public void setLoggedTime(String loggedTime) {
    this.loggedTime = loggedTime;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public List<Attachment> getAttachments() {
    return attachments;
  }

  public void addAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public TaskACL getTaskRoles() {
    return taskRoles;
  }

  public void setTaskRoles(TaskACL taskRoles) {
    this.taskRoles = taskRoles;
  }
}
