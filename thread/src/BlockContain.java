import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞容器:为空取等待 ；满了放等待
 *
 * @Author luozhengchao
 * @Date 2021/4/9 上午11:48
 */
public class BlockContain<T> {

    private volatile int capacity = 0;
    List<T> list;
    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition isFull = lock.newCondition();

    public BlockContain(int capacity) {
        this.list = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public boolean put(T t) {
        lock.lock();
        try {
            if (list.size() < capacity) {
                list.add(t);
                notEmpty.notify();
                return true;
            } else {
                isFull.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    public T get(int index){
        lock.lock();
        try {
            if (list.isEmpty()){
                //阻塞
                try {
                    notEmpty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                T remove = list.remove(index);
                isFull.notify();
                return remove;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return null;
    }

    public static void main(String[] args) {
        BlockContain<Integer> contain = new BlockContain<>(2);
        contain.put(1);
        contain.put(3);
        contain.put(6);
        Integer integer = contain.get(1);
        System.out.println(integer);
    }

}
