package org.exoplatform.portlet.administration;

import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.config.annotation.EventConfig;


@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class,
        template = "app:/groovy/task-extension/webui/component/admin/TaskManagementPortlet.gtmpl")
public class TaskManagementPortlet extends UIPortletApplication {
  public TaskManagementPortlet() throws Exception {
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }
}
