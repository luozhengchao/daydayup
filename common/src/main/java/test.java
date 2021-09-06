import java.lang.reflect.Constructor;

/**
 * @author luozhengchao
 * @date 2021/9/5 9:39 上午
 */
public class test {

    public static void main(String[] args) throws Exception {
        var test01Class = Test01.class;

        var constructor = test01Class.getConstructor();
        var test01 = constructor.newInstance();
    }

}
