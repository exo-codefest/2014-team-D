package org.exoplatform.task.webui.component;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.codefest.services.api.ProjectManager;
import org.exoplatform.codefest.services.model.Project;
import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.task.webui.portlet.TaskManagementPortlet;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIPopupWindow;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "system:/groovy/webui/form/UIForm.gtmpl",
        events = {
                @EventConfig(listeners = ProjectForm.SaveActionListener.class),
                @EventConfig(listeners = ProjectForm.CancelActionListener.class)
        }
)
public class ProjectForm extends UIForm {

  private static final String PROJECT_NAME = "projectName";
  private static final String PROJECT_DESCRIPTION = "projectDescription";
  private static final String PROJECT_MEMBERS = "projectMembers";
  private String projectId = StringUtils.EMPTY;

  public ProjectForm() {
    UIFormStringInput projectNameUIFormStringInput = new UIFormStringInput(PROJECT_NAME, PROJECT_NAME, null);
    UIFormTextAreaInput projectDescriptionUIFormTextAreaInput = new UIFormTextAreaInput(PROJECT_DESCRIPTION,
            PROJECT_DESCRIPTION, null);
    addUIFormInput(projectNameUIFormStringInput);
    addUIFormInput(projectDescriptionUIFormTextAreaInput);
    setActions(new String[]{"Save","Cancel"})  ;
  }

  public void fillForm(Project project) {
    projectId = project.getName();
    getUIStringInput(PROJECT_NAME).setValue(project.getName());
    getUIFormTextAreaInput(PROJECT_DESCRIPTION).setValue(project.getDescription());
  }

  static public class SaveActionListener extends EventListener<ProjectForm> {
    public void execute(Event<ProjectForm> event) throws Exception {
      ProjectForm projectForm = event.getSource();
      String projectName = projectForm.getUIStringInput(PROJECT_NAME).getValue();
      String projectDescription = projectForm.getUIFormTextAreaInput(PROJECT_DESCRIPTION).getValue();
      ProjectManager projectManager = CoreUtils.getService(ProjectManager.class);
      if(projectForm.projectId.length() > 0) {
        projectManager.updateProject(projectForm.projectId, projectName, projectDescription, null);
      } else {
        projectManager.createProject(projectName, projectDescription, null);
      }
      event.getRequestContext().addUIComponentToUpdateByAjax(projectForm.getAncestorOfType(TaskManagementPortlet.class));
      projectForm.getAncestorOfType(UIPopupWindow.class).setRendered(false);
    }
  }

  static public class CancelActionListener extends EventListener<ProjectForm> {
    public void execute(Event<ProjectForm> event) throws Exception {
      ProjectForm projectForm = event.getSource();
      projectForm.getAncestorOfType(UIPopupWindow.class).setRendered(false);
    }
  }
}
