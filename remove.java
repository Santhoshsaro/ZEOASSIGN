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
        
        JPanel toolbar = new JPanel();
        JButton boldButton = new JButton("B");
        JButton italicButton = new JButton("I");
        JButton underlineButton = new JButton("U");
        JButton saveButton = new JButton("Save");
        JButton addRowButton = new JButton("+");
        JButton deleteRowButton = new JButton("-");
        
        boldButton.addActionListener(e -> applyTextStyle(Font.BOLD));
        italicButton.addActionListener(e -> applyTextStyle(Font.ITALIC));
        underlineButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Underline not supported"));
        saveButton.addActionListener(e -> saveData());
        addRowButton.addActionListener(e -> addRow());
        deleteRowButton.addActionListener(e -> deleteRow());
        
        toolbar.add(boldButton);
        toolbar.add(italicButton);
        toolbar.add(underlineButton);
        toolbar.add(saveButton);
        toolbar.add(addRowButton);
        toolbar.add(deleteRowButton);
        
        JPanel formulaPanel = new JPanel(new BorderLayout());
        formulaField = new JTextField();
        formulaPanel.add(new JLabel("Formula: "), BorderLayout.WEST);
        formulaPanel.add(formulaField, BorderLayout.CENTER);
        
        frame.setLayout(new BorderLayout());
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(formulaPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
    
    private void applyTextStyle(int style) {
        Font currentFont = table.getFont();
        table.setFont(new Font(currentFont.getFontName(), style, currentFont.getSize()));
    }
    
    private void saveData() {
        JOptionPane.showMessageDialo