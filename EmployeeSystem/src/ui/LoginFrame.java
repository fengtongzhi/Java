package ui;

import bean.Employee;
import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private static ArrayList<User> allUser = new ArrayList<>();

    static{
        allUser.add(new User("管理员","admin","admin"));
        allUser.add(new User("张三","123456","zhangsan"));
        allUser.add(new User("李四","123456","lisi"));
    }

    public LoginFrame() {
        // 设置窗口标题
        setTitle("登录界面");

        // 设置窗口大小
        setSize(720, 720);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 设置布局为null，使用绝对定位
        setLayout(null);

        // 设置背景色为浅灰色
        getContentPane().setBackground(new Color(240, 240, 240));

        // 创建标题标签
        JLabel titleLabel = new JLabel("黑马程序员人事管理系统");
        titleLabel.setFont(new Font("微软雅黑", Font. BOLD, 32));
        titleLabel.setBounds(180, 100, 450, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // 创建用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("微软雅黑", Font. PLAIN, 18));
        usernameLabel.setBounds(110, 240, 90, 40);
        add(usernameLabel);

        // 创建用户名输入框
        usernameField = new JTextField();
        usernameField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        usernameField. setBounds(310, 240, 340, 40);
        add(usernameField);

        // 创建密码标签
        JLabel passwordLabel = new JLabel("密    码:");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passwordLabel.setBounds(110, 330, 90, 40);
        add(passwordLabel);

        // 创建密码输入框
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        passwordField.setBounds(310, 330, 340, 40);
        add(passwordField);

        // 创建登录按钮
        loginButton = new JButton("登    录");
        loginButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        loginButton.setBounds(110, 420, 270, 50);
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        add(loginButton);

        // 创建注册按钮
        registerButton = new JButton("注    册");
        registerButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        registerButton.setBounds(380, 420, 270, 50);
        registerButton.setBackground(new Color(200, 200, 200));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        add(registerButton);

        // 添加登录按钮事件监听
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                        "用户名或密码不能为空！",
                        "提示",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    // 遍历登录用户列表，验证用户名和密码是否正确，如果正确，跳转到用户管理界面
                    boolean found = false;
                    for (User user : allUser) {
                        if (user.getLoginName().equals(username) && user.getPassword().equals(password)) {
                            found = true;
                            JOptionPane.showMessageDialog(LoginFrame.this,
                                "登录成功！\n欢迎，" + user.getLoginName(),
                                "登录",
                                JOptionPane.INFORMATION_MESSAGE);
                            // 关闭登录界面
                            dispose();
                            // 打开员工管理界面
                            new HRManagementSystem(username);
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(LoginFrame.this,
                            "用户名或密码错误！",
                            "提示",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // 添加注册按钮事件监听
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击注册按钮后弹出提示框，提示注册成功，事件发生时获取输入框的LoginName和password并且将他们添加到allUser中，username可以默认设置为LoginName
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                        "用户名或密码不能为空！",
                        "提示",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    //检查用户名是否已经存在
                    boolean exists = false;
                    for (User user : allUser) {
                        if (user.getLoginName().equals(username)) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        JOptionPane.showMessageDialog(LoginFrame.this,
                            "用户名已存在！",
                            "提示",
                            JOptionPane.ERROR_MESSAGE);
                    } else {
                        allUser.add(new User(username, password, username));
                        JOptionPane.showMessageDialog(LoginFrame.this,
                            "注册成功！\n请使用新账号登录。",
                            "注册",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // 设置窗口可见
        setVisible(true);
    }
}