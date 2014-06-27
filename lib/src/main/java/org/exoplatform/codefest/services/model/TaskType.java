package org.exoplatform.codefest.services.model;

/**
 * Created by minhdv on 6/26/14.
 */
public class TaskType {

  public final static String TYPE_BUG = "Bug";
  public final static String TYPE_TASK = "Task";
  public final static String TYPE_IMPROVEMENT = "Improvement";
  public final static String TYPE_NEW_FEATURE = "New Feature";
  public final static String TYPE_DOCUMENTATION = "Documentation";

  private String name;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
