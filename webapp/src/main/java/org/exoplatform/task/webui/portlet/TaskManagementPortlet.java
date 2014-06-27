package org.exoplatform.task.webui.portlet;

import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class,
        template = "app:/groovy/task-extension/webui/component/admin/TaskManagementPortlet.gtmpl",
        events = {
                @EventConfig(listeners = TaskManagementPortlet.CreateTaskActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.DeleteTaskActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.EditTaskActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.CreateProjectActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.DeleteProjectActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.EditProjectActionListener.class)
        })
public class TaskManagementPortlet extends UIPortletApplication {
  public TaskManagementPortlet() throws Exception {
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }

  static public class CreateProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
    }
  }

  static public class EditProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
    }
  }

  static public class DeleteProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
    }
  }

  static public class CreateTaskActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
    }
  }

  static public class DeleteTaskActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
    }
  }

  static public class EditTaskActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
    }
  }
}
