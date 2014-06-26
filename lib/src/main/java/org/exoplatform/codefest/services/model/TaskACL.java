package org.exoplatform.codefest.services.model;

/**
 * Created by minhdv on 6/27/14.
 */
public class TaskACL {

  private boolean canEditContent = false;
  private boolean canComment = false;
  private boolean canAttachment = false;
  private boolean canRemoveAttachment = false;
  private boolean canAssign = false;
  private boolean canResolveTask = false;
  private boolean canCloseTask = false;

  public boolean isCanEditContent() {
    return canEditContent;
  }

  public void setCanEditContent(boolean canEditContent) {
    this.canEditContent = canEditContent;
  }

  public boolean isCanAttachment() {
    return canAttachment;
  }

  public void setCanAttachment(boolean canAttachment) {
    this.canAttachment = canAttachment;
  }

  public boolean isCanComment() {
    return canComment;
  }

  public void setCanComment(boolean canComment) {
    this.canComment = canComment;
  }

  public boolean isCanRemoveAttachment() {
    return canRemoveAttachment;
  }

  public void setCanRemoveAttachment(boolean canRemoveAttachment) {
    this.canRemoveAttachment = canRemoveAttachment;
  }

  public boolean isCanAssign() {
    return canAssign;
  }

  public void setCanAssign(boolean canAssign) {
    this.canAssign = canAssign;
  }

  public boolean isCanResolveTask() {
    return canResolveTask;
  }

  public void setCanResolveTask(boolean canResolveTask) {
    this.canResolveTask = canResolveTask;
  }

  public boolean isCanCloseTask() {
    return canCloseTask;
  }

  public void setCanCloseTask(boolean canCloseTask) {
    this.canCloseTask = canCloseTask;
  }
}
