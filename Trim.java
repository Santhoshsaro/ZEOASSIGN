import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpreadsheetJava {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public SpreadsheetJava() {
        frame = new JFrame("Spreadsheet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        tableModel = new DefaultTableModel(new Object[][]{
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
        }, new String[]{"A", "B", "C"});
        
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel toolbar = new JPanel();
        JButton boldButton = new JButton("B");
        JButton italicButton = new JButton("I");
        JButton underlineButton = new JButton("U");
        JButton saveButton = new JButton("Save");
        
        toolbar.add(boldButton);
        toolbar.add(italicButton);
        toolbar.add(underlineButton);
        toolbar.add(saveButton);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (row != -1 && col != -1) {
                    String value = (String) table.getValueAt(row, col);
                    if (value != null) {
                        table.setValueAt(value.trim(), row, col);
                    }
                }
            }
        });
        
        frame.setLayout(new BorderLayout());
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpreadsheetJava::new);
    }
}
