package context;

/**
 * Interface that encapsulates event publication functionality
 * @author luozhengchao
 * @date 2021/7/10 9:52 下午
 */
@FunctionalInterface
public interface ApplicationEventPublisher {


    /**
     * publish
     * @param event
     */
    default void publishEvent(ApplicationEvent event){
        publishEvent((Object)event);
    }


    /**
     * Notify all matching listeners registered with this application of an event;
     * @param event
     */
    void publishEvent(Object event);

}
