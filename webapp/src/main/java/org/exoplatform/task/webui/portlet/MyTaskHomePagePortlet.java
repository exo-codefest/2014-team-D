package org.exoplatform.task.webui.portlet;

import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;


@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class,
        template = "app:/groovy/task-extension/webui/component/MyTaskHomePagePortlet.gtmpl")
public class MyTaskHomePagePortlet extends UIPortletApplication {
  public MyTaskHomePagePortlet() throws Exception {
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }
}
