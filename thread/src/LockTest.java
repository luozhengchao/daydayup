/**
 * @Author luozhengchao
 * @Date 2021/5/1 下午1:53
 */
public class LockTest {

    int n;
    int i = 1;

    public LockTest(int n) {
        this.n = n;
    }

    volatile boolean zero = true;
    volatile boolean even = false;
    volatile boolean odd = false;

    public static void main(String[] args) {
        LockTest lockTest = new LockTest(20);
        new Thread(lockTest::zero, "t1").start();
        new Thread(lockTest::even, "t2").start();
        new Thread(lockTest::odd, "t3").start();

    }


    private void zero() {
        while (i <= n) {
            while (!zero) ;
            print(0);
            zero = false;
            if (i % 2 == 1) {
                even = true;
            } else {
                odd = true;
            }
        }
    }

    private void even() {
        while (i <= n) {
            while (!even) ;
            print(i++);
            even = false;
            zero = true;
        }
    }

    private void odd() {
        while (i <= n) {
            while (!odd) ;
            print(i++);
            odd = false;
            zero = true;
        }
    }

    private static void print(int i) {
        System.out.print(i);
    }

}
