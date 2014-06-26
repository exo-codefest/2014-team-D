package org.exoplatform.codefest.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class Project {

  public Project() {

  }

  private String name;
  private String description;
  private List<Task> tasks = new ArrayList<Task>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
