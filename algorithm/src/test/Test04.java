package test;

import java.util.ArrayList;

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

}
