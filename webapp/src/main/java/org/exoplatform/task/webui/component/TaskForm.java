package org.exoplatform.task.webui.component;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;


@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "system:/groovy/webui/form/UIForm.gtmpl",
        events = {
                @EventConfig(listeners = TaskForm.SaveActionListener.class),
                @EventConfig(listeners = TaskForm.CancelActionListener.class)
        }
)
public class TaskForm extends UIForm {
  static public class SaveActionListener extends EventListener<TaskForm> {
    public void execute(Event<TaskForm> event) throws Exception {
    }
  }

  static public class CancelActionListener extends EventListener<TaskForm> {
    public void execute(Event<TaskForm> event) throws Exception {
    }
  }
}