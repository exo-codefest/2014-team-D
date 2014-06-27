package org.exoplatform.task.webui.portlet;

import org.exoplatform.codefest.services.api.TaskManager;
import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.codefest.services.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

  public List<Task> getTaskList(String projectId) {
    List<Task> taskList = new ArrayList<Task>();
    Task tempTask = new Task();
    tempTask.setName("Can't add or edit \"Illustrated Web Content\" with filling field \"JS Data\"");
    tempTask.setDueDate(new Date());
    tempTask.setPriority("Minor");
    tempTask.setStatus("In progress");
    taskList.add(tempTask);

    tempTask = new Task();
    tempTask.setName("[IE] Failed upload and throw RepositoryException when upload a zip file");
    tempTask.setDueDate(new Date());
    tempTask.setPriority("Low");
    tempTask.setStatus("Open");
    taskList.add(tempTask);

    tempTask = new Task();
    tempTask.setName("Unplanned stuff for sprint PLF-2014-S26");
    tempTask.setDueDate(new Date());
    tempTask.setPriority("High");
    tempTask.setStatus("Closed");
    taskList.add(tempTask);

    tempTask = new Task();
    tempTask.setName("Impossible to open uploaded file in Chinese from Upload Status box");
    tempTask.setDueDate(new Date());
    tempTask.setPriority("High");
    tempTask.setStatus("In progress");
    taskList.add(tempTask);

    tempTask = new Task();
    tempTask.setName("Limit size of upload file if they are uploaded by webdav client tools.");
    tempTask.setDueDate(new Date());
    tempTask.setPriority("Low");
    tempTask.setStatus("Open");
    taskList.add(tempTask);

    tempTask = new Task();
    tempTask.setName("[Weemo-extension] Not redirected to space after created and space menu disappears when updating navigation of space");
    tempTask.setDueDate(new Date());
    tempTask.setPriority("High");
    tempTask.setStatus("Closed");
    taskList.add(tempTask);
    return taskList;
   // return CoreUtils.getService(TaskManager.class).getAllTaskInProject(projectId);
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
