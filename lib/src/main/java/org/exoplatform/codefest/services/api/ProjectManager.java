package org.exoplatform.codefest.services.api;

/**
 * Created by minhdv on 6/26/14.
 */
public interface ProjectManager {

  public void addProject(String name, String description, String members) throws Exception;

  public void updateProject(String name, String description, String members) throws Exception;

  public void removeProject(String name) throws Exception;

}
