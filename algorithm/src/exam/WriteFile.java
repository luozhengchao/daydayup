package exam;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author luozhengchao
 * @Date 2021/4/7 下午9:20
 */
public class WriteFile {
    //thread1  A
    static StringBuffer file = new StringBuffer();

    private static Map<String, Thread> map = new HashMap();

    static {
        map.put("A", new Thread() {
            @Override
            public void run() {
                file.append("A");
            }
        });
        map.put("B", new Thread() {
            @Override
            public void run() {
                file.append("B");
            }
        });
        map.put("C", new Thread() {
            @Override
            public void run() {
                file.append("C");
            }
        });
        map.put("D", new Thread() {
            @Override
            public void run() {
                file.append("D");
            }
        });
    }


    public static void write(int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            Thread thread1 = map.get("A");
            thread1.start();
            thread1.join();
            map.get("B").start();
            map.get("B").join();
            map.get("C").start();
            map.get("C").join();
            map.get("D").start();
            map.get("D").join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        write(1);
        System.out.println(file.toString());
    }

}
