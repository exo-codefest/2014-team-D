package org.exoplatform.codefest.services.api;

import org.exoplatform.codefest.services.model.Task;

import java.util.List;

public interface TaskManager {

  public void createTask(Task task);

  public void updateTask(String taskName);

  public void removeTask(String taskName);

  public void assignTask(String taskName);

  public void reAssignTask(String taskName);

  public List<Task> getUserTasks(String userName);

  public List<Task> getAllTaskInProject(String projectName);

  public void startProgress(String taskName);

  public void stopProgress(String taskName);

  public void resolveTask(String taskName);

  public void closeTask(String taskName);

  public void moveTask(Task task, String targetProject);

  public void convertTask(Task task);

  public void cloneTask(Task task);

  public Task getTaskByName(String taskName);

}
