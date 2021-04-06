package study;

import java.util.*;

/**
 * 堆，二叉堆（大顶堆）
 *
 * @Author luozhengchao
 * @Date 2021/3/28 下午2:20
 */
public class HeapSolution {

}

/**
 * 剑指 Offer 40. 最小的k个数
 */

class Solution40j {
    // 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
    // 1. 若目前堆的大小小于K，将当前数字放入堆中。
    // 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
    //    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;
    }
}


/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 *
 * 设窗口区间为 [i, j][i,j] ，最大值为 x_jx
 * j。当窗口向前移动一格，则区间变为 [i+1,j+1][i+1,j+1] ，即添加了 nums[j + 1]nums[j+1] ，删除了 nums[i]nums[i] 。

 */
class Solution59j {

    public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums.length == 0 || k == 0) return new int[0];
            Deque<Integer> deque = new LinkedList<>();
            int[] res = new int[nums.length - k + 1];
            for(int j = 0, i = 1 - k; j < nums.length; i++, j++) {
                // 删除 deque 中对应的 nums[i-1]
                if(i > 0 && deque.peekFirst() == nums[i - 1])
                    deque.removeFirst();
                // 保持 deque 递减
                while(!deque.isEmpty() && deque.peekLast() < nums[j])
                    deque.removeLast();
                deque.addLast(nums[j]);
                // 记录窗口最大值
                if(i >= 0)
                    res[i] = deque.peekFirst();
            }
            return res;
    }
}

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 */
class Solution {
    /**
     * 借助 哈希表 来建立数字和其出现次数的映射，遍历一遍数组统计元素的频率
     * 维护一个元素数目为 kk 的最小堆
     * 每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
     * 如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
     * 最终，堆中的 kk 个元素即为前 kk 个高频元素
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }

}

