package game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private final static String PATH="stone_maze/src/image/";

    //准备数组，存储数字图片4行4列
    private int[][] imageData = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //规定胜利时的数组状态
    private  int[][] winData = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //定位空白色块的位置
    private int Row;
    private int Col;
    private int count;

    public MainFrame() {
        initFrame();
        //打乱顺序，在展示图片
        initRandomImage();
        //初始化界面，展示数字色块
        initImage();
        //初始化系统菜单
        initMenu();
        //给当前窗口绑定上下左右按键事件
        initKeyPressEvent();

        this.setVisible(true);
    }

    private void initKeyPressEvent() {
        //给当前窗口绑定上下左右按键事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        switchAndMove(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        switchAndMove(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        switchAndMove(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        switchAndMove(Direction.RIGHT);
                        break;
                }
            }
        });
    }

    private void switchAndMove(Direction direction) {
        switch (direction) {
            case UP:
                //上交换的条件必须是行<3，然后才开始交换
                if(Row<imageData.length-1) {
                    //和下面的数字交换
                    imageData[Row][Col] = imageData[Row + 1][Col];
                    imageData[Row + 1][Col] = 0;
                    //更新空白色块的位置
                    Row = Row + 1;
                    count++;
                }
                break;
            case DOWN:
                //下交换的条件必须是行>0，然后才开始交换
                if(Row>0) {
                    //和上面的数字交换
                    imageData[Row][Col] = imageData[Row - 1][Col];
                    imageData[Row - 1][Col] = 0;
                    //更新空白色块的位置
                    Row = Row - 1;
                    count++;
                }
                break;
            case LEFT:
                //左交换的条件必须是列<3，然后才开始交换
                if(Col<imageData[0].length-1) {
                    //和右边的数字交换
                    imageData[Row][Col] = imageData[Row][Col + 1];
                    imageData[Row][Col + 1] = 0;
                    //更新空白色块的位置
                    Col = Col + 1;
                    count++;
                }
                break;
            case RIGHT:
                //右交换的条件必须是列>0，然后才开始交换
                if(Col>0) {
                    //和左边的数字交换
                    imageData[Row][Col] = imageData[Row][Col - 1];
                    imageData[Row][Col - 1] = 0;
                    //更新空白色块的位置
                    Col = Col - 1;
                    count++;
                }
                break;
        }

        //更新图层
        initImage();
    }

    private void initRandomImage() {
        //打乱数组顺序
        int image_len=imageData.length;
        for (int i = 0; i < image_len; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                //生成随机行列下标
                int r1 = (int) (Math.random() * imageData.length);
                int c1 = (int) (Math.random() * imageData[i].length);
                int r2 = (int) (Math.random() * imageData.length);
                int c2 = (int) (Math.random() * imageData[i].length);
                //交换两个位置的数字
                int temp = imageData[r1][c1];
                imageData[r1][c1] = imageData[r2][c2];
                imageData[r2][c2] = temp;
            }
        }

        OUT:
        //遍历数组，找到0的位置
        for (int i = 0; i < image_len; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                if (imageData[i][j] == 0) {
                    Row = i;
                    Col = j;
                    break OUT;
                }
            }
        }
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu functionMenu = new JMenu("功能");
        //重启游戏
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        reLoginItem.addActionListener(e -> {
            count=0;
            initRandomImage();
            initImage();
        });
        //关闭系统
        JMenuItem closeItem = new JMenuItem("关闭系统");
        closeItem.addActionListener(e -> {
            dispose();
        });

        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);

        menuBar.add(functionMenu);
        this.setJMenuBar(menuBar);
    }

    private void initImage() {
        //先清空窗口上的全部图层
        this.getContentPane().removeAll();

        //展示移动次数
        JLabel stepLabel=new JLabel("步数："+count);
        stepLabel.setBounds(0,0,100,20);
        this.add(stepLabel);

        //判断是否胜利，展示胜利图片
        if(isWin()){
            JLabel label=new JLabel(new ImageIcon(PATH+"win.png"));
            label.setBounds(124,230,266,88);
            this.add(label);
        }

        //展示一个行列矩阵的图片色块依次铺满窗口(4*4)
        int image_len=imageData.length;
        for(int i = 0; i < image_len; i++) {
            for(int j = 0; j < imageData[i].length; j++) {
                //获取数字
                int num = imageData[i][j];
                //创建标签对象
                JLabel label = new JLabel();
                //创建图片对象
                ImageIcon icon = new ImageIcon(PATH + num + ".png");
                label.setIcon(icon);
                //设置标签位置
                label.setBounds(20+j * 100, 60+i * 100, 100, 100);
                //添加到窗口中
                this.add(label);
            }
        }

        //设置窗口的背景图片
        JLabel background = new JLabel(new ImageIcon(PATH + "background.png"));
        background.setBounds(0, 0, 450, 484);
        this.add(background);

        //刷新新图层
        this.repaint();
    }

    private boolean isWin() {
        int image_len=imageData.length;
        for(int i = 0; i < image_len; i++) {
            for(int j = 0; j < imageData[i].length; j++) {
                if(imageData[i][j]!=winData[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private void initFrame() {
        this.setTitle("石子迷宫 v1.0");
        this.setSize(465, 575);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }
}
