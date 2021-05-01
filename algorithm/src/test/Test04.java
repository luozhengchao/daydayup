package test;

import java.util.*;

/**
 * @Author luozhengchao
 * @Date 2021/3/26 下午2:11
 */
public class Test04 {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList();
        // 排序
        int len = input.length;
        if (k > len) return res;
        for (int i = 0; i < len; i++) {
            if(res.size() > k) break;
            for (int j = 1; j < len - i; j++){
                int temp = input[j];
                if (input[i] < input[j]) {
                    input[j] = input[i];
                }
                input[i] = temp;
            }
            res.add(input[i]);

        }
        return res;

    }


    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(8);
        q.add(3);
        System.out.println(q.peek());//1
        System.out.println(q.poll());
        System.out.println(q.poll());

        Stack<Integer> s = new Stack();
        s.push(1);
        s.push(5);
        s.push(10);
        System.out.println(s.peek());
        System.out.println(s.pop());

        Queue<Integer> qq = new PriorityQueue<>(4, (a1,a2) -> a2 - a1);

        System.out.println(1%2);
    }

}
