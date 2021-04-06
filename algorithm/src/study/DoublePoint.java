package study;

import java.util.*;

/**
 * 283、移动0 ：0移动到最后
 * 快慢指针
 * 一维数组坐标变换
 * 12004320
 * j = 0;慢指针控制不为0的下标
 * i = 0; 快指针遍历
 */
class Solution283 {
    public void moveZeroes(int[] nums) {
        // 快慢指针，慢指针指向最后一个不为0的数字，快慢指针间所有数字为0
        //慢指针
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (j < i) {
                    nums[j] = nums[i];
                    //快慢指针间所有数字为0（j 和 i做了交换）
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}

/**
 * 11. 盛最多水的容器
 * 设置双指针 ii,jj 分别位于容器壁两端，根据规则移动指针（后续说明），并且更新面积最大值 res，直到 i == j 时返回 res
 */
class Solution11 {
    public int maxArea(int[] height) {
        //双指针夹逼
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while (i < j) {
            if (height[i] > height[j]) {
                res = Math.max(res, (j - i) * height[j--]);
            } else {
                res = Math.max(res, (j - i) * height[i++]);
            }
        }
        return res;
    }
}

/**
 * 70. 爬楼梯
 * 动态规划
 * 递归 照规律，找重复子问题
 * 反向把所有可能列举出来
 * 时间复杂度：O(n)O(n)
 */
class Solution70 {
    public int climbStairs(int n) {
        int[] res = new int[n + 2];
        res[1] = 1;
        res[2] = 2;
        //把所有可能一次加入数组，空间存储
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * 1、三层暴力循环
 */
class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (len < 3) return ans;
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环-->做了排序
            if (nums[i] > 0) break;
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }
}

/**
 141. 环形链表
 给定一个链表，判断链表中是否有环。
 1、暴力法：set记录 如果重复则有换
 */
class Solution141 {
    public boolean hasCycle(ListNode head) {
        //1、双重循环  2.快慢指针 3、map
        Set<ListNode> set = new HashSet();
        set.add(head);
        while (head != null && head.next != null) {
            if (!set.add(head.next)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
