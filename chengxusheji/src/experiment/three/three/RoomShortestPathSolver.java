package experiment.three.three;

import java.util.*;

/**
 * 房间最短路径求解器 - 完整版
 * 给定一个内含阻碍墙的房间，求解从起点到终点的最短路径
 * 房间边界: x=[0,10], y=[0,10]
 * 起点: (0,5), 终点: (10,5)
 * 墙的个数: 0-18个，每个墙有两个门
 */
public class RoomShortestPathSolver {

    private static final double EPS = 1e-9;
    private static final double ROOM_MIN_X = 0;
    private static final double ROOM_MAX_X = 10;
    private static final double ROOM_MIN_Y = 0;
    private static final double ROOM_MAX_Y = 10;

    /**
     * 点类
     */
    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Point(Point p) {
            this. x = p.x;
            this.y = p.y;
        }

        double distance(Point other) {
            return Math.sqrt((this.x - other.x) * (this. x - other.x) +
                           (this.y - other.y) * (this. y - other.y));
        }

        boolean equals(Point p) {
            return Math.abs(x - p.x) < EPS && Math.abs(y - p.y) < EPS;
        }

        @Override
        public String toString() {
            return String.format("(%.2f, %.2f)", x, y);
        }
    }

    /**
     * 墙类
     */
    static class Wall {
        double x;      // 墙的x位置
        double y1, y2; // 两个门的y坐标范围

        Wall(double x, double y1, double y2) {
            this.x = x;
            this.y1 = Math.min(y1, y2);
            this.y2 = Math.max(y1, y2);
        }

        /**
         * 判断水平直线能否通过这堵墙
         * 从(x, fromY)到(x, toY)的竖直线段
         */
        boolean canPassThrough(double fromY, double toY) {
            double minY = Math.min(fromY, toY);
            double maxY = Math.max(fromY, toY);

            // 如果线段完全在门的范围内或完全在门外，就可以通过
            return (maxY <= this.y1 + EPS) || (minY >= this.y2 - EPS);
        }

        @Override
        public String toString() {
            return String.format("Wall at x=%.2f, gaps: [%.2f, %.2f]", x, y1, y2);
        }
    }

    /**
     * 图的边类
     */
    static class Edge implements Comparable<Edge> {
        int to;
        double weight;

        Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }
    }

    private Point start;
    private Point end;
    private List<Wall> walls;
    private List<Point> keyPoints;

    RoomShortestPathSolver(Point start, Point end, List<Wall> walls) {
        this.start = start;
        this.end = end;
        this.walls = walls;
        this.keyPoints = new ArrayList<>();
        initializeKeyPoints();
    }

    /**
     * 初始化关键点：起点、终点、所有门的位置
     */
    private void initializeKeyPoints() {
        keyPoints.clear();
        keyPoints.add(start);
        keyPoints.add(end);

        // 添加每堵墙的两个门的位置
        for (Wall wall : walls) {
            keyPoints.add(new Point(wall.x, wall. y1));
            keyPoints. add(new Point(wall.x, wall.y2));
        }
    }

    /**
     * 检查从p1到p2的直线段是否能通过所有的墙
     */
    private boolean canPassBetween(Point p1, Point p2) {
        // 如果两点x坐标相同（竖直线）
        if (Math.abs(p1.x - p2.x) < EPS) {
            double x = p1.x;
            double minY = Math.min(p1.y, p2.y);
            double maxY = Math. max(p1.y, p2.y);

            // 检查是否在房间边界内
            if (x < ROOM_MIN_X - EPS || x > ROOM_MAX_X + EPS ||
                minY < ROOM_MIN_Y - EPS || maxY > ROOM_MAX_Y + EPS) {
                return false;
            }

            // 竖直线段穿过墙没有问题（墙是竖直的）
            return true;
        }

        // 如果两点y坐标相同（水平线）
        if (Math.abs(p1.y - p2.y) < EPS) {
            double y = p1. y;
            double minX = Math.min(p1.x, p2.x);
            double maxX = Math.max(p1.x, p2. x);

            // 检查是否在房间边界内
            if (y < ROOM_MIN_Y - EPS || y > ROOM_MAX_Y + EPS ||
                minX < ROOM_MIN_X - EPS || maxX > ROOM_MAX_X + EPS) {
                return false;
            }

            // 对于水平线段，检查穿过的每堵墙
            for (Wall wall : walls) {
                if (wall.x > minX + EPS && wall.x < maxX - EPS) {
                    // 这堵墙在路径的中间
                    if (! wall.canPassThrough(y, y)) {
                        return false;
                    }
                }
            }

            return true;
        }

        // 斜线情况：检查线段是否穿过任何墙
        double minX = Math.min(p1. x, p2.x);
        double maxX = Math.max(p1.x, p2.x);
        double minY = Math.min(p1.y, p2.y);
        double maxY = Math.max(p1.y, p2.y);

        // 检查是否在房间边界内
        if (minX < ROOM_MIN_X - EPS || maxX > ROOM_MAX_X + EPS ||
            minY < ROOM_MIN_Y - EPS || maxY > ROOM_MAX_Y + EPS) {
            return false;
        }

        for (Wall wall : walls) {
            if (wall.x > minX + EPS && wall.x < maxX - EPS) {
                // 计算线段在x=wall.x处的y坐标
                double y = p1.y + (p2.y - p1.y) * (wall.x - p1.x) / (p2.x - p1.x);

                // 检查这个点是否可以通过墙
                if (! wall.canPassThrough(y, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 使用Dijkstra算法求最短路径
     */
    double findShortestPath() {
        int n = keyPoints.size();

        // 初始化距离数组
        double[] dist = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Double.MAX_VALUE);

        // 找到起点的索引
        int startIdx = -1;
        for (int i = 0; i < n; i++) {
            if (keyPoints.get(i).equals(start)) {
                startIdx = i;
                break;
            }
        }

        dist[startIdx] = 0;

        // Dijkstra算法
        for (int i = 0; i < n; i++) {
            // 找未访问的最小距离节点
            int u = -1;
            double minDist = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    u = j;
                }
            }

            if (u == -1 || dist[u] == Double.MAX_VALUE) {
                break;
            }
            visited[u] = true;

            // 更新相邻节点
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    Point from = keyPoints.get(u);
                    Point to = keyPoints.get(v);

                    if (canPassBetween(from, to)) {
                        double edgeDist = from.distance(to);
                        if (dist[u] + edgeDist < dist[v]) {
                            dist[v] = dist[u] + edgeDist;
                        }
                    }
                }
            }
        }

        // 找到终点的索引
        int endIdx = -1;
        for (int i = 0; i < n; i++) {
            if (keyPoints.get(i).equals(end)) {
                endIdx = i;
                break;
            }
        }

        return dist[endIdx];
    }

    /**
     * 打印房间信息
     */
    void printRoomInfo() {
        System.out.println("房间边界: x=[0, 10], y=[0, 10]");
        System.out.println("起点: " + start);
        System.out.println("终点: " + end);
        System.out.println("墙的个数: " + walls.size());
        for (int i = 0; i < walls.size(); i++) {
            System.out.println("  墙" + (i + 1) + ": " + walls.get(i));
        }
        System.out.println("关键点个数: " + keyPoints.size());
    }

    /**
     * 主程序
     */
    public static void main(String[] args) {
        System.out.println("========== 房间最短路径求解 ==========\n");

        // 测试用例1：无墙
        System.out.println("【测试1】无墙的房间");
        Point start = new Point(0, 5);
        Point end = new Point(10, 5);
        List<Wall> walls = new ArrayList<>();
        RoomShortestPathSolver solver1 = new RoomShortestPathSolver(start, end, walls);
        solver1.printRoomInfo();
        double dist1 = solver1.findShortestPath();
        System. out.println("最短路径长度: " + String.format("%.4f", dist1) + " (直线距离)\n");

        // 测试用例2：一堵墙，有缝隙
        System.out.println("【测试2】一堵墙在x=5，缝隙在y=0-4");
        walls = new ArrayList<>();
        walls.add(new Wall(5, 0, 4));
        RoomShortestPathSolver solver2 = new RoomShortestPathSolver(start, end, walls);
        solver2. printRoomInfo();
        double dist2 = solver2.findShortestPath();
        System.out.println("最短路径长度: " + String. format("%.4f", dist2) + "\n");

        // 测试用例3：一堵墙，缝隙在上方
        System.out.println("【测试3】一堵墙在x=5，缝隙在y=6-10");
        walls = new ArrayList<>();
        walls.add(new Wall(5, 6, 10));
        RoomShortestPathSolver solver3 = new RoomShortestPathSolver(start, end, walls);
        solver3.printRoomInfo();
        double dist3 = solver3.findShortestPath();
        System.out.println("最短路径长度: " + String.format("%.4f", dist3) + "\n");

        // 测试用例4：多堵墙
        System.out.println("【测试4】多堵墙的复杂情况");
        walls = new ArrayList<>();
        walls. add(new Wall(3, 2, 8));
        walls.add(new Wall(5, 0, 4));
        walls. add(new Wall(7, 6, 10));
        RoomShortestPathSolver solver4 = new RoomShortestPathSolver(start, end, walls);
        solver4. printRoomInfo();
        double dist4 = solver4.findShortestPath();
        System.out.println("最短路径长度: " + String. format("%.4f", dist4) + "\n");

        // 交互式输入
        System.out.println("【交互式输入】");
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 5;

        while (true) {
            System.out. print("\n输入墙的个数 (0-18, -1退出): ");
            int wallCount = scanner.nextInt();

            if (wallCount == -1) {
                break;
            }

            if (wallCount < 0 || wallCount > 18) {
                System.out.println("错误：墙的个数应该在0-18之间");
                continue;
            }

            walls = new ArrayList<>();
            for (int i = 0; i < wallCount; i++) {
                System.out.print("墙" + (i + 1) + " - 输入x坐标和两个y坐标 (x y1 y2): ");
                double x = scanner.nextDouble();
                double y1 = scanner. nextDouble();
                double y2 = scanner.nextDouble();
                walls.add(new Wall(x, y1, y2));
            }

            RoomShortestPathSolver solver = new RoomShortestPathSolver(start, end, walls);
            System.out.println("\n========== 情形 " + caseNumber + " ==========");
            solver.printRoomInfo();
            double distance = solver.findShortestPath();
            System.out.println("最短路径长度: " + String.format("%.4f", distance));

            caseNumber++;
        }

        scanner.close();
        System.out.println("\n程序结束");
    }
}