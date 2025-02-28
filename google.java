import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpreadsheetApp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField formulaField;
    
    public SpreadsheetApp() {
        frame = new JFrame("Spreadsheet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        tableModel = new DefaultTableModel(10, 5); // 10 rows, 5 columns
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel formulaPanel = new JPanel(new BorderLayout());
        formulaField = new JTextField();
        formulaPanel.add(new JLabel("Formula: "), BorderLayout.WEST);
        formulaPanel.add(formulaField, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton sumButton = new JButton("SUM");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSum();
            }
        });
        
        buttonPanel.add(sumButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        
        frame.setLayout(new BorderLayout());
        frame.add(formulaPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
    
    private void calculateSum() {
        int sum = 0;
        int[] selectedRows = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();
        
        for (int row : selectedRows) {
            for (int col : selectedColumns) {
                Object value = table.getValueAt(row, col);
                if (value instanceof Number) {
                    sum += ((Number) value).intValue();
                } else {
                    try {
                        sum += Integer.parseInt(value.toString());
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        formulaField.setText("SUM: " + sum);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpreadsheetApp::new);
    }
}
