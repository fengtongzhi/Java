package experiment.two;

import java.util.*;

public class WaterJugProblem {

    static class State {
        int jug1;
        int jug2;

        State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return jug1 == state.jug1 && jug2 == state.jug2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(jug1, jug2);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", jug1, jug2);
        }
    }

    static class Node {
        State state;
        Node parent;
        String operation;

        Node(State state, Node parent, String operation) {
            this.state = state;
            this.parent = parent;
            this.operation = operation;
        }
    }

    private int capacity1;
    private int capacity2;
    private int target;

    public WaterJugProblem(int capacity1, int capacity2, int target) {
        this.capacity1 = capacity1;
        this.capacity2 = capacity2;
        this. target = target;
    }

    public List<String> solve() {
        // 检查是否有解
        if (target > capacity1 && target > capacity2) {
            return null;  // 无解
        }

        if (target % gcd(capacity1, capacity2) != 0) {
            return null;  // 无解（根据数论）
        }

        Queue<Node> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State initial = new State(0, 0);
        queue. offer(new Node(initial, null, "初始状态"));
        visited.add(initial);

        while (! queue.isEmpty()) {
            Node current = queue.poll();

            // 检查是否达到目标
            if (current.state.jug1 == target || current.state.jug2 == target ||
                current.state.jug1 + current.state.jug2 == target) {
                return buildPath(current);
            }

            // 生成所有可能的下一步状态
            List<Node> nextNodes = generateNextStates(current);

            for (Node next : nextNodes) {
                if (!visited.contains(next.state)) {
                    visited.add(next.state);
                    queue.offer(next);
                }
            }
        }

        return null;  // 无解
    }

    private List<Node> generateNextStates(Node current) {
        List<Node> nextStates = new ArrayList<>();
        int j1 = current.state.jug1;
        int j2 = current.state.jug2;

        // 1. 倒空第一个容器
        if (j1 > 0) {
            nextStates.add(new Node(
                new State(0, j2),
                current,
                String.format("将容器1倒空: (%d,%d) -> (0,%d)", j1, j2, j2)
            ));
        }

        // 2. 倒空第二个容器
        if (j2 > 0) {
            nextStates.add(new Node(
                new State(j1, 0),
                current,
                String.format("将容器2倒空: (%d,%d) -> (%d,0)", j1, j2, j1)
            ));
        }

        // 3.  装满第一个容器
        if (j1 < capacity1) {
            nextStates.add(new Node(
                new State(capacity1, j2),
                current,
                String.format("装满容器1: (%d,%d) -> (%d,%d)", j1, j2, capacity1, j2)
            ));
        }

        // 4. 装满第二个容器
        if (j2 < capacity2) {
            nextStates.add(new Node(
                new State(j1, capacity2),
                current,
                String.format("装满容器2: (%d,%d) -> (%d,%d)", j1, j2, j1, capacity2)
            ));
        }

        // 5. 从容器1倒入容器2
        if (j1 > 0 && j2 < capacity2) {
            int pour = Math.min(j1, capacity2 - j2);
            nextStates.add(new Node(
                new State(j1 - pour, j2 + pour),
                current,
                String.format("从容器1倒入容器2: (%d,%d) -> (%d,%d)", j1, j2, j1 - pour, j2 + pour)
            ));
        }

        // 6. 从容器2倒入容器1
        if (j2 > 0 && j1 < capacity1) {
            int pour = Math.min(j2, capacity1 - j1);
            nextStates.add(new Node(
                new State(j1 + pour, j2 - pour),
                current,
                String.format("从容器2倒入容器1: (%d,%d) -> (%d,%d)", j1, j2, j1 + pour, j2 - pour)
            ));
        }

        return nextStates;
    }

    private List<String> buildPath(Node node) {
        List<String> path = new ArrayList<>();

        while (node != null) {
            path.add(0, node.operation);
            node = node.parent;
        }

        return path;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        // 经典问题：3升和5升瓶子装4升水
        System.out. println("【测试1】: 用3升和5升的瓶子装4升水");
        testWaterJug(3, 5, 4);

        // 测试4：无解的情况
        System.out. println("\n【测试2】: 用3升和5升的瓶子装1升水（无解）");
        testWaterJug(3, 5, 1);

        // 测试5：更大的容器
        System.out.println("\n【测试3】: 用7升和11升的瓶子装6升水");
        testWaterJug(7, 11, 6);
    }

    private static void testWaterJug(int cap1, int cap2, int target) {
        long start = System.currentTimeMillis();
        WaterJugProblem problem = new WaterJugProblem(cap1, cap2, target);
        List<String> solution = problem.solve();
        long time = System.currentTimeMillis() - start;

        if (solution != null) {
            System.out.println("求解时间: " + time + " ms\n");
            System.out. println("求解步骤:");
            for (int i = 0; i < solution.size(); i++) {
                System.out.printf("步骤%d: %s\n", i, solution.get(i));
            }
        } else {
            System.out.println("✗ No Solution (求解时间: " + time + " ms)");
        }
    }
}