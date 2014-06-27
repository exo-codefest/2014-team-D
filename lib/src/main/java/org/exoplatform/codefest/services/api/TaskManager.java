package org.exoplatform.codefest.services.api;

import org.exoplatform.codefest.services.model.Task;

import java.util.List;

public interface TaskManager {

  public void createTask(String projectId, Task task) throws Exception;

  public void createSubTask(Task task, String parentTaskId);

  public void updateTask(Task task) throws Exception;

  public void removeTask(String taskId) throws Exception;

  public void assignTask(String taskId, String userId) throws Exception;

  public List<Task> getUserTasks(String userId) throws Exception;

  public List<Task> getAllTaskInProject(String projectId) throws Exception;

  public void startProgress(String taskId) throws Exception;

  public void stopProgress(String taskId) throws Exception;

  public void resolveTask(String taskId) throws Exception;

  public void closeTask(String taskId) throws Exception;

  public void moveTask(String taskId, String targetProjectId) throws Exception;

  public void cloneTask(String taskId) throws Exception;

  public Task getTaskById(String taskId) throws Exception;

}
