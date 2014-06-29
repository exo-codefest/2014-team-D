package org.exoplatform.task.webui.portlet;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.codefest.services.api.ProjectManager;
import org.exoplatform.codefest.services.api.TaskManager;
import org.exoplatform.codefest.services.model.Project;
import org.exoplatform.codefest.services.model.Task;
import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.task.webui.component.ProjectForm;
import org.exoplatform.task.webui.component.TaskForm;
import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIPopupWindow;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class,
        template = "app:/groovy/task-extension/webui/component/admin/TaskManagementPortlet.gtmpl",
        events = {
                @EventConfig(listeners = TaskManagementPortlet.CreateTaskActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.DeleteTaskActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.EditTaskActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.CreateProjectActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.DeleteProjectActionListener.class,
                  confirm = "project.msg.confirm-delete"),
                @EventConfig(listeners = TaskManagementPortlet.EditProjectActionListener.class),
                @EventConfig(listeners = TaskManagementPortlet.SelectProjectActionListener.class)

        })
public class TaskManagementPortlet extends UIPortletApplication {
  private String selectedProjectId;

  public TaskManagementPortlet() throws Exception {
    UIPopupWindow uiPopup = addChild(UIPopupWindow.class, null, "taskPopup");
  }

  public void initPopup(UIComponent uiComponent) throws Exception {
    UIPopupWindow uiPopup = getChildById("taskPopup");
    uiPopup.setRendered(true);
    uiPopup.setShowMask(true);
    uiPopup.setWindowSize(600, 270);
    uiPopup.setUIComponent(uiComponent);
    uiPopup.setShow(true);
    uiPopup.setResizable(true);
  }

  public List<Project> getProjects() throws Exception {
    return CoreUtils.getService(ProjectManager.class).getProjects();
  }

  public List<Task> getTaskList(String projectId) throws Exception {
    if (StringUtils.isEmpty(projectId)) return new ArrayList<Task>();
    return CoreUtils.getService(TaskManager.class).getAllTaskInProject(projectId);
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }

  public String getSelectedProjectId() {
    return selectedProjectId;
  }

  public void setSelectedProjectId(String selectedProjectId) {
    this.selectedProjectId = selectedProjectId;
  }

  static public class CreateProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
      ProjectForm projectForm = taskManagementPortlet.createUIComponent(ProjectForm.class, null, null);
      taskManagementPortlet.initPopup(projectForm);
      event.getRequestContext().addUIComponentToUpdateByAjax(taskManagementPortlet);
    }
  }

  static public class EditProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
      ProjectManager projectManager = CoreUtils.getService(ProjectManager.class);
      String projectId = event.getRequestContext().getRequestParameter(OBJECTID);
      ProjectForm projectForm = taskManagementPortlet.createUIComponent(ProjectForm.class, null, null);
      taskManagementPortlet.initPopup(projectForm);
      projectForm.fillForm(projectManager.getProjectById(projectId));
      event.getRequestContext().addUIComponentToUpdateByAjax(taskManagementPortlet);
    }
  }

  static public class DeleteProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
      ProjectManager projectManager = CoreUtils.getService(ProjectManager.class);
      String projectId = event.getRequestContext().getRequestParameter(OBJECTID);
      projectManager.removeProject(projectId);
      event.getRequestContext().addUIComponentToUpdateByAjax(taskManagementPortlet);
    }
  }

  static public class CreateTaskActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
      TaskForm taskForm = taskManagementPortlet.createUIComponent(TaskForm.class, null, null);
      taskManagementPortlet.initPopup(taskForm);
      event.getRequestContext().addUIComponentToUpdateByAjax(taskManagementPortlet);
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

  static public class SelectProjectActionListener extends EventListener<TaskManagementPortlet> {
    public void execute(Event<TaskManagementPortlet> event) throws Exception {
      TaskManagementPortlet taskManagementPortlet = event.getSource();
      String projectId = event.getRequestContext().getRequestParameter(OBJECTID);
      taskManagementPortlet.setSelectedProjectId(projectId);
      event.getRequestContext().addUIComponentToUpdateByAjax(taskManagementPortlet);
    }
  }
}
