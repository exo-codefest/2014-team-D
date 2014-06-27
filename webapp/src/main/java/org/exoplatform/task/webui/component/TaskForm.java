package org.exoplatform.task.webui.component;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
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

  public TaskForm() {
    List<SelectItemOption<String>> projectOptions = new ArrayList<SelectItemOption<String>>();
    projectOptions.add(new SelectItemOption<String>("Uncategoried", "Uncategoried"));
    projectOptions.add(new SelectItemOption<String>("HR", "hr"));
    projectOptions.add(new SelectItemOption<String>("ECMS", "ecms"));
    UIFormSelectBox taskProjectUIFormSelectBox = new UIFormSelectBox("taskProject", "taskProject", projectOptions);
    UIFormStringInput taskNameUIFormStringInput = new UIFormStringInput("taskName", "taskName", null);
    UIFormDateTimeInput taskDueDateUIFormDateTimeInput = new UIFormDateTimeInput("taskDueDate", "taskDueDate", null);
    List<SelectItemOption<String>> priorityOptions = new ArrayList<SelectItemOption<String>>();
    priorityOptions.add(new SelectItemOption<String>("High", "high"));
    priorityOptions.add(new SelectItemOption<String>("Medium", "medium"));
    priorityOptions.add(new SelectItemOption<String>("Minor", "minor"));
    UIFormSelectBox taskPriorityUIFormSelectBox = new UIFormSelectBox("taskPriority", "taskPriority", priorityOptions);
    List<SelectItemOption<String>> statusOptions = new ArrayList<SelectItemOption<String>>();
    statusOptions.add(new SelectItemOption<String>("Open", "open"));
    statusOptions.add(new SelectItemOption<String>("In progress", "inprogress"));
    statusOptions.add(new SelectItemOption<String>("Closed", "closed"));
    UIFormSelectBox taskStatusUIFormSelectBox = new UIFormSelectBox("taskStatus", "taskStatus", statusOptions);
    addUIFormInput(taskProjectUIFormSelectBox);
    addUIFormInput(taskNameUIFormStringInput);
    addUIFormInput(taskDueDateUIFormDateTimeInput);
    addUIFormInput(taskPriorityUIFormSelectBox);
    addUIFormInput(taskStatusUIFormSelectBox);
    setActions(new String[]{"Save","Cancel"})  ;
  }

  static public class SaveActionListener extends EventListener<TaskForm> {
    public void execute(Event<TaskForm> event) throws Exception {
    }
  }

  static public class CancelActionListener extends EventListener<TaskForm> {
    public void execute(Event<TaskForm> event) throws Exception {
    }
  }
}