package test;

import java.util.Scanner;

/**
 * @Author luozhengchao
 * @Date 2021/3/21 下午12:10
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //行数
        int m = sc.nextInt();
        String s1 = sc.nextLine();
        int n = s1.length();
        //定义二维数组
        int[][] rs = new int[m][n];
        int k = 0;
        boolean flag = false;
        for (int j = 0; j < n; j++) {
            rs[k][j] = (int) s1.charAt(j);
            if (rs[k][j] != 0 && ! flag){
                flag = true;
            }
        }
        while (sc.hasNextLine() && k < m) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                rs[k][j] = (int) s.charAt(j);
                if (rs[k][j] != 0 && ! flag){
                    flag = true;
                }
            }
            k++;
        }
        if (!flag) {
            System.out.println(0);
            return;
        }
        int max = -1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int min = rs[i - 1][j - 1];
                if (rs[i][j] == 1) {
                    min = rs[i - 1][j -1];
                    if (rs[i][j - 1] < min) {
                        min = rs[i][j - 1];
                    }
                    rs[i][j] += min;
                    if (max < rs[i][i]) {
                        max = rs[i][j];
                    }
                }
            }
        }
        System.out.println(max * max);
    }

}
