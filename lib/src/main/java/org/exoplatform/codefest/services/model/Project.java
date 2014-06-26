package org.exoplatform.codefest.services.model;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.codefest.services.utils.CoreUtils;

import javax.jcr.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by minhdv on 6/26/14.
 */
public class Project {

  public static final String EXO_PROJECT = "exo:project";
  public static final String EXO_PROJECT_NAME = "exo:projectName";
  public static final String EXO_PROJECT_DESC = "exo:projectDescription";
  public static final String EXO_PROJECT_MEMBERS = "exo:projectMembers";
  public static final String EXO_PROJECT_ROLES = "exo:projectRoles";
  public static final String EXO_PROJECT_AVATAR = "exo:projectAvatar";
  public static final String EXO_PROJECT_LEAD = "exo:projectLead";


  private String pName = StringUtils.EMPTY;
  private String pDesc = StringUtils.EMPTY;
  private String members = StringUtils.EMPTY;
  private String pLead = StringUtils.EMPTY;
  private ProjectACL pRoles;


  public Project() {
  }

  public Project(String pName) {
    this.pName = pName;
  }

  public Project(String name, String description) {
    this.pName = name;
    this.pDesc = description;
  }

  public Project(String name, String description, String members) {
    this.pName = name;
    this.pDesc = description;
    this.members = members;
  }

  public String getName() {
    return pName;
  }

  public void setName(String name) {
    this.pName = name;
  }

  public String getDescription() { return pDesc; }

  public void setDescription(String description) {
    this.pDesc = description;
  }

  public List<String> getMembers() {
    List<String> memberList = new ArrayList<String>();
    if(members.indexOf(",") > -1) {
      memberList = new ArrayList<String>(Arrays.asList(members.split(",")));
    } else {
      memberList = new ArrayList<String>(Arrays.asList(new String[] {members}));
    }
    return memberList;
  }

  public void setMembers(String members) {
    this.members = members;
  }

  public String getProjectLead() {
    return pLead;
  }

  public void setProjectLead(String pLead) {
    this.pLead = pLead;
  }

  public ProjectACL getProjectRoles() {
    return pRoles;
  }

  public void setProjectRoles(ProjectACL pRoles) {
    this.pRoles = pRoles;
  }

  public void create() throws Exception {
    Node projectRoot = CoreUtils.getProjectRootNode();
    Node project = projectRoot.addNode(this.getName(), EXO_PROJECT);
    project.setProperty(EXO_PROJECT_NAME, pName);
    project.setProperty(EXO_PROJECT_DESC, pDesc);
    project.setProperty(EXO_PROJECT_LEAD, pLead);
    project.setProperty(EXO_PROJECT_MEMBERS, members);
    projectRoot.save();
  }

  public void update() throws Exception {
    Node projectRoot = CoreUtils.getProjectRootNode();
    Node project = projectRoot.getNode(pName);
    project.setProperty(EXO_PROJECT_NAME, pName);
    project.setProperty(EXO_PROJECT_DESC, pDesc);
    project.setProperty(EXO_PROJECT_LEAD, pLead);
    project.setProperty(EXO_PROJECT_MEMBERS, members);
    project.save();
  }

  public void remove() throws Exception {
    Node projectRoot = CoreUtils.getProjectRootNode();
    projectRoot.getNode(pName).remove();
    projectRoot.save();
  }

}
