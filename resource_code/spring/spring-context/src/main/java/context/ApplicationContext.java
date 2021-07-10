package context;

import context.factory.ListableBeanFactory;
import core.EnvironmentCapable;

/**
 * Central interface to provide configuration for an application
 * Bean factory methods for accessing application components.
 * @author luozhengchao
 * @date 2021/7/10 4:14 下午
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ApplicationEventPublisher {

    /**
     * Return the unique id of thin application context
     * @return the unique id of the context, or {@conde null} if none
     */
    String getId();

    /**
     * Return a name for the deployed application that this context belongs to.
     * @return a name for the deployed application, or the empty String by default
     */
    String getApplicationName();

    /**
     * Return a friendly name for this context.
     * @return a display name for this context (never {@code null})
     */
    String getDisPlayName();

    /**
     * Return this parent context,or {@code null} if there is no parent and
     * this is the root of the context hierarchy.
     * @return
     */
    ApplicationContext getParent();


}
