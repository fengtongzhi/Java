package base01.enum_demo;

public class Test2 {
    public static void main(String[] args) {

        //使用常量类中的常量来表示方向,但参数没有约束
        move(Constant.DOWN);

        //使用枚举类中的枚举常量来表示方向,参数有约束
        move2(Direction.LEFT);

    }

    public static void move(int direction){
        switch (direction){
            case Constant.UP:
                System.out.println("向上移动");
                break;
            case Constant.DOWN:
                System.out.println("向下移动");
                break;
            case Constant.LEFT:
                System.out.println("向左移动");
                break;
            case Constant.RIGHT:
                System.out.println("向右移动");
                break;
            default:
                System.out.println("未知方向");
        }
    }

    public static void move2(Direction direction){
        switch (direction){
            case Direction.UP:
                System.out.println("向上移动");
                break;
            case Direction.DOWN:
                System.out.println("向下移动");
                break;
            case Direction.LEFT:
                System.out.println("向左移动");
                break;
            case Direction.RIGHT:
                System.out.println("向右移动");
                break;
            default:
                System.out.println("未知方向");
        }
    }
}
