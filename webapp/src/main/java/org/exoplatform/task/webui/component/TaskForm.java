package org.exoplatform.task.webui.component;

import org.exoplatform.codefest.services.api.ProjectManager;
import org.exoplatform.codefest.services.api.TaskManager;
import org.exoplatform.codefest.services.model.Project;
import org.exoplatform.codefest.services.model.Task;
import org.exoplatform.codefest.services.utils.CoreUtils;
import org.exoplatform.task.webui.portlet.TaskManagementPortlet;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIPopupWindow;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormDateTimeInput;
import org.exoplatform.webui.form.UIFormSelectBox;
import org.exoplatform.webui.form.UIFormStringInput;

import java.util.ArrayList;
import java.util.List;


@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "system:/groovy/webui/form/UIForm.gtmpl",
        events = {
                @EventConfig(listeners = TaskForm.SaveActionListener.class),
                @EventConfig(listeners = TaskForm.CancelActionListener.class)
        }
)
public class TaskForm extends UIForm {

  private static final String TASK_PROJECT_ID = "taskProjectId";
  private static final String TASK_PROJECT_SUMMARY = "taskProjectSummary";
  private static final String TASK_PROJECT_DESCRIPTION = "taskProjectDescription";
  private static final String TASK_PROJECT_PRIORITY = "taskProjectPriority";
  private static final String TASK_PROJECT_STATUS = "taskProjectStatus";
  private static final String TASK_PROJECT_REPORTER = "taskReporterReporter";
  private static final String TASK_PROJECT_ASSIGNEE = "taskProjectAssignee";
  private static final String TASK_PROJECT_ESTIMATE_TIME = "taskProjectEstimateTime";
  private static final String TASK_DUE_DATE = "taskDueDate";

  public TaskForm() throws Exception {


    // ProjectId
    List<SelectItemOption<String>> projectOptions = new ArrayList<SelectItemOption<String>>();
    List<Project> allProjects = CoreUtils.getService(ProjectManager.class).getProjects();
    for (Project project : allProjects) {
      projectOptions.add(new SelectItemOption<String>(project.getName(), project.getName()));
    }
    UIFormSelectBox taskProjectIdUIFormSelectBox = new UIFormSelectBox(TASK_PROJECT_ID, TASK_PROJECT_ID, projectOptions);

    // Summary
    UIFormStringInput taskProjectSummaryUIFormStringInput = new UIFormStringInput(TASK_PROJECT_SUMMARY, TASK_PROJECT_SUMMARY, null);

    // Description
    //UIFormTextAreaInput taskProjectDescriptionUIFormTextAreaInput = new UIFormTextAreaInput(TASK_PROJECT_DESCRIPTION, TASK_PROJECT_DESCRIPTION, null);

    // Priority
    List<SelectItemOption<String>> priorityOptions = new ArrayList<SelectItemOption<String>>();
    priorityOptions.add(new SelectItemOption<String>("High", "high"));
    priorityOptions.add(new SelectItemOption<String>("Medium", "medium"));
    priorityOptions.add(new SelectItemOption<String>("Low", "low"));
    UIFormSelectBox taskProjectPriorityUIFormSelectBox = new UIFormSelectBox(TASK_PROJECT_PRIORITY, TASK_PROJECT_PRIORITY, priorityOptions);

    // Status
//    List<SelectItemOption<String>> statusOptions = new ArrayList<SelectItemOption<String>>();
//    statusOptions.add(new SelectItemOption<String>(TaskState.STATE_OPEN , TaskState.STATE_OPEN));
//    statusOptions.add(new SelectItemOption<String>(TaskState.STATE_REOPENED , TaskState.STATE_REOPENED));
//    statusOptions.add(new SelectItemOption<String>(TaskState.STATE_INPREGRESS , TaskState.STATE_INPREGRESS));
//    statusOptions.add(new SelectItemOption<String>(TaskState.STATE_REOPENED , TaskState.STATE_RESOLVED));
//    statusOptions.add(new SelectItemOption<String>(TaskState.STATE_CLOSED , TaskState.STATE_CLOSED));
//    statusOptions.add(new SelectItemOption<String>(TaskState.STATE_AWAITING , TaskState.STATE_AWAITING));
//    UIFormSelectBox taskProjectStatusUIFormSelectBox = new UIFormSelectBox(TASK_PROJECT_STATUS, TASK_PROJECT_STATUS, statusOptions);

    // Assignee
    UIFormStringInput taskProjectAssigneeUIFormStringInput = new UIFormStringInput(TASK_PROJECT_ASSIGNEE, TASK_PROJECT_ASSIGNEE, null);

    // Estimate time
    //UIFormStringInput taskProjectEstimateTimeUIFormStringInput = new UIFormStringInput(TASK_PROJECT_ESTIMATE_TIME, TASK_PROJECT_ESTIMATE_TIME, null);

    // Due Date
    UIFormDateTimeInput taskProjectDueDateUIFormDateTimeInput = new UIFormDateTimeInput(TASK_DUE_DATE, TASK_DUE_DATE, null);

    addUIFormInput(taskProjectIdUIFormSelectBox);
    addUIFormInput(taskProjectSummaryUIFormStringInput);
    //addUIFormInput(taskProjectDescriptionUIFormTextAreaInput);
    addUIFormInput(taskProjectPriorityUIFormSelectBox);
    //addUIFormInput(taskProjectStatusUIFormSelectBox);
    addUIFormInput(taskProjectAssigneeUIFormStringInput);
    //addUIFormInput(taskProjectEstimateTimeUIFormStringInput);
    addUIFormInput(taskProjectDueDateUIFormDateTimeInput);

    setActions(new String[]{"Save", "Cancel"});
  }

  static public class SaveActionListener extends EventListener<TaskForm> {
    public void execute(Event<TaskForm> event) throws Exception {
      TaskForm taskForm = event.getSource();
      String projectId = taskForm.getUIStringInput(TASK_PROJECT_ID).getValue();
      Task task = new Task();
      task.setProjectId(projectId);
      task.setSummary(taskForm.getUIStringInput(TASK_PROJECT_SUMMARY).getValue());
      task.setDueDate(taskForm.getUIFormDateTimeInput(TASK_DUE_DATE).getCalendar());
      //task.setStatus(taskForm.getUIFormSelectBox(TASK_PROJECT_STATUS).getValue());
      task.setAssignee(taskForm.getUIStringInput(TASK_PROJECT_ASSIGNEE).getValue());
      task.setPriority(taskForm.getUIFormSelectBox(TASK_PROJECT_PRIORITY).getValue());
      task.setReporter(event.getRequestContext().getRemoteUser());
      CoreUtils.getService(TaskManager.class).createTask(projectId, task);
      event.getRequestContext().addUIComponentToUpdateByAjax(taskForm.getAncestorOfType(TaskManagementPortlet.class));
      taskForm.getAncestorOfType(UIPopupWindow.class).setRendered(false);
    }
  }

  static public class CancelActionListener extends EventListener<TaskForm> {
    public void execute(Event<TaskForm> event) throws Exception {
      TaskForm taskForm = event.getSource();
      taskForm.getAncestorOfType(UIPopupWindow.class).setRendered(false);
    }
  }
}