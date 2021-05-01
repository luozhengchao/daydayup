import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author luozhengchao
 * @Date 2021/5/1 下午7:14
 */
public class ClassLoad_01 {

    public static void main(String[] args) {
        String a = new String("1a");
        String c = new String("1a");
        String b = "1";
        System.out.println("1".equals(a));
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(ClassLoad_01.class.getClassLoader());
        System.out.println(ConcurrentHashMap.class.getSuperclass());
        System.out.println(String.class.getClassLoader());


    }
}
