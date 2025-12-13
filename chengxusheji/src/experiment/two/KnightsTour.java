package experiment.two;

import java.util.ArrayList;
import java.util.List;

public class KnightsTour {

    private int boardSize;
    private int[][] board;
    private int[][] moves = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
    private int moveCount;

    public KnightsTour(int boardSize) {
        this.boardSize = boardSize;
        this. board = new int[boardSize][boardSize];
        this.moveCount = 0;

        // 初始化棋盘，-1表示未访问
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = -1;
            }
        }
    }

    public boolean solve(int startRow, int startCol) {
        if (! isValid(startRow, startCol)) {
            System.out.println("起始位置无效!");
            return false;
        }

        board[startRow][startCol] = 0;
        moveCount = 1;

        if (backtrack(startRow, startCol)) {
            return true;
        } else {
            // 重置棋盘
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    board[i][j] = -1;
                }
            }
            return false;
        }
    }

    private boolean backtrack(int row, int col) {
        if (moveCount == boardSize * boardSize) {
            return true;
        }

        // 获取所有有效的下一步移动，按可访问相邻节点数排序
        List<int[]> nextMoves = getNextMovesByHeuristic(row, col);

        for (int[] move : nextMoves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            board[nextRow][nextCol] = moveCount++;

            if (backtrack(nextRow, nextCol)) {
                return true;
            }

            board[nextRow][nextCol] = -1;
            moveCount--;
        }

        return false;
    }

    private List<int[]> getNextMovesByHeuristic(int row, int col) {
        List<int[]> validMoves = new ArrayList<>();

        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isValid(nextRow, nextCol) && board[nextRow][nextCol] == -1) {
                validMoves.add(move);
            }
        }

        // 按可访问相邻节点数排序（Warnsdorff启发式）
        validMoves.sort((a, b) -> {
            int countA = countAccessibleNeighbors(row + a[0], col + a[1]);
            int countB = countAccessibleNeighbors(row + b[0], col + b[1]);
            return Integer.compare(countA, countB);
        });

        return validMoves;
    }

    private int countAccessibleNeighbors(int row, int col) {
        int count = 0;
        for (int[] move : moves) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];
            if (isValid(nextRow, nextCol) && board[nextRow][nextCol] == -1) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.printf("%3d ", board[i][j]);
            }
            System.out.println();
        }
    }

    public String getPath() {
        StringBuilder path = new StringBuilder();
        int[][] positions = new int[boardSize * boardSize][2];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                positions[board[i][j]][0] = i;
                positions[board[i][j]][1] = j;
            }
        }

        for (int i = 0; i < positions.length; i++) {
            path.append(String.format("(%d,%d)", positions[i][0], positions[i][1]));
            if (i < positions.length - 1) {
                path.append(" -> ");
            }
        }

        return path.toString();
    }

    public static void main(String[] args) {
        System.out.println("========== 骑士游历问题求解器 ==========\n");

        int boardSize = 8;
        int startRow = 0;
        int startCol = 0;

        System.out.printf("求解 %d×%d 国际象棋棋盘上的骑士游历问题\n", boardSize, boardSize);
        System.out.printf("起始位置: (%d, %d)\n\n", startRow, startCol);

        long start = System.currentTimeMillis();
        KnightsTour tour = new KnightsTour(boardSize);

        if (tour.solve(startRow, startCol)) {
            long time = System.currentTimeMillis() - start;
            System.out.println("求解时间: " + time + " ms\n");

            System.out.println("棋盘（数字表示访问顺序）:");
            tour. printBoard();

            System.out.println("\n路径序列:");
            String path = tour.getPath();
            System.out.println(path. length() > 200 ?
                path.substring(0, 200) + "..." : path);
        } else {
            long time = System.currentTimeMillis() - start;
            System. out.println("✗ 求解失败!");
            System.out.println("求解时间: " + time + " ms");
        }

        // 测试不同起始位置
        System.out.println("\n【不同起始位置测试】");
        int[][] startPositions = {{0, 0}, {0, 7}, {7, 0}, {7, 7}, {3, 3}};

        for (int[] pos : startPositions) {
            start = System.currentTimeMillis();
            KnightsTour tour2 = new KnightsTour(boardSize);
            boolean result = tour2.solve(pos[0], pos[1]);
            long time = System.currentTimeMillis() - start;
            System.out.printf("起始(%d,%d): %s (时间: %d ms)\n",
                pos[0], pos[1], result ? "成功" : "失败", time);
        }
    }
}