package org.exoplatform.codefest.services.impl;

import org.exoplatform.codefest.services.api.ProjectManager;
import org.exoplatform.codefest.services.api.TaskManager;
import org.exoplatform.codefest.services.model.Project;
import org.exoplatform.codefest.services.model.Task;
import org.exoplatform.codefest.services.model.TaskState;
import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.services.jcr.util.IdGenerator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class TaskManagerImpl implements TaskManager {

  private ProjectManager projectManager;

  public TaskManagerImpl(ProjectManager projectManager) {
    this.projectManager = projectManager;
  }
  @Override
  public void createTask(String projectId, Task task) throws Exception {
    Project project = projectManager.getProjectById(projectId);
    Node projectNode = project.toEntity();
    String taskId = task.getGenerateTaskId();
    Node taskNode = projectNode.addNode(taskId, Task.TASK);
    taskNode.setProperty(Task.TASK_ID, taskId);
    taskNode.setProperty(Task.TASK_SUMMARY, task.getSummary());
    taskNode.setProperty(Task.TASK_DESCRIPTION, task.getDescription());
    taskNode.setProperty(Task.TASK_PRIORITY, task.getPriority());
    taskNode.setProperty(Task.TASK_STATUS, TaskState.STATE_OPEN);
    taskNode.setProperty(Task.TASK_REPORTER, task.getReporter());
    taskNode.setProperty(Task.TASK_ASSIGNEE, task.getAssignee());
    taskNode.setProperty(Task.TASK_LABELS, task.getLabels());
    taskNode.setProperty(Task.TASK_ESTIMATE_TIME, task.getEstimateTime());
    taskNode.setProperty(Task.TASK_DUE_DATE, task.getDueDate());
    taskNode.setProperty(Task.TASK_TYPE, task.getType());
    taskNode.setProperty(Task.TASK_PROJECT_ID, projectId);
    projectNode.save();
  }

  @Override
  public void createSubTask(Task task, String parentTaskId) {
    //TODO
  }

  @Override
  public void updateTask(Task task) throws Exception {
    Node taskNode = task.toEntity();
    taskNode.setProperty(Task.TASK_SUMMARY, task.getSummary());
    taskNode.setProperty(Task.TASK_DESCRIPTION, task.getDescription());
    taskNode.setProperty(Task.TASK_PRIORITY, task.getPriority());
    taskNode.setProperty(Task.TASK_STATUS, task.getStatus());
    taskNode.setProperty(Task.TASK_REPORTER, task.getReporter());
    taskNode.setProperty(Task.TASK_ASSIGNEE, task.getAssignee());
    taskNode.setProperty(Task.TASK_LABELS, task.getLabels());
    taskNode.setProperty(Task.TASK_ESTIMATE_TIME, task.getEstimateTime());
    taskNode.setProperty(Task.TASK_DUE_DATE, task.getDueDate());
    taskNode.setProperty(Task.TASK_TYPE, task.getType());
    taskNode.save();
  }

  @Override
  public void removeTask(String taskId) throws Exception {
    Task task = new Task(taskId);
    task.remove();
  }

  @Override
  public void assignTask(String taskId, String userId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    taskNode.setProperty(Task.TASK_ASSIGNEE, userId);
    taskNode.save();
  }

  @Override
  public List<Task> getUserTasks(String userId) throws Exception {
    List<Task> tasks = new ArrayList<Task>();
    String statement = "SELECT * FROM exo:task WHERE exo:assignee = '"+userId+"";
    QueryResult result = CoreUtils.buildQuery(statement);
    NodeIterator nodeIter = result.getNodes();
    while(nodeIter.hasNext()) {
      Node taskNode = nodeIter.nextNode();
      tasks.add(convertToTask(taskNode));
    }
    return tasks;
  }

  @Override
  public List<Task> getAllTaskInProject(String projectId) throws Exception {
    List<Task> tasks = new ArrayList<Task>();
    String statement = "SELECT * FROM exo:task WHERE exo:projectId = '"+projectId+"";
    QueryResult result = CoreUtils.buildQuery(statement);
    NodeIterator nodeIter = result.getNodes();
    while(nodeIter.hasNext()) {
      Node taskNode = nodeIter.nextNode();
      tasks.add(convertToTask(taskNode));
    }
    return tasks;
  }

  @Override
  public void startProgress(String taskId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    String status = taskNode.getProperty(Task.TASK_STATUS).getString();
    if(status.equals(TaskState.STATE_OPEN) || status.equals(TaskState.STATE_REOPENED)) {
      taskNode.setProperty(Task.TASK_STATUS, TaskState.STATE_INPREGRESS);
      taskNode.setProperty(Task.TASK_START_DATE, new GregorianCalendar());
      taskNode.save();
    } else if(status.equals(TaskState.STATE_INPREGRESS)) {
      return;
    }
  }

  @Override
  public void stopProgress(String taskId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    String status = taskNode.getProperty(Task.TASK_STATUS).getString();
    if(!status.equals(TaskState.STATE_INPREGRESS)) return;
    taskNode.setProperty(Task.TASK_STATUS, TaskState.STATE_OPEN);
    taskNode.save();
  }

  @Override
  public void resolveTask(String taskId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    String status = taskNode.getProperty(Task.TASK_STATUS).getString();
    if(status.equals(TaskState.STATE_RESOLVED) || status.equals(TaskState.STATE_CLOSED)) return;
    taskNode.setProperty(Task.TASK_STATUS, TaskState.STATE_RESOLVED);
    taskNode.setProperty(Task.TASK_END_DATE, new GregorianCalendar());
    taskNode.save();
  }

  @Override
  public void closeTask(String taskId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    String status = taskNode.getProperty(Task.TASK_STATUS).getString();
    if(status.equals(TaskState.STATE_CLOSED)) return;
    taskNode.setProperty(Task.TASK_STATUS, TaskState.STATE_CLOSED);
    taskNode.setProperty(Task.TASK_END_DATE, new GregorianCalendar());
    taskNode.save();
  }

  @Override
  public void moveTask(String taskId, String targetProjectId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    taskNode.setProperty(Task.TASK_PROJECT_ID, targetProjectId);
    taskNode.save();
    Project targetProject = projectManager.getProjectById(targetProjectId);
    Node projectNode = targetProject.toEntity();
    Session session = CoreUtils.getSystemSessionProvider().getSession(System.getProperty("gatein.jcr.workspace.default"),
      CoreUtils.getRepository());
    session.move(taskNode.getPath(), projectNode.getPath() + "/" + taskId);
  }

  @Override
  public void cloneTask(String taskId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    String newCloneTaskId = IdGenerator.generate();
    Node projectNode = taskNode.getParent();
    Node cloneNode = projectNode.addNode(newCloneTaskId, Task.TASK);
    cloneNode.setProperty(Task.TASK_SUMMARY, task.getSummary());
    cloneNode.setProperty(Task.TASK_DESCRIPTION, task.getDescription());
    cloneNode.setProperty(Task.TASK_PRIORITY, task.getPriority());
    cloneNode.setProperty(Task.TASK_STATUS, task.getStatus());
    cloneNode.setProperty(Task.TASK_REPORTER, task.getReporter());
    cloneNode.setProperty(Task.TASK_ASSIGNEE, task.getAssignee());
    cloneNode.setProperty(Task.TASK_LABELS, task.getLabels());
    cloneNode.setProperty(Task.TASK_ESTIMATE_TIME, task.getEstimateTime());
    cloneNode.setProperty(Task.TASK_DUE_DATE, task.getDueDate());
    cloneNode.setProperty(Task.TASK_REMAINING_TIME, task.getRemainingTime());
    cloneNode.setProperty(Task.TASK_LOGGED_TIME, task.getLoggedTime());
    cloneNode.setProperty(Task.TASK_TYPE, task.getType());
    cloneNode.save();
  }

  @Override
  public Task getTaskById(String taskId) throws Exception {
    Task task = new Task(taskId);
    Node taskNode = task.toEntity();
    return convertToTask(taskNode);
  }

  public Task convertToTask(Node taskNode) throws Exception {
    Task task = new Task();
    task.setTaskId(taskNode.getProperty(Task.TASK_ID).getString());
    task.setSummary(taskNode.getProperty(Task.TASK_SUMMARY).getString());
    task.setDescription(taskNode.getProperty(Task.TASK_DESCRIPTION).getString());
    task.setReporter(taskNode.getProperty(Task.TASK_REPORTER).getString());
    task.setAssignee(taskNode.getProperty(Task.TASK_ASSIGNEE).getString());
    task.setDueDate(taskNode.getProperty(Task.TASK_DUE_DATE).getDate());
    task.setStartDate(taskNode.getProperty(Task.TASK_START_DATE).getDate());
    task.setEndDate(taskNode.getProperty(Task.TASK_END_DATE).getDate());
    task.setPriority(taskNode.getProperty(Task.TASK_PRIORITY).getString());
    task.setStatus(taskNode.getProperty(Task.TASK_STATUS).getString());
    task.setType(taskNode.getProperty(Task.TASK_TYPE).getString());
    task.setEstimateTime(taskNode.getProperty(Task.TASK_ESTIMATE_TIME).getString());
    task.setRemainingTime(taskNode.getProperty(Task.TASK_REMAINING_TIME).getString());
    task.setLoggedTime(taskNode.getProperty(Task.TASK_LOGGED_TIME).getString());
    return task;
  }

}
