package org.exoplatform.codefest.services;

/**
 * Created by minhdv on 6/26/14.
 */
public class Assignee {

  public Assignee() {

  }

  private String userName;
  private String displayName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
