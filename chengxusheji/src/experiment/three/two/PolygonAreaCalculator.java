package experiment.three.two;

import java.util.*;

/**
 * 多边形面积计算器
 * 判断给定顶点是否能构成有效的多边形
 * 如果有效，计算其面积（使用Shoelace公式）
 * 如果无效（自相交或顶点不足），输出 "Impossible"
 */
public class PolygonAreaCalculator {

    private static final double EPS = 1e-9;

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
            this.x = p.x;
            this.y = p.y;
        }

        boolean equals(Point p) {
            return Math. abs(x - p.x) < EPS && Math.abs(y - p.y) < EPS;
        }

        @Override
        public String toString() {
            return String.format("(%.2f, %.2f)", x, y);
        }
    }

    /**
     * 线段类
     */
    static class LineSegment {
        Point p1, p2;

        LineSegment(Point p1, Point p2) {
            this.p1 = new Point(p1);
            this.p2 = new Point(p2);
        }

        /**
         * 计算叉积：(p1-o) × (p2-o)
         */
        static double crossProduct(Point o, Point a, Point b) {
            return (a.x - o.x) * (b.y - o. y) - (a.y - o.y) * (b. x - o.x);
        }

        /**
         * 计算点积：(a-o) · (b-o)
         */
        static double dotProduct(Point o, Point a, Point b) {
            return (a.x - o.x) * (b. x - o.x) + (a.y - o.y) * (b.y - o. y);
        }

        /**
         * 判断点是否在线段上（包括端点）
         */
        boolean pointOnSegment(Point p) {
            // 检查点是否在直线上
            if (Math.abs(crossProduct(p1, p2, p)) > EPS) {
                return false;
            }

            // 检查点是否在线段的投影范围内
            double dotProd = dotProduct(p1, p, p2);
            double len2 = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);

            return dotProd >= -EPS && dotProd <= len2 + EPS;
        }

        /**
         * 判断两个点是否相等
         */
        static boolean pointsEqual(Point a, Point b) {
            return Math.abs(a.x - b.x) < EPS && Math.abs(a.y - b.y) < EPS;
        }

        /**
         * 判断两条线段是否相交
         * 处理端点重合、端点在线段上等特殊情况
         */
        boolean intersectsWith(LineSegment other) {
            Point a = this.p1;
            Point b = this.p2;
            Point c = other.p1;
            Point d = other.p2;

            double d1 = crossProduct(a, b, c);
            double d2 = crossProduct(a, b, d);
            double d3 = crossProduct(c, d, a);
            double d4 = crossProduct(c, d, b);

            // 情况1：标准相交（中间相交）
            if (((d1 > EPS && d2 < -EPS) || (d1 < -EPS && d2 > EPS)) &&
                ((d3 > EPS && d4 < -EPS) || (d3 < -EPS && d4 > EPS))) {
                return true;
            }

            // 情况2和3：端点重合或端点在线段上
            if (pointsEqual(c, a) || pointsEqual(c, b)) {
                return true;
            }
            if (pointsEqual(d, a) || pointsEqual(d, b)) {
                return true;
            }

            if (Math.abs(d1) < EPS && pointOnSegment(c)) {
                return true;
            }
            if (Math.abs(d2) < EPS && pointOnSegment(d)) {
                return true;
            }
            if (Math.abs(d3) < EPS && other.pointOnSegment(a)) {
                return true;
            }
            if (Math. abs(d4) < EPS && other.pointOnSegment(b)) {
                return true;
            }

            return false;
        }

        /**
         * 判断两条线段是否严格相交（仅中间相交，不在端点）
         */
        boolean strictlyIntersects(LineSegment other) {
            Point a = this.p1;
            Point b = this.p2;
            Point c = other.p1;
            Point d = other.p2;

            double d1 = crossProduct(a, b, c);
            double d2 = crossProduct(a, b, d);
            double d3 = crossProduct(c, d, a);
            double d4 = crossProduct(c, d, b);

            return ((d1 > EPS && d2 < -EPS) || (d1 < -EPS && d2 > EPS)) &&
                   ((d3 > EPS && d4 < -EPS) || (d3 < -EPS && d4 > EPS));
        }

        @Override
        public String toString() {
            return String.format("[%s -> %s]", p1, p2);
        }
    }

    /**
     * 多边形类
     */
    static class Polygon {
        private List<Point> vertices;

        Polygon() {
            this.vertices = new ArrayList<>();
        }

        void addVertex(Point p) {
            vertices.add(new Point(p));
        }

        void addVertex(double x, double y) {
            vertices.add(new Point(x, y));
        }

        int getVertexCount() {
            return vertices.size();
        }

        Point getVertex(int i) {
            return vertices.get(i);
        }

        /**
         * 检查多边形是否有效
         * 条件：
         * 1.  至少有3个顶点
         * 2. 没有自相交的边（除了相邻边共享的端点）
         */
        boolean isValid() {
            // 检查顶点数
            if (vertices.size() < 3) {
                return false;
            }

            // 检查线段相交
            for (int i = 0; i < vertices.size(); i++) {
                LineSegment seg1 = new LineSegment(
                    vertices.get(i),
                    vertices.get((i + 1) % vertices.size())
                );

                // 与不相邻的边进行比较
                for (int j = i + 2; j < vertices.size(); j++) {
                    // 避免比较最后一条边和第一条边（它们在末尾相邻）
                    if (i == 0 && j == vertices.size() - 1) {
                        continue;
                    }

                    LineSegment seg2 = new LineSegment(
                        vertices. get(j),
                        vertices.get((j + 1) % vertices. size())
                    );

                    // 检查是否严格相交（不包括端点接触）
                    if (seg1. strictlyIntersects(seg2)) {
                        return false;
                    }
                }
            }

            return true;
        }

        /**
         * 使用Shoelace公式计算多边形面积
         * 公式：Area = |Σ(x[i] * y[i+1] - x[i+1] * y[i])| / 2
         */
        double calculateArea() {
            if (! isValid()) {
                return -1;  // 无效的多边形
            }

            double area = 0.0;
            int n = vertices.size();

            for (int i = 0; i < n; i++) {
                Point current = vertices.get(i);
                Point next = vertices.get((i + 1) % n);
                area += current.x * next.y;
                area -= next.x * current.y;
            }

            return Math.abs(area) / 2.0;
        }

        void print() {
            System.out. println("Polygon with " + vertices.size() + " vertices:");
            for (int i = 0; i < vertices.size(); i++) {
                System.out.println("  V" + (i + 1) + ": " + vertices.get(i));
            }
        }
    }

    /**
     * 格式化面积输出
     */
    static String formatArea(double area) {
        return String.format("%.2f", area);
    }

    /**
     * 处理多边形并输出结果
     */
    static void processPolygon(List<Point> points, int figureNumber, boolean printBlankLine) {
        if (printBlankLine) {
            System.out.println();
        }

        Polygon polygon = new Polygon();
        for (Point p : points) {
            polygon.addVertex(p);
        }

        System.out.print("Figure " + figureNumber + ": ");

        if (! polygon.isValid()) {
            System.out.println("Impossible");
        } else {
            double area = polygon.calculateArea();
            System.out.println(formatArea(area));
        }
    }

    /**
     * 主函数 - 处理输入和输出
     */
    public static void main(String[] args) {
        System.out.println("========== 多边形面积计算器 ==========\n");
        System.out.println("【预设测试用例】\n");

        // 预设测试用例1：凹多边形（有效）
        System.out. println("测试1：凹多边形");
        List<Point> test1 = new ArrayList<>();
        test1.add(new Point(0, 0));
        test1.add(new Point(0, 1));
        test1.add(new Point(0.5, 0.5));
        test1.add(new Point(1, 1));
        test1.add(new Point(1, 0));
        processPolygon(test1, 1, false);

        // 预设测试用例2：自相交四边形（无效）
        System.out.println("\n测试2：自相交四边形");
        List<Point> test2 = new ArrayList<>();
        test2.add(new Point(0, 0));
        test2.add(new Point(0, 1));
        test2.add(new Point(1, 0));
        test2. add(new Point(1, 1));
        processPolygon(test2, 2, false);

        // 预设测试用例3：正方形（有效）
        System.out. println("\n测试3：正方形");
        List<Point> test3 = new ArrayList<>();
        test3.add(new Point(0, 0));
        test3.add(new Point(1, 0));
        test3.add(new Point(1, 1));
        test3.add(new Point(0, 1));
        processPolygon(test3, 3, false);

        // 预设测试用例4：直角三角形（有效）
        System.out.println("\n测试4：直角三角形");
        List<Point> test4 = new ArrayList<>();
        test4.add(new Point(0, 0));
        test4.add(new Point(3, 0));
        test4.add(new Point(0, 4));
        processPolygon(test4, 4, false);

        // 预设测试用例5：五边形（有效）
        System.out.println("\n测试5：五边形");
        List<Point> test5 = new ArrayList<>();
        test5.add(new Point(0, 0));
        test5. add(new Point(2, 0));
        test5.add(new Point(3, 2));
        test5.add(new Point(1, 3));
        test5.add(new Point(-1, 2));
        processPolygon(test5, 5, false);

        // 预设测试用例6：顶点数不足（无效）
        System.out.println("\n测试6：顶点数不足（2个顶点）");
        List<Point> test6 = new ArrayList<>();
        test6.add(new Point(0, 0));
        test6.add(new Point(1, 1));
        processPolygon(test6, 6, false);

        // 预设测试用例7：复杂凹多边形（有效）
        System.out. println("\n测试7：复杂凹多边形");
        List<Point> test7 = new ArrayList<>();
        test7.add(new Point(0, 0));
        test7.add(new Point(2, 0));
        test7.add(new Point(2, 1));
        test7.add(new Point(1, 1));
        test7.add(new Point(1, 2));
        test7.add(new Point(0, 2));
        processPolygon(test7, 7, false);

        System.out.println("\n\n【用户输入模式】\n");
        System.out.println("现在进入交互式输入模式");
        System.out.println("输入0退出程序\n");

        Scanner scanner = new Scanner(System.in);
        int figureNumber = 8;

        while (true) {
            System.out. print("输入顶点个数 (输入0退出): ");
            int n = scanner.nextInt();

            // 0表示输入结束
            if (n == 0) {
                break;
            }

            if (n < 0) {
                System.out.println("顶点个数不能为负数，请重新输入\n");
                continue;
            }

            // 创建多边形
            List<Point> points = new ArrayList<>();
            System.out.println("输入顶点坐标 (x y):");
            for (int i = 0; i < n; i++) {
                System.out.print("第" + (i + 1) + "个顶点: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                points.add(new Point(x, y));
            }

            // 处理多边形
            processPolygon(points, figureNumber, true);
            figureNumber++;
            System.out.println();
        }

        scanner.close();
        System.out.println("\n程序结束");
    }
}