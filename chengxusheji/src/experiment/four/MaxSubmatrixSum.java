package experiment.four;

import java.util.*;

/**
 * 最大子矩阵和求解
 * 使用动态规划和Kadane算法的组合
 * 时间复杂度：O(rows^2 * cols)
 */
public class MaxSubmatrixSum {

    static class SubmatrixSolver {
        private int[][] matrix;
        private int rows, cols;
        private int maxSum;
        private int startRow, endRow, startCol, endCol;

        SubmatrixSolver(int[][] matrix) {
            this.matrix = matrix;
            this.rows = matrix.length;
            this.cols = rows > 0 ? matrix[0]. length : 0;
            this.maxSum = Integer.MIN_VALUE;
        }

        /**
         * 暴力求解 - O(rows^2 * cols^2)
         * 适合小规模矩阵
         */
        int solveBruteForce() {
            maxSum = Integer.MIN_VALUE;

            // 枚举所有子矩阵
            for (int r1 = 0; r1 < rows; r1++) {
                for (int r2 = r1; r2 < rows; r2++) {
                    for (int c1 = 0; c1 < cols; c1++) {
                        for (int c2 = c1; c2 < cols; c2++) {
                            // 计算子矩阵和
                            int sum = 0;
                            for (int i = r1; i <= r2; i++) {
                                for (int j = c1; j <= c2; j++) {
                                    sum += matrix[i][j];
                                }
                            }

                            if (sum > maxSum) {
                                maxSum = sum;
                                startRow = r1;
                                endRow = r2;
                                startCol = c1;
                                endCol = c2;
                            }
                        }
                    }
                }
            }

            return maxSum;
        }

        int solveOptimized() {
            maxSum = Integer.MIN_VALUE;

            // 对于每一行的组合 (r1, r2)
            for (int r1 = 0; r1 < rows; r1++) {
                // 初始化列求和数组
                int[] colSum = new int[cols];

                for (int r2 = r1; r2 < rows; r2++) {
                    // 更新列求和：加上第r2行
                    for (int c = 0; c < cols; c++) {
                        colSum[c] += matrix[r2][c];
                    }

                    // 对colSum应用Kadane算法求最大子数组和
                    int[] result = maxSubarraySum(colSum);
                    int sum = result[0];
                    int startCol_local = result[1];
                    int endCol_local = result[2];

                    if (sum > maxSum) {
                        maxSum = sum;
                        startRow = r1;
                        endRow = r2;
                        startCol = startCol_local;
                        endCol = endCol_local;
                    }
                }
            }

            return maxSum;
        }

        private int[] maxSubarraySum(int[] arr) {
            int maxSum = arr[0];
            int maxStart = 0, maxEnd = 0;
            int currentSum = arr[0];
            int tempStart = 0;

            for (int i = 1; i < arr.length; i++) {
                if (currentSum < 0) {
                    currentSum = arr[i];
                    tempStart = i;
                } else {
                    currentSum += arr[i];
                }

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxStart = tempStart;
                    maxEnd = i;
                }
            }

            return new int[]{maxSum, maxStart, maxEnd};
        }

        int getMaxSum() {
            return maxSum;
        }

        String getRange() {
            return String.format("行: [%d, %d], 列: [%d, %d]",
                startRow + 1, endRow + 1, startCol + 1, endCol + 1);
        }

        void printMatrix() {
            System.out.println("矩阵:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.printf("%4d ", matrix[i][j]);
                }
                System.out.println();
            }
        }

        void printMaxSubmatrix() {
            System. out.println("\n最大子矩阵 (范围: " + getRange() + "):");
            for (int i = startRow; i <= endRow; i++) {
                for (int j = startCol; j <= endCol; j++) {
                    System.out. printf("%4d ", matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== 最大子矩阵和求解器 ==========\n");

        // 内置测试用例
        int[][][] testCases = {
            // 测试1：题目给定的例子
            {
                {0, -2, -7, 0},
                {9, 2, -6, 2},
                {-4, 1, -4, 1},
                {-1, 8, 0, -2}
            },
            // 测试2：都是正数
            {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            },
            // 测试3：都是负数
            {
                {-1, -2},
                {-3, -4}
            },
            // 测试4：混合
            {
                {1, -2, 3},
                {-4, 5, -6},
                {7, -8, 9}
            }
        };

        for (int i = 0; i < testCases.length; i++) {
            int[][] matrix = testCases[i];
            System.out.println("测试" + (i + 1) + ":");

            SubmatrixSolver solver = new SubmatrixSolver(matrix);
            solver.printMatrix();

            long start = System.currentTimeMillis();
            int maxSum = solver.solveOptimized();
            long time = System.currentTimeMillis() - start;

            System.out.println("最大子矩阵和: " + maxSum);
            solver.printMaxSubmatrix();
            System.out.println("求解时间: " + time + " ms");
            System.out.println();
        }

        // 用户输入
        while (true) {
            System. out.println("\n【用户输入】");
            System.out.print("输入矩阵行数 (或输入 0 退出): ");
            int rows = scanner.nextInt();

            if (rows == 0) {
                break;
            }

            System.out. print("输入矩阵列数: ");
            int cols = scanner. nextInt();
            scanner.nextLine();  // 消费换行符

            int[][] matrix = new int[rows][cols];
            System.out.println("输入矩阵元素 (按行输入，用空格分隔):");
            for (int i = 0; i < rows; i++) {
                System.out.print("第" + (i + 1) + "行: ");
                String[] parts = scanner.nextLine().split(" ");
                for (int j = 0; j < cols; j++) {
                    if (j < parts.length) {
                        matrix[i][j] = Integer.parseInt(parts[j]);
                    }
                }
            }

            SubmatrixSolver solver = new SubmatrixSolver(matrix);

            System.out.println("\n原始矩阵:");
            solver.printMatrix();

            System.out.print("\n选择求解方法 (1=暴力O(n^4), 2=优化O(n^3)): ");
            int method = scanner.nextInt();
            scanner. nextLine();

            long start = System.currentTimeMillis();
            int maxSum;
            if (method == 1) {
                if (rows * cols > 100) {
                    System.out.println("警告：矩阵过大，暴力方法可能很慢");
                }
                maxSum = solver.solveBruteForce();
            } else {
                maxSum = solver.solveOptimized();
            }
            long time = System.currentTimeMillis() - start;

            System.out.println("\n最大子矩阵和: " + maxSum);
            solver.printMaxSubmatrix();
            System.out.println("求解时间: " + time + " ms");
        }

        scanner.close();
        System.out.println("\n程序结束");
    }
}