package org.exoplatform.codefest.services.impl;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.codefest.services.api.ProjectManager;
import org.exoplatform.codefest.services.model.Project;
import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class ProjectManagerImpl implements ProjectManager {

  private static final Log LOG  = ExoLogger.getLogger(ProjectManager.class.getName());

  public ProjectManagerImpl() throws Exception {

  }
  @Override
  public void createProject(String projectName, String projectDescription, String members)
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

  @Override
  public Project getProjectById(String projectId) throws Exception{
    Node rootProjectNode = CoreUtils.getProjectRootNode();
    Node projectNode = rootProjectNode.getNode(projectId);
    Project project = new Project();
    project.setName(getPropertyValue(projectNode, Project.EXO_PROJECT_NAME));
    project.setDescription(getPropertyValue(projectNode, Project.EXO_PROJECT_DESC));
    project.setDefautlAssignee(getPropertyValue(projectNode, Project.EXO_PROJECT_DEFAULT_ASSIGNEE));
    return project;
  }

  @Override
  public List<Project> getProjects() throws Exception {
    List<Project> projects = new ArrayList<Project>();
    Node rootProjectNode = CoreUtils.getProjectRootNode();
    NodeIterator nodeIter = rootProjectNode.getNodes();
    while(nodeIter.hasNext()) {
      Node projectNode = nodeIter.nextNode();
      projects.add(getProjectFromNode(projectNode));
    }
    return projects;
  }

  private String getPropertyValue(Node node, String propertyName) {
    try {
      return node.getProperty(propertyName).getString();
    } catch(PathNotFoundException pne) {
      return StringUtils.EMPTY;
    } catch(RepositoryException e) {
      return StringUtils.EMPTY;
    }
  }

  private Project getProjectFromNode(Node node) throws Exception {
    Project project = new Project();
    project.setName(node.getProperty(Project.EXO_PROJECT_NAME).getString());
    project.setDescription(node.getProperty(Project.EXO_PROJECT_DESC).getString());
    project.setDefautlAssignee(node.getProperty(Project.EXO_PROJECT_DEFAULT_ASSIGNEE).getString());
    project.setProjectLead(node.getProperty(Project.EXO_PROJECT_LEAD).getString());
    project.setMembers(node.getProperty(Project.EXO_PROJECT_MEMBERS).getString());
    return project;
  }

}
