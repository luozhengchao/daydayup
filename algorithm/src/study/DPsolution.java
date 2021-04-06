package study;

/**
 * @Author luozhengchao
 * @Date 2021/3/28 下午3:02
 */
public class DPsolution {

}

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 */
class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}


class SolutionFib {
    public static void main(String[] args) {
        int n = 100;
//        System.out.println(fib(n));
        System.out.println(fib(n, new int[n + 1]));

    }

    private static int fib(int n) {
        return n < 2 ? 1 : fib(n - 1) + fib(n - 2);
    }

    private static int fib(int n, int[] memo) {
        if (n < 2) {
            return 1;
        }
        if (memo[n] == 0) {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }
        return memo[n];
    }
}