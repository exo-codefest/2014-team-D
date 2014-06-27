package org.exoplatform.task.webui.component;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormSelectBox;
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
  public ProjectForm() {
    UIFormStringInput projectNameUIFormStringInput = new UIFormStringInput("projectName", "projectName", null);
    UIFormTextAreaInput projectDescriptionUIFormTextAreaInput = new UIFormTextAreaInput("projectDescription",
            "projectDescription", null);
    addUIFormInput(projectNameUIFormStringInput);
    addUIFormInput(projectDescriptionUIFormTextAreaInput);
    setActions(new String[]{"Save","Cancel"})  ;
  }

  static public class SaveActionListener extends EventListener<ProjectForm> {
    public void execute(Event<ProjectForm> event) throws Exception {
    }
  }

  static public class CancelActionListener extends EventListener<ProjectForm> {
    public void execute(Event<ProjectForm> event) throws Exception {
    }
  }
}
