package org.exoplatform.codefest.services.impl;

import org.exoplatform.codefest.services.api.ProjectManager;
import org.exoplatform.codefest.services.model.Project;

/**
 * Created by minhdv on 6/26/14.
 */
public class ProjectManagerImpl implements ProjectManager {

  public ProjectManagerImpl() throws Exception {

  }
  @Override
  public void addProject(String projectName, String projectDescription, String members)
    throws Exception {
    Project project = new Project(projectName, projectDescription, members);
    project.create();
  }

  @Override
  public void updateProject(String projectName, String projectDescription, String members) throws Exception {
    Project project = new Project(projectName, projectDescription, members);
    project.update();
  }

  @Override
  public void removeProject(String projectName) throws Exception {
    Project project = new Project(projectName);
    project.remove();
  }

}
