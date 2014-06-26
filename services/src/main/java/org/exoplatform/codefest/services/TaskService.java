package org.exoplatform.codefest.services;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
  /**
   * Called when the activity is first created.
   */
  public void TaskService() throws Exception {

  }

  public void addTask(Task task) {

  }

  public void updateTask(String taskName) {

  }

  public void removeTask(String taskName) {

  }

  public List<Task> getTaskListByAssignee(String userName) {
    return new ArrayList<Task>();
  }

  public List<Task> getTaskListByProject(String projectName) {
    return new ArrayList<Task>();
  }

  public void startProgress(String taskName) {

  }

  public void stopProgress(String taskName) {

  }

  public void resolveTask(String taskName) {

  }

  public Task getTaskByName(String taskName) {
    Task task = new Task();
    return task;
  }

}
