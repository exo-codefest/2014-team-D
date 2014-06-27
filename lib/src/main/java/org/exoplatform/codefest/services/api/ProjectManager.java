package org.exoplatform.codefest.services.api;

import org.exoplatform.codefest.services.model.Project;

/**
 * Created by minhdv on 6/26/14.
 */
public interface ProjectManager {

  public void createProject(String name, String description, String members) throws Exception;

  public void updateProject(String name, String description, String members) throws Exception;

  public void removeProject(String name) throws Exception;

  public Project getProjectById(String projectId) throws Exception;

}
