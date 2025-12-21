package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class HRManagementSystem extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;
    private JPopupMenu rightClickMenu;

    public HRManagementSystem(String username) {
        // 设置窗口标题
        setTitle("欢迎"+username+"使用人事管理系统");

        // 设置窗口大小
        setSize(1400, 800);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 设置布局
        setLayout(new BorderLayout(10, 10));

        // 创建顶部面板
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout. NORTH);

        // 创建表格
        createTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // 创建右键菜单
        createRightClickMenu();

        // 初始化示例数据
        initSampleData();

        // 设置窗口可见
        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // 搜索输入框
        searchField = new JTextField(30);
        searchField. setFont(new Font("微软雅黑", Font. PLAIN, 14));
        searchField.setPreferredSize(new Dimension(320, 35));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // 搜索按钮
        searchButton = new JButton("搜索");
        searchButton.setFont(new Font("微软雅黑", Font. PLAIN, 14));
        searchButton.setPreferredSize(new Dimension(90, 35));
        searchButton.setBackground(new Color(220, 220, 220));
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor. HAND_CURSOR));
        searchButton.addActionListener(e -> searchEmployee());

        // 添加按钮
        addButton = new JButton("添加");
        addButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        addButton.setPreferredSize(new Dimension(90, 35));
        addButton.setBackground(new Color(220, 220, 220));
        addButton. setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor. HAND_CURSOR));
        addButton.addActionListener(e -> addEmployee());

        panel.add(searchField);
        panel.add(searchButton);
        panel.add(addButton);

        return panel;
    }

    private void createTable() {
        // 创建表格列名：ID, 姓名, 性别, 年龄, 电话, 职位, 入职时间, 薪水, 部门信息
        String[] columnNames = {"ID", "姓名", "性别", "年龄", "电话", "职位", "入职时间", "薪水", "部门信息"};

        // 创建表格模型
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 禁止直接编辑
            }
        };

        // 创建表格
        employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        employeeTable.setRowHeight(40);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.setShowGrid(true);
        employeeTable.setGridColor(new Color(230, 230, 230));
        employeeTable.setSelectionBackground(new Color(184, 207, 229));
        employeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // 设置表头样式
        JTableHeader header = employeeTable.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.BOLD, 14));
        header.setBackground(new Color(240, 240, 240));
        header.setPreferredSize(new Dimension(header.getWidth(), 45));

        // 设置列宽
        employeeTable. getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
        employeeTable. getColumnModel().getColumn(1).setPreferredWidth(100);  // 姓名
        employeeTable.getColumnModel().getColumn(2).setPreferredWidth(60);   // 性别
        employeeTable.getColumnModel().getColumn(3).setPreferredWidth(60);   // 年龄
        employeeTable.getColumnModel().getColumn(4).setPreferredWidth(130);  // 电话
        employeeTable.getColumnModel().getColumn(5).setPreferredWidth(120);  // 职位
        employeeTable.getColumnModel().getColumn(6).setPreferredWidth(110);  // 入职时间
        employeeTable.getColumnModel().getColumn(7).setPreferredWidth(100);  // 薪水
        employeeTable.getColumnModel().getColumn(8).setPreferredWidth(150);  // 部门信息

        // 设置单元格居中
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 添加鼠标监听器（右键菜单）
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseClick(e);
            }

            private void handleMouseClick(MouseEvent e) {
                if (e.isPopupTrigger()) { // 检测右键点击
                    int row = employeeTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < employeeTable.getRowCount()) {
                        employeeTable.setRowSelectionInterval(row, row);
                        rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });
    }

    private void createRightClickMenu() {
        rightClickMenu = new JPopupMenu();
        rightClickMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));

        // 修改菜单项
        JMenuItem editItem = new JMenuItem("修改");
        editItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        editItem.addActionListener(e -> editEmployee());

        // 删除菜单项
        JMenuItem deleteItem = new JMenuItem("删除");
        deleteItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        deleteItem.addActionListener(e -> deleteEmployee());

        rightClickMenu.add(editItem);
        rightClickMenu. addSeparator();
        rightClickMenu.add(deleteItem);
    }

    private void initSampleData() {
        // 添加示例数据
        String[] genders = {"男", "女"};
        String[] positions = {"Java工程师", "前端工程师", "产品经理", "UI设计师", "测试工程师"};
        String[] departments = {"技术部", "产品部", "设计部", "测试部", "运营部"};

        for (int i = 1; i <= 17; i++) {
            tableModel.addRow(new Object[]{
                i,
                "员工" + i,
                genders[i % 2],
                25 + (i % 10),
                "138" + String.format("%08d", 10000000 + i * 111111),
                positions[i % positions.length],
                "2023-" + String.format("%02d", (i % 12) + 1) + "-01",
                (8000 + i * 500) + "元",
                departments[i % departments.length]
            });
        }
    }

    private void searchEmployee() {
        String keyword = searchField.getText().trim();

        if (keyword. isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "请输入搜索关键词！",
                "提示",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 搜索逻辑
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 1; j < tableModel.getColumnCount(); j++) {
                String value = tableModel.getValueAt(i, j).toString();
                if (value.contains(keyword)) {
                    employeeTable.setRowSelectionInterval(i, i);
                    employeeTable.scrollRectToVisible(employeeTable.getCellRect(i, 0, true));
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        if (!found) {
            JOptionPane.showMessageDialog(this,
                "未找到匹配的员工信息！",
                "搜索结果",
                JOptionPane. INFORMATION_MESSAGE);
        }
    }

    private void addEmployee() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField nameField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"男", "女"});
        JTextField ageField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField hireDateField = new JTextField("2024-01-01");
        JTextField salaryField = new JTextField();
        JTextField departmentField = new JTextField();

        panel.add(new JLabel("姓名: "));
        panel.add(nameField);
        panel.add(new JLabel("性别:"));
        panel.add(genderBox);
        panel.add(new JLabel("年龄: "));
        panel.add(ageField);
        panel.add(new JLabel("电话: "));
        panel.add(phoneField);
        panel.add(new JLabel("职位:"));
        panel.add(positionField);
        panel.add(new JLabel("入职时间:"));
        panel.add(hireDateField);
        panel.add(new JLabel("薪水:"));
        panel.add(salaryField);
        panel.add(new JLabel("部门信息:"));
        panel.add(departmentField);

        int result = JOptionPane. showConfirmDialog(this, panel, "添加员工",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String gender = genderBox.getSelectedItem().toString();
            String age = ageField.getText().trim();
            String phone = phoneField.getText().trim();
            String position = positionField.getText().trim();
            String hireDate = hireDateField.getText().trim();
            String salary = salaryField. getText().trim();
            String department = departmentField.getText().trim();

            if (name.isEmpty() || age.isEmpty() || phone.isEmpty() ||
                position.isEmpty() || salary.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "请填写完整信息！",
                    "提示",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 添加薪水单位
            if (! salary.endsWith("元")) {
                salary += "元";
            }

            int newId = tableModel.getRowCount() + 1;
            tableModel.addRow(new Object[]{newId, name, gender, age, phone, position, hireDate, salary, department});

            JOptionPane.showMessageDialog(this,
                "员工添加成功！",
                "成功",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editEmployee() {
        int selectedRow = employeeTable. getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "请选择要修改的员工！",
                "提示",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 获取当前数据
        String currentName = tableModel.getValueAt(selectedRow, 1).toString();
        String currentGender = tableModel. getValueAt(selectedRow, 2).toString();
        String currentAge = tableModel.getValueAt(selectedRow, 3).toString();
        String currentPhone = tableModel.getValueAt(selectedRow, 4).toString();
        String currentPosition = tableModel.getValueAt(selectedRow, 5).toString();
        String currentHireDate = tableModel.getValueAt(selectedRow, 6).toString();
        String currentSalary = tableModel.getValueAt(selectedRow, 7).toString().replace("元", "");
        String currentDepartment = tableModel.getValueAt(selectedRow, 8).toString();

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory. createEmptyBorder(15, 15, 15, 15));

        JTextField nameField = new JTextField(currentName);
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"男", "女"});
        genderBox.setSelectedItem(currentGender);
        JTextField ageField = new JTextField(currentAge);
        JTextField phoneField = new JTextField(currentPhone);
        JTextField positionField = new JTextField(currentPosition);
        JTextField hireDateField = new JTextField(currentHireDate);
        JTextField salaryField = new JTextField(currentSalary);
        JTextField departmentField = new JTextField(currentDepartment);

        panel.add(new JLabel("姓名:"));
        panel.add(nameField);
        panel.add(new JLabel("性别:"));
        panel.add(genderBox);
        panel.add(new JLabel("年龄:"));
        panel.add(ageField);
        panel.add(new JLabel("电话:"));
        panel.add(phoneField);
        panel.add(new JLabel("职位:"));
        panel.add(positionField);
        panel.add(new JLabel("入职时间:"));
        panel.add(hireDateField);
        panel.add(new JLabel("薪水: "));
        panel.add(salaryField);
        panel.add(new JLabel("部门信息:"));
        panel.add(departmentField);

        int result = JOptionPane. showConfirmDialog(this, panel, "修改员工信息",
            JOptionPane. OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String gender = genderBox. getSelectedItem().toString();
            String age = ageField.getText().trim();
            String phone = phoneField.getText().trim();
            String position = positionField. getText().trim();
            String hireDate = hireDateField.getText().trim();
            String salary = salaryField.getText().trim();
            String department = departmentField.getText().trim();

            if (name.isEmpty() || age.isEmpty() || phone.isEmpty() ||
                position.isEmpty() || salary.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "请填写完整信息！",
                    "提示",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 添加薪水单位
            if (!salary.endsWith("元")) {
                salary += "元";
            }

            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(gender, selectedRow, 2);
            tableModel.setValueAt(age, selectedRow, 3);
            tableModel.setValueAt(phone, selectedRow, 4);
            tableModel.setValueAt(position, selectedRow, 5);
            tableModel.setValueAt(hireDate, selectedRow, 6);
            tableModel.setValueAt(salary, selectedRow, 7);
            tableModel.setValueAt(department, selectedRow, 8);

            JOptionPane.showMessageDialog(this,
                "员工信息修改成功！",
                "成功",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "请选择要删除的员工！",
                "提示",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = tableModel.getValueAt(selectedRow, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
            "确定要删除员工 " + name + " 吗？",
            "确认删除",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);

            // 更新ID
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(i + 1, i, 0);
            }

            JOptionPane.showMessageDialog(this,
                "员工删除成功！",
                "成功",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}