package org.exoplatform.codefest.services.model;

import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.services.jcr.util.IdGenerator;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class Task {

  private String taskId;
  private String taskParentId;
  private String projectId;
  private String summary;
  private String description;
  private String priority;
  private String status;
  private String type;
  private String labels;
  private Calendar dueDate;
  private Calendar startDate;
  private Calendar endDate;
  private String estimateTime;
  private String remainingTime;
  private String loggedTime;
  private List<Attachment> attachments = new ArrayList<Attachment>();
  private Attachment attachment;
  private String assignee;
  private String reporter;
  private TaskACL taskRoles;

  public static final String TASK = "exo:task";
  public static final String TASK_ID = "exo:taskId";
  public static final String TASK_SUMMARY = "exo:taskSummary";
  public static final String TASK_DESCRIPTION = "exo:taskDescription";
  public static final String TASK_REPORTER = "exo:taskReporter";
  public static final String TASK_ASSIGNEE = "exo:taskAssignee";
  public static final String TASK_STATUS = "exo:taskStatus";
  public static final String TASK_TYPE = "exo:taskType";
  public static final String TASK_LABELS = "exo:taskLabels";
  public static final String TASK_PRIORITY = "exo:taskPriority";
  public static final String TASK_DUE_DATE = "exo:taskDueDate";
  public static final String TASK_START_DATE = "exo:taskStartDate";
  public static final String TASK_END_DATE = "exo:taskEndDate";
  public static final String TASK_ESTIMATE_TIME = "exo:taskEstimateTime";
  public static final String TASK_REMAINING_TIME = "exo:taskRemainingTime";
  public static final String TASK_LOGGED_TIME = "exo:taskLoggedTime";
  public static final String TASK_PROJECT_ID = "exo:projectId";


  public Task() {
  }

  public Task(String taskId) {
    this.taskId = taskId;
  }

  public Task(String summary, String description, String reporter) {
    this.summary = summary;
    this.description = description;
    this.reporter = reporter;
  }

  public String getGenerateTaskId() {
    return "task" + IdGenerator.generate();
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() { return description; }

  public void setDescription(String description) {
    this.description = description;
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

  public Calendar getStartDate() {
    return startDate;
  }

  public void setStartDate(Calendar startDate) {
    this.startDate = startDate;
  }

  public Calendar getEndDate() {
    return endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  public Calendar getDueDate() {
    return dueDate;
  }

  public void setDueDate(Calendar dueDate) {
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

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getReporter() {
    return reporter;
  }

  public void setReporter(String reporter) {
    this.reporter = reporter;
  }

  public Node toEntity() throws Exception {
    String queryStatement = "SELECT * FROM exo:task WHERE taskId = '"+taskId+"'";
    Session session = CoreUtils.getSystemSessionProvider().getSession(System.getProperty("gatein.jcr.workspace.default"),
      CoreUtils.getRepository());
    QueryResult result = session.getWorkspace().getQueryManager().createQuery(queryStatement, Query.SQL).execute();
    return result.getNodes().nextNode();
  }

  public void remove() throws Exception {
    Session session = CoreUtils.getSystemSessionProvider().getSession(System.getProperty("gatein.jcr.workspace.default"),
      CoreUtils.getRepository());
    toEntity().remove();
    session.save();
  }

  public String getTaskParentId() {
    return taskParentId;
  }

  public void setTaskParentId(String taskParentId) {
    this.taskParentId = taskParentId;
  }
}
