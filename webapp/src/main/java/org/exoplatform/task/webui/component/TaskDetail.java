package org.exoplatform.task.webui.component;

/**
 * Created by minhdv on 6/29/14.
 */

import org.exoplatform.portal.webui.container.UIContainer;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

@ComponentConfig(
  template = "app:/groovy/task-extension/webui/component/TaskDetail.gtmpl",
  events = {
    @EventConfig(listeners = TaskDetail.EditTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.RemoveTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.CloneTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.MoveTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.WatchTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.ResolveTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.CloseTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.StartProgressTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.ReopenTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.WatchTaskActionListener.class),
    @EventConfig(listeners = TaskDetail.LogworkTaskActionListener.class)
  }
)
public class TaskDetail extends UIContainer {

  static public class EditTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class RemoveTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class CloneTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class MoveTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class WatchTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class ResolveTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class CloseTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class StartProgressTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class ReopenTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }

  static public class LogworkTaskActionListener extends EventListener<TaskDetail> {
    public void execute(Event<TaskDetail> event) throws Exception {
      TaskDetail taskDetail = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(taskDetail);
    }
  }
}
