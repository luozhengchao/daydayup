package context.factory;

/**
 * the root interface for accessing a Spring bean container.
 *
 * @author luozhengchao
 * @date 2021/7/10 10:13 下午
 */
public interface BeanFactory {

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requiredType);
}
