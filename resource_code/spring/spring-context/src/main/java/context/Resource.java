package context;

import java.io.File;

/**
 * @author luozhengchao
 * @date 2021/7/10 10:46 下午
 */
public interface Resource {

    /**
     * Return a file handle for this resource.
     * @return
     */
    File getFile();
}
