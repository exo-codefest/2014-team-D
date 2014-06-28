package org.exoplatform.task.webui.component;

import org.exoplatform.codefest.services.api.ProjectManager;
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

  public ProjectForm() {
    UIFormStringInput projectNameUIFormStringInput = new UIFormStringInput(PROJECT_NAME, PROJECT_NAME, null);
    UIFormTextAreaInput projectDescriptionUIFormTextAreaInput = new UIFormTextAreaInput(PROJECT_DESCRIPTION,
            PROJECT_DESCRIPTION, null);
    addUIFormInput(projectNameUIFormStringInput);
    addUIFormInput(projectDescriptionUIFormTextAreaInput);
    setActions(new String[]{"Save","Cancel"})  ;
  }

  static public class SaveActionListener extends EventListener<ProjectForm> {
    public void execute(Event<ProjectForm> event) throws Exception {
      ProjectForm projectForm = event.getSource();
      String projectName = projectForm.getUIStringInput(PROJECT_NAME).getValue();
      String projectDescription = projectForm.getUIFormTextAreaInput(PROJECT_DESCRIPTION).getValue();
      CoreUtils.getService(ProjectManager.class).createProject(projectName, projectDescription, null);
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
