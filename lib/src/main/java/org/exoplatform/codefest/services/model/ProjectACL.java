package org.exoplatform.codefest.services.model;

/**
 * Created by minhdv on 6/27/14.
 */
public class ProjectACL {

  private boolean canMoveTask = false;
  private boolean canCloneTask = false;
  private boolean canRemoveTask = false;
  private boolean canEditTask = false;

  public boolean canMoveTask() {
    return canMoveTask;
  }

  public void setCanMoveTask(boolean canMoveTask) {
    this.canMoveTask = canMoveTask;
  }

  public boolean canEditTask() {
    return canEditTask;
  }

  public void setCanEditTask(boolean canEditTask) {
    this.canEditTask = canEditTask;
  }

  public boolean canCloneTask() {
    return canCloneTask;
  }

  public void setCanCloneTask(boolean canCloneTask) {
    this.canCloneTask = canCloneTask;
  }

  public boolean canRemoveTask() {
    return canRemoveTask;
  }

  public void setCanRemoveTask(boolean canRemoveTask) {
    this.canRemoveTask = canRemoveTask;
  }
}
