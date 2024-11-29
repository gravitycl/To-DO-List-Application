import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ToDoListApp {
    private JFrame frame;
    private JTable taskTable;
    private JTextField taskInputField;
    private JButton addButton, removeButton, clearButton;
    private DefaultTableModel tableModel;


    private DefaultTableModel tableDataModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ToDoListApp window = new ToDoListApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ToDoListApp() {
  
        frame = new JFrame("To-Do List");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());
        frame.setContentPane(gradientPanel);

        tableDataModel = new DefaultTableModel(new Object[]{"Task", "Status"}, 0);
        taskTable = new JTable(tableDataModel);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskTable.setFont(new Font("Arial", Font.PLAIN, 16));
        taskTable.setBackground(new Color(255, 255, 255));
        taskTable.setForeground(new Color(0, 0, 0));

        taskTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"Not Completed", "Completed"})));

        JScrollPane scrollPane = new JScrollPane(taskTable);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 10)); 
        panel.setBackground(new Color(240, 240, 240));
        gradientPanel.add(panel, BorderLayout.SOUTH);

        taskInputField = new JTextField(20);
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        taskInputField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        panel.add(taskInputField);

        addButton = new JButton("Add Task");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(34, 193, 195)); 
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(120, 40));
        addButton.addActionListener(e -> addTask());
        panel.add(addButton);


        removeButton = new JButton("Remove Task");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
        removeButton.setBackground(new Color(239, 83, 80)); 
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.setPreferredSize(new Dimension(120, 40));
        removeButton.addActionListener(e -> removeTask());
        panel.add(removeButton);

    
        clearButton = new JButton("Clear List");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(33, 150, 243));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(120, 40));
        clearButton.addActionListener(e -> clearList());
        panel.add(clearButton);

        addButton.setToolTipText("Click to add a task");
        removeButton.setToolTipText("Click to remove selected task");
        clearButton.setToolTipText("Click to clear all tasks");
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            Color color1 = new Color(255, 165, 0); 
            Color color2 = new Color(34, 193, 195); 
            GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void addTask() {
        String task = taskInputField.getText().trim();
        if (!task.isEmpty()) {
            
            tableDataModel.addRow(new Object[]{task, "Not Completed"});
            taskInputField.setText("");
        }
    }

    private void removeTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow != -1) {
            tableDataModel.removeRow(selectedRow);
        }
    }

    private void clearList() {
        tableDataModel.setRowCount(0); 
    }
}
