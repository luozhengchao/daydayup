package study;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author luozhengchao
 * @Date 2021/3/28 下午1:33
 */
public class DequeSolution {

}

/**
 * 20. 有效的括号
 * 栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；
 */
class Solution20 {
    public boolean isValid(String s) {
        // 建立映射关系
        Map<Character, Character> map = new HashMap(3) {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
        }};
        // 双端队列 左括号先入栈，如果头匹配又括号则消除 ，最后判断是否为空
        Deque<Character> deque = new LinkedList();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                deque.addFirst(c);
            } else {
                if (deque.isEmpty()) {
                    return false;
                }
                if (c != map.get(deque.poll())) {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }
}

/**
 * 84. 柱状图中最大的矩形
 *1、暴力：依次遍历柱形的高度，对于每一个高度分别向两边扩散，求出以当前高度为矩形的最大宽度多少。
 */
class Solution84 {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        // 特判
        if (len == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }

            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }
}

/**
 * 242. 有效的字母异位词
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 */
class Solution242 {
    public boolean isAnagram(String s, String t) {
        // 1.转化为数组  2.排序  3、比较是否一致
        // 1、遍历 插入哈希表，比较最后是否一致
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);
        return Arrays.equals(ss, tt);
    }
}

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 */
class Solution49 {
    /**
     * 注意 groupingBy 算子计算完以后，返回的是一个 Map<String, List<String>>，map 的键是每种排序后的字符串，值是聚合的原始字符串，我们只关心值，所以我们最后 new ArrayList<>(map.values())
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    // 返回 str 排序后的结果。
                    // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
                    char[] array = str.toCharArray();
                    Arrays.sort(array);
                    return new String(array);
                })).values());
    }
}

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 */
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        // map 实现
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

