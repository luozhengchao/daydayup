package test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 不能出现空轮询-->不能用CAS，Lock（会自旋），synchronized (自旋锁状态时也会空轮询)？
 * 不能出现连接阻塞--》不能用等待队列,wait?
 * 题意：多线程计算1-50万数据能有多少个数被3整除？
 *
 * @Author luozhengchao
 * @Date 2021/4/14 下午7:40
 */
public class BatchSolution {

    public static void main(String[] args) {
        List<Integer> batch = new LinkedList<>();
        //线程数，分批数
        int threadNum = 50;
        int sum = 50000;
        for (int i = 1; i < sum; i++) {
            batch.add(i);
        }

        AtomicInteger res = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        //分批
        split(threadNum, batch).stream().map(subList ->
                executorService.submit(() -> {
                    AtomicInteger subCount = new AtomicInteger();
                    subList.forEach(num -> {
                        if (num % 3 == 0) {
                            subCount.getAndIncrement();
                        }
                    });
                    return subCount.get();
                })
        ).forEach(sub -> {
            try {
                res.addAndGet(sub.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println(res.get());
    }

    /**
     * 分割
     * @param group
     * @param list
     * @return
     */
    private static List<List<Integer>> split(int group, List<Integer> list) {
        int t = list.size() / group;
        int limit = list.size() % group == 0 ? t : t - 1;
        return Stream.iterate(0, n -> n + 1)
                .limit(limit)
                .map(a -> list.stream().skip(a * group).limit(group).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }

}

