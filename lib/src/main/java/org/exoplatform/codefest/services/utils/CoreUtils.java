package org.exoplatform.codefest.services.utils;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.ext.hierarchy.NodeHierarchyCreator;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by minhdv on 6/26/14.
 */
public class CoreUtils {

  private static final Log LOG = ExoLogger.getLogger(CoreUtils.class.getName());
  /**
   * Gets the service.
   *
   * @param clazz the clazz
   *
   * @return the service
   */
  public static <T> T getService(Class<T> clazz) {
    return getService(clazz, null);
  }

  /**
   * Gets the service.
   *
   * @param clazz the class
   * @param containerName the container's name
   *
   * @return the service
   */
  public static <T> T getService(Class<T> clazz, String containerName) {
    ExoContainer container = ExoContainerContext.getCurrentContainer();
    if (containerName != null) {
      container = RootContainer.getInstance().getPortalContainer(containerName);
    }
    if (container.getComponentInstanceOfType(clazz)==null) {
      containerName = PortalContainer.getCurrentPortalContainerName();
      container = RootContainer.getInstance().getPortalContainer(containerName);
    }
    return clazz.cast(container.getComponentInstanceOfType(clazz));
  }

  /**
   * Gets the system session provider.
   *
   * @return the system session provider
   */
  public static SessionProvider getSystemSessionProvider() {
    SessionProviderService sessionProviderService = getService(SessionProviderService.class);
    return sessionProviderService.getSystemSessionProvider(null);
  }

  /**
   * Gets the session provider.
   *
   * @return the session provider
   */
  public static SessionProvider getUserSessionProvider() {
    SessionProviderService sessionProviderService = getService(SessionProviderService.class);
    return sessionProviderService.getSessionProvider(null);
  }

  /**
   * Get the current repository
   *
   * @return the current manageable repository
   */
  public static ManageableRepository getRepository() {
    try {
      RepositoryService repositoryService = getService(RepositoryService.class);
      return repositoryService.getCurrentRepository();
    } catch (Exception e) {
      if (LOG.isErrorEnabled()) {
        LOG.error("getRepository() failed because of ", e);
      }
    }
    return null;
  }

  public static Node getProjectRootNode() throws RepositoryException {
    NodeHierarchyCreator nodeHierarchyCreator = getService(NodeHierarchyCreator.class);
    String path = nodeHierarchyCreator.getJcrPath("projectManagement");
    Session session = getSystemSessionProvider().getSession(System.getProperty("gatein.jcr.workspace.default"), getRepository());
    return (Node) session.getItem(path);
  }
}
