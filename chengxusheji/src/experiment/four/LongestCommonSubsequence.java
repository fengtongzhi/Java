package experiment.four;

import java.util.*;

/**
 * 最长公共子序列 (Longest Common Subsequence)
 * 使用动态规划求解
 */
public class LongestCommonSubsequence {

    static class LCS {
        private String s1, s2;
        private int[][] dp;
        private int m, n;

        LCS(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
            this.m = s1.length();
            this.n = s2.length();
            this.dp = new int[m + 1][n + 1];
        }

        /**
         * 计算最长公共子序列
         */
        String solve() {
            // 填充DP表
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            // 回溯构建LCS
            return buildLCS();
        }

        /**
         * 通过回溯构建LCS字符串
         */
        private String buildLCS() {
            StringBuilder result = new StringBuilder();
            int i = m, j = n;

            while (i > 0 && j > 0) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    result.insert(0, s1.charAt(i - 1));
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }

            return result.toString();
        }

        /**
         * 获取LCS的长度
         */
        int getLength() {
            return dp[m][n];
        }

        /**
         * 打印DP表
         */
        void printDP() {
            System. out.println("\nDP表:");
            System.out.print("    ");
            for (int j = 0; j < n; j++) {
                System.out.print(s2.charAt(j) + " ");
            }
            System. out.println();

            for (int i = 0; i <= m; i++) {
                if (i > 0) {
                    System.out.print(s1.charAt(i - 1) + " ");
                } else {
                    System.out.print("  ");
                }
                for (int j = 0; j <= n; j++) {
                    System.out.print(dp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== 最长公共子序列求解器 ==========\n");

        // 内置测试用例
        String[][] testCases = {
            {"pear", "peach"},
            {"AGGTAB", "GXTXAYB"},
            {"abc", "def"},
            {"hello", "hallo"},
            {"ABCDGH", "AEDFHR"}
        };

        System.out.println("【预设测试用例】\n");
        for (int i = 0; i < testCases.length; i++) {
            String s1 = testCases[i][0];
            String s2 = testCases[i][1];

            System. out.println("测试" + (i + 1) + ":");
            System.out.println("字符串1: " + s1);
            System.out.println("字符串2: " + s2);

            LCS lcs = new LCS(s1, s2);
            String result = lcs.solve();

            System.out.println("最长公共子序列: " + result);
            System.out.println("长度: " + lcs.getLength());
            System.out.println();
        }

        // 用户输入
        while (true) {
            System. out.println("\n【用户输入】");
            System.out.print("输入第一个字符串 (或输入 'exit' 退出): ");
            String s1 = scanner.nextLine();

            if (s1.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("输入第二个字符串: ");
            String s2 = scanner.nextLine();

            LCS lcs = new LCS(s1, s2);
            String result = lcs.solve();

            System.out. println("\n结果:");
            System.out.println("字符串1: " + s1);
            System.out.println("字符串2: " + s2);
            System.out.println("最长公共子序列: " + result);
            System.out.println("长度: " + lcs.getLength());

            System.out.print("是否显示DP表? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                lcs.printDP();
            }
        }

        scanner.close();
        System.out.println("\n程序结束");
    }
}
