package org.exoplatform.portlet.administration;

import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;


@ComponentConfig(lifecycle = UIApplicationLifecycle.class)
public class TaskManagementPortlet extends UIPortletApplication {
  public TaskManagementPortlet() throws Exception {
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    ((PortletRequestContext)context).getWriter().write("Hello World");
    return;
  }
}
