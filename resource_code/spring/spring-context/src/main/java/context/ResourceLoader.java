package context;

/**
 * 加载资源的策略接口
 * Strategy interface for load resources
 * @author luozhengchao
 * @date 2021/7/10 10:40 下午
 */
public interface ResourceLoader {

    /**
     * Return a Resource handle for the specified resource location.
     * @param location
     * @return
     */
    Resource getResource(String location);

}
