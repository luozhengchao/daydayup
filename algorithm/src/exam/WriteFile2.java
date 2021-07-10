package exam;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author luozhengchao
 * @Date 2021/4/9 上午9:52
 */
public class WriteFile2 {

    static StringBuffer file1 = new StringBuffer();`
    static StringBuffer file2 = new StringBuffer();
    static StringBuffer file3 = new StringBuffer();
    static StringBuffer file4 = new StringBuffer();

    static Lock lock = new ReentrantLock(true);
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();
    static Condition conditionD = lock.newCondition();
    static List<Condition> list = Arrays.asList(conditionA,conditionB, conditionC, conditionD);
    static List<StringBuffer> fileList = Arrays.asList(file1,file2, file3, file4);


    public static void main(String[] args) throws Exception{
        write();
        Thread.sleep(10L);
        System.out.println(file1.toString());
        System.out.println(file2.toString());
        System.out.println(file3.toString());
        System.out.println(file4.toString());
    }

    public static void write() {
        new Thread(() -> append(file1,"A", conditionB), "thread1").start();
        new Thread(() -> append(file1,"B", conditionC), "thread2").start();
        new Thread(() -> append(file1,"C", conditionD), "thread3").start();
        new Thread(() -> append(file1,"D", conditionA), "thread4").start();
    }


    private static void append(StringBuffer file, String str, Condition condition) {
        while (true){
            lock.lock();
            try {
                //写文件
                file1.append(str);
                file.append(str);
                list.stream().filter(s -> s != condition).forEach(s -> {
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                condition.notify();
            }catch (Exception e){
            }finally {
               lock.unlock();
            }
        }
    }

}
