package experiment.four;

import java.util.*;

/**
 * 编辑距离 (Edit Distance / Levenshtein Distance)
 * 支持删除、插入、替换三种操作
 */
public class EditDistance {

    // 操作类型枚举
    enum OperationType {
        DELETE, INSERT, REPLACE, MATCH
    }

    /**
     * 操作类
     */
    static class Operation {
        OperationType type;
        int pos;
        char fromChar;
        char toChar;

        Operation(OperationType type, int pos, char fromChar, char toChar) {
            this.type = type;
            this.pos = pos;
            this.fromChar = fromChar;
            this.toChar = toChar;
        }

        @Override
        public String toString() {
            switch (type) {
                case DELETE:
                    return String.format("删除: 在位置%d删除字符'%c'", pos, fromChar);
                case INSERT:
                    return String.format("插入: 在位置%d插入字符'%c'", pos, toChar);
                case REPLACE:
                    return String.format("替换: 在位置%d将'%c'替换为'%c'", pos, fromChar, toChar);
                case MATCH:
                    return String.format("匹配: 位置%d的字符'%c'相同", pos, fromChar);
                default:
                    return "";
            }
        }
    }

    static class Editor {
        private String s1, s2;
        private int[][] dp;
        private int m, n;

        Editor(String s1, String s2) {
            this. s1 = s1;
            this.s2 = s2;
            this.m = s1.length();
            this.n = s2.length();
            this.dp = new int[m + 1][n + 1];
        }

        /**
         * 计算编辑距离
         */
        int solve() {
            // 初始化
            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= n; j++) {
                dp[0][j] = j;
            }

            // 填充DP表
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + Math.min(
                            Math.min(dp[i - 1][j],      // 删除
                                     dp[i][j - 1]),     // 插入
                            dp[i - 1][j - 1]            // 替换
                        );
                    }
                }
            }

            return dp[m][n];
        }

        /**
         * 获取变换步骤
         */
        List<Operation> getTransformSteps() {
            List<Operation> steps = new ArrayList<>();
            int i = m, j = n;
            int pos = m;

            while (i > 0 || j > 0) {
                if (i == 0) {
                    // 只剩插入操作
                    steps. add(0, new Operation(
                        OperationType.INSERT, pos + 1, ' ', s2.charAt(j - 1)
                    ));
                    j--;
                    pos++;
                } else if (j == 0) {
                    // 只剩删除操作
                    steps.add(0, new Operation(
                        OperationType.DELETE, pos, s1.charAt(i - 1), ' '
                    ));
                    i--;
                    pos--;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // 字符匹配
                    steps.add(0, new Operation(
                        OperationType.MATCH, pos, s1.charAt(i - 1), s2.charAt(j - 1)
                    ));
                    i--;
                    j--;
                    pos--;
                } else {
                    // 比较三种操作的成本
                    int delete = dp[i - 1][j];
                    int insert = dp[i][j - 1];
                    int replace = dp[i - 1][j - 1];

                    if (delete < insert && delete < replace) {
                        steps.add(0, new Operation(
                            OperationType.DELETE, pos, s1.charAt(i - 1), ' '
                        ));
                        i--;
                        pos--;
                    } else if (insert < replace) {
                        steps.add(0, new Operation(
                            OperationType.INSERT, pos + 1, ' ', s2.charAt(j - 1)
                        ));
                        j--;
                        pos++;
                    } else {
                        steps.add(0, new Operation(
                            OperationType.REPLACE, pos, s1.charAt(i - 1), s2.charAt(j - 1)
                        ));
                        i--;
                        j--;
                    }
                }
            }

            return steps;
        }

        /**
         * 获取编辑距离值
         */
        int getDistance() {
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

        System.out.println("========== 编辑距离求解器 ==========\n");

        // 内置测试用例
        String[][] testCases = {
            {"kitten", "sitting"},
            {"saturday", "sunday"},
            {"abc", "abc"},
            {"abc", "def"},
            {"horse", "ros"}
        };

        System.out.println("【预设测试用例】\n");
        for (int i = 0; i < testCases.length; i++) {
            String s1 = testCases[i][0];
            String s2 = testCases[i][1];

            System.out.println("测试" + (i + 1) + ":");
            System.out.println("源字符串: " + s1);
            System.out. println("目标字符串: " + s2);

            Editor editor = new Editor(s1, s2);
            int distance = editor.solve();
            List<Operation> steps = editor.getTransformSteps();

            System.out.println("编辑距离: " + distance);
            System.out. println("变换步骤:");

            int operationCount = 0;
            for (Operation op : steps) {
                if (op.type != OperationType.MATCH) {
                    operationCount++;
                    System.out.println("  " + operationCount + ".  " + op);
                }
            }

            System. out.println();
        }

        // 用户输入
        while (true) {
            System. out.println("\n【用户输入】");
            System.out.print("输入源字符串 (或输入 'exit' 退出): ");
            String s1 = scanner.nextLine();

            if (s1.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("输入目标字符串: ");
            String s2 = scanner.nextLine();

            Editor editor = new Editor(s1, s2);
            int distance = editor.solve();
            List<Operation> steps = editor.getTransformSteps();

            System.out.println("\n结果:");
            System.out. println("源字符串: " + s1);
            System.out.println("目标字符串: " + s2);
            System. out.println("编辑距离: " + distance);

            System.out.println("\n变换步骤:");
            int operationCount = 0;
            for (Operation op : steps) {
                if (op.type != OperationType.MATCH) {
                    operationCount++;
                    System.out.println("  " + operationCount + ". " + op);
                }
            }

            System.out.print("\n是否显示DP表? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                editor.printDP();
            }
        }

        scanner.close();
        System.out.println("\n程序结束");
    }
}