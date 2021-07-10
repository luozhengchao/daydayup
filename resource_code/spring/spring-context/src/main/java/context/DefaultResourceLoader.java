package context;

import context.util.Assert;

/**
 * @author luozhengchao
 * @date 2021/7/10 10:39 下午
 */
public class DefaultResourceLoader implements ResourceLoader{

    private ClassLoader classLoader;

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must not be null");

        return null;

    }
}


