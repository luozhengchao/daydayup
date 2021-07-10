package context;

/**
 * @author luozhengchao
 * @date 2021/7/10 10:03 下午
 */
public abstract class ApplicationEvent {

    private final long timestamp;

    public ApplicationEvent(Object source) {
        this.timestamp = System.currentTimeMillis();
    }
}
