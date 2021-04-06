package test;

import java.util.Scanner;

/**
 * @Author luozhengchao
 * @Date 2021/3/21 下午5:09
 */
public class Main1 {
    public static void main(String[] args) {
        int maxLen = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++){
            strings[i] = scanner.next();
        }
        int [][] dp = new int[n][strings[0].length()];

        for (int i = 0; i< n; i++) {
            dp[i][0] = strings[i].charAt(0) - '0';
            if (dp[i][0] == 1){
                maxLen = 1;
            }
        }

        for (int i = 0; i< strings[0].length(); i++){
            dp[0][i] = strings[0].charAt(i) - '0';
            if (dp[0][i] == 1){
                maxLen = 1;
            }
        }

        for (int i = 1; i< n ; i++){
            for (int j = 1; j < strings[0].length(); j++){
                if (strings[i].charAt(j) == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]),dp[i-1][j]) + 1;
                    maxLen = maxLen > dp[i][j] ? maxLen : dp[i][j];
                }
            }
        }
        System.out.println(maxLen * maxLen);
    }

}
