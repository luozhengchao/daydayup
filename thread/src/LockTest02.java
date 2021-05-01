import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * @Author luozhengchao
 * @Date 2021/5/1 下午1:53
 */
public class LockTest02 {

    int n;
    volatile int i = 1;
    volatile int num = 0;
    static Map<String, Thread> map = new ConcurrentHashMap<>(4);

    public LockTest02(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        LockTest02 lockTest = new LockTest02(20);
        Thread t1 = new Thread(lockTest::zero, "t1");
        Thread t2 = new Thread(lockTest::even, "t2");
        Thread t3 = new Thread(lockTest::odd, "t3");

        map.put("t1", t1);
        map.put("t2", t2);
        map.put("t3", t3);
        map.forEach((k, v) -> v.start());
    }


    private void zero() {
        while (i <= n) {
            while (num != 0) {
                LockSupport.park();
            }
            print(0);
            num = i % 2 == 0 ? 2 : 1;
            map.forEach((k, v) -> LockSupport.unpark(v));
        }
    }

    private void even() {
        while (i <= n) {
            while(num != 1){
                LockSupport.park();
            }
            print(i++);
            num = 0;
            LockSupport.unpark(map.get("t1"));
        }
    }

    private void odd() {
        while (i <= n) {
            while(num != 2){
                LockSupport.park();
            }
            print(i++);
            num = 0;
            LockSupport.unpark(map.get("t1"));
        }
    }

    private static void print(int i) {
        System.out.print(i);
    }

}
