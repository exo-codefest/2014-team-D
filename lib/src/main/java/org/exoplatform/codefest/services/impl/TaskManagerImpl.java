package org.exoplatform.codefest.services.impl;

import org.exoplatform.codefest.services.api.TaskManager;
import org.exoplatform.codefest.services.model.Task;

import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class TaskManagerImpl implements TaskManager {

  @Override
  public void createTask(Task task) {

  }

  @Override
  public void updateTask(String taskName) {

  }

  @Override
  public void removeTask(String taskName) {

  }

  @Override
  public void assignTask(String taskName) {

  }

  @Override
  public void reAssignTask(String taskName) {

  }

  @Override
  public List<Task> getUserTasks(String userName) {
    return null;
  }

  @Override
  public List<Task> getAllTaskInProject(String projectName) {
    return null;
  }

  @Override
  public void startProgress(String taskName) {

  }

  @Override
  public void stopProgress(String taskName) {

  }

  @Override
  public void resolveTask(String taskName) {

  }

  @Override
  public void closeTask(String taskName) {

  }

  @Override
  public void moveTask(Task task, String targetProject) {

  }

  @Override
  public void convertTask(Task task) {

  }

  @Override
  public void cloneTask(Task task) {

  }

  @Override
  public Task getTaskByName(String taskName) {
    return null;
  }
}
