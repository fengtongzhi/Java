package experiment.two;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    private int n;
    private int[] queens;          // queens[i] 表示第 i 行皇后在第几列
    private boolean[] cols;        // 用于标记列被占用
    private boolean[] diag1;       // 用于标记主对角线被占用
    private boolean[] diag2;       // 用于标记副对角线被占用
    private List<List<String>> solutions;
    private int solutionCount;

    public NQueens(int n) {
        this.n = n;
        this.queens = new int[n];
        this.cols = new boolean[n];
        this.diag1 = new boolean[2 * n - 1];
        this.diag2 = new boolean[2 * n - 1];
        this.solutions = new ArrayList<>();
        this.solutionCount = 0;
    }

    public List<List<String>> solve() {
        solutions.clear();
        solutionCount = 0;
        backtrack(0);
        return solutions;
    }


    public int countSolutions() {
        solutionCount = 0;
        backtrack(0);
        return solutionCount;
    }


    private void backtrack(int row) {
        if (row == n) {
            solutionCount++;
            if (solutions.size() < 100) {  // 只保存前100个解，防止内存溢出
                solutions.add(buildSolution());
            }
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                placeQueen(row, col);
                backtrack(row + 1);
                removeQueen(row, col);
            }
        }
    }


    private boolean isValid(int row, int col) {
        // 检查列
        if (cols[col]) return false;

        // 检查主对角线 (row - col 为常数)
        int diag1Index = row - col + (n - 1);
        if (diag1[diag1Index]) return false;

        // 检查副对角线 (row + col 为常数)
        int diag2Index = row + col;
        if (diag2[diag2Index]) return false;

        return true;
    }

    private void placeQueen(int row, int col) {
        queens[row] = col;
        cols[col] = true;
        diag1[row - col + (n - 1)] = true;
        diag2[row + col] = true;
    }

    private void removeQueen(int row, int col) {
        queens[row] = -1;
        cols[col] = false;
        diag1[row - col + (n - 1)] = false;
        diag2[row + col] = false;
    }

    private List<String> buildSolution() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(queens[i] == j ? 'Q' : '.');
            }
            solution. add(row.toString());
        }
        return solution;
    }

    public void printBoard(List<String> solution) {
        for (String row : solution) {
            System.out.println(row);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("========== N皇后问题求解器 ==========\n");

        // 测试8皇后
        System.out.println("【8皇后问题】");
        long start = System.currentTimeMillis();
        NQueens nQueens8 = new NQueens(8);
        int count8 = nQueens8. countSolutions();
        long time8 = System.currentTimeMillis() - start;
        System.out.println("解的个数: " + count8);
        System.out.println("求解时间: " + time8 + " ms\n");


        // 测试16皇后
        System.out.println("\n【16皇后问题】");
        start = System.currentTimeMillis();
        NQueens nQueens16 = new NQueens(16);
        int count16 = nQueens16. countSolutions();
        long time16 = System.currentTimeMillis() - start;
        System.out.println("解的个数: " + count16);
        System.out.println("求解时间: " + time16 + " ms\n");

        // 测试更大规模（可选）
        System.out.println("【性能测试】");
        for (int n : new int[]{10, 12, 14}) {
            start = System.currentTimeMillis();
            NQueens nQueens = new NQueens(n);
            int count = nQueens.countSolutions();
            long time = System.currentTimeMillis() - start;
            System. out.printf("N=%2d: 解数=%6d, 时间=%6d ms\n", n, count, time);
        }
    }
}
