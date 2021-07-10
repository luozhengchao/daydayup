package context;

import core.env.Environment;

/**
 * @author luozhengchao
 * @date 2021/7/10 10:23 下午
 */
public class AnnotationConfigApplicationContext implements ConfigurableApplicationContext {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public String getDisPlayName() {
        return null;
    }

    @Override
    public ApplicationContext getParent() {
        return null;
    }

    @Override
    public void publishEvent(Object event) {

    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public Environment getEnvironment() {
        return null;
    }
}
