package context.util;

/**
 * @author luozhengchao
 * @date 2021/7/10 10:52 下午
 */
public class Assert {

    public static void notNull(String str, String errorMsg) {
        if (null == str || "".equals(str)) {
            throw new RuntimeException(errorMsg);
        }
    }
}
