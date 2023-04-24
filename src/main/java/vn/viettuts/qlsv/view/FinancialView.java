package vn.viettuts.qlsv.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import vn.viettuts.qlsv.entity.Financial;
import vn.viettuts.qlsv.dao.FinancialDao;

public class FinancialView extends JFrame implements ActionListener, ListSelectionListener {
    private final FinancialDao financialDao = new FinancialDao();

    private static final long serialVersionUID = 1L;
    private JButton addFinancialBtn;
    private JButton editFinancialBtn;
    private JButton deleteFinancialBtn;
    private JButton clearBtn;
    private JButton sortFinancialAmountBtn;
    private JButton sortFinancialDateBtn;
    private JButton sortFinancialIdBtn;
    private JButton searchFinancialDateBtn;
    private JButton searchFinancialAmountBtn;
    private JTable financialThuTable;
    private JTable financialChiTable;

    private JTextField idField;
    private JTextField dateField;
    private JRadioButton typeThuRadio;
    private JRadioButton typeChiRadio;
    private JRadioButton dateDayRadio;
    private JRadioButton dateMonthRadio;
    private JRadioButton dateYearRadio;
    private ButtonGroup typeRadio;
    private JTextArea detailsTA;
    private JTextField amountField;
    private JTextField balanceField;
    private JTextField totalThuField;
    private JTextField totalChiField;
    private JTextField searchFinancialDateField;
    private JTextField searchFinancialFromAmountField;
    private JTextField searchFinancialToAmountField;

    // định nghĩa các cột của bảng financial
    private final String [] columnNames = new String [] {
            "ID", "Date", "Type", "Details", "Amount (VND)"};
    // định nghĩa dữ liệu mặc định của bẳng financial là rỗng
    private final Object dataThu = new Object [][] {};
    private final Object dataChi = new Object [][] {};
    
    public FinancialView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addFinancialBtn = new JButton("Add");
        editFinancialBtn = new JButton("Edit");
        deleteFinancialBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortFinancialAmountBtn = new JButton("Sort By Amount");
        sortFinancialDateBtn = new JButton("Sort By Date");
        sortFinancialIdBtn = new JButton("Sort By ID");
        searchFinancialDateBtn=new JButton("Search By Date");
        searchFinancialAmountBtn =new JButton("Search By Amount");

        // khởi tạo bảng financial
        JScrollPane jScrollPaneFinancialThuTable = new JScrollPane();
        financialThuTable = new JTable();
        JScrollPane jScrollPaneFinancialChiTable = new JScrollPane();
        financialChiTable = new JTable();
        
        // khởi tạo các label
        JLabel idLabel = new JLabel("Id");
        JLabel thuLabel = new JLabel("Receive");
        JLabel chiLabel = new JLabel("Spend");
        JLabel dateLabel = new JLabel("Date");
        JLabel typeLabel = new JLabel("Type");
        JLabel detailsLabel = new JLabel("Details");
        JLabel amountLabel = new JLabel("Amount (VND)");
        JLabel balanceLabel = new JLabel("Available Balance (VND)");
        JLabel fromAmountLabel = new JLabel("From:");
        JLabel toAmountLabel = new JLabel("To:");
        JLabel totalThuLabel = new JLabel("Total Receive (VND)");
        JLabel totalChiLabel = new JLabel("Total Spend (VND)");

        // khởi tạo Radio Button
        dateDayRadio = new JRadioButton("day");
        dateMonthRadio = new JRadioButton("month");
        dateYearRadio = new JRadioButton("year");
        add(dateDayRadio);
        add(dateMonthRadio);
        add(dateYearRadio);
        typeThuRadio = new JRadioButton("Thu");
        typeChiRadio = new JRadioButton("Chi");
        typeRadio = new ButtonGroup();
        typeRadio.add(typeThuRadio);
        typeRadio.add(typeChiRadio);
        add(typeThuRadio);
        add(typeChiRadio);
        
        // khởi tạo các trường nhập dữ liệu cho financial
        idField = new JTextField(6);
        idField.setEditable(false);
        dateField = new JTextField(17);
        detailsTA = new JTextArea();
        detailsTA.setColumns(17);
        detailsTA.setRows(3);
        JScrollPane jScrollPaneDetails = new JScrollPane();
        jScrollPaneDetails.setViewportView(detailsTA);
        amountField = new JTextField(17);
        balanceField = new JTextField(8);
        balanceField.setEditable(false);
        totalThuField = new JTextField(8);
        totalThuField.setEditable(false);
        totalChiField = new JTextField(8);
        totalChiField.setEditable(false);
        searchFinancialDateField = new JTextField(7);
        searchFinancialFromAmountField = new JTextField(7);
        searchFinancialToAmountField = new JTextField(7);
        
        // cài đặt các cột và data cho bảng financial
        financialThuTable.setModel(new DefaultTableModel((Object[][]) dataThu, columnNames));
        jScrollPaneFinancialThuTable.setViewportView(financialThuTable);
        jScrollPaneFinancialThuTable.setPreferredSize(new Dimension (480, 370));
        financialChiTable.setModel(new DefaultTableModel((Object[][]) dataChi, columnNames));
        jScrollPaneFinancialChiTable.setViewportView(financialChiTable);
        jScrollPaneFinancialChiTable.setPreferredSize(new Dimension (480, 370));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Financial
        JPanel panel = new JPanel();
        panel.setSize(1500, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneFinancialThuTable);
        panel.add(jScrollPaneFinancialChiTable);
        
        panel.add(addFinancialBtn);
        panel.add(editFinancialBtn);
        panel.add(deleteFinancialBtn);
        panel.add(clearBtn);
        panel.add(sortFinancialAmountBtn);
        panel.add(sortFinancialDateBtn);
        panel.add(sortFinancialIdBtn);
        panel.add(searchFinancialDateBtn);
        panel.add(searchFinancialAmountBtn);

        panel.add(idLabel);
        panel.add(thuLabel);
        panel.add(chiLabel);
        panel.add(dateLabel);
        panel.add(typeLabel);
        panel.add(detailsLabel);
        panel.add(amountLabel);
        panel.add(balanceLabel);
        panel.add(fromAmountLabel);
        panel.add(toAmountLabel);
        panel.add(totalThuLabel);
        panel.add(totalChiLabel);
        
        panel.add(idField);
        panel.add(dateField);
        panel.add(typeThuRadio);
        panel.add(typeChiRadio);
        panel.add(dateDayRadio);
        panel.add(dateMonthRadio);
        panel.add(dateYearRadio);
        panel.add(jScrollPaneDetails);
        panel.add(amountField);
        panel.add(balanceField);
        panel.add(totalThuField);
        panel.add(totalChiField);
        panel.add(searchFinancialDateField);
        panel.add(searchFinancialFromAmountField);
        panel.add(searchFinancialToAmountField);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, thuLabel, 520, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, thuLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, chiLabel, 1020, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, chiLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeLabel, 110, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, detailsLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, detailsLabel, 155, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, amountLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, amountLabel, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, balanceLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, balanceLabel, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, fromAmountLabel, 175, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fromAmountLabel, 385, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, toAmountLabel, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, toAmountLabel, 415, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, totalThuLabel, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, totalThuLabel, 420, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, totalChiLabel, 800, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, totalChiLabel, 420, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateField, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateField, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeThuRadio, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeThuRadio, 110, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeChiRadio, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeChiRadio, 110, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateDayRadio, 65, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateDayRadio, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateMonthRadio, 65, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateMonthRadio, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateYearRadio, 65, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateYearRadio, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneDetails, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneDetails, 155, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, amountField, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, amountField, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, balanceField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, balanceField, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, totalThuField, 440, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, totalThuField, 420, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, totalChiField, 940, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, totalChiField, 420, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialDateField, 220, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialDateField, 345, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialFromAmountField, 220, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialFromAmountField, 385, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialToAmountField, 220, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialToAmountField, 415, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneFinancialThuTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneFinancialThuTable, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneFinancialChiTable, 800, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneFinancialChiTable, 40, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addFinancialBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addFinancialBtn, 285, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editFinancialBtn, 60, SpringLayout.WEST, addFinancialBtn);
        layout.putConstraint(SpringLayout.NORTH, editFinancialBtn, 285, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteFinancialBtn, 60, SpringLayout.WEST, editFinancialBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteFinancialBtn, 285, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialDateBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialDateBtn, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialAmountBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialAmountBtn, 395, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 285, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteFinancialBtn);
        
        layout.putConstraint(SpringLayout.WEST, sortFinancialAmountBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortFinancialAmountBtn, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortFinancialDateBtn, 150, SpringLayout.WEST, sortFinancialAmountBtn);
        layout.putConstraint(SpringLayout.NORTH, sortFinancialDateBtn, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortFinancialIdBtn, 130, SpringLayout.WEST, sortFinancialDateBtn);
        layout.putConstraint(SpringLayout.NORTH, sortFinancialIdBtn, 450, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Financial Management");
        this.setSize(1300, 530);
        // disable Edit and Delete buttons
        editFinancialBtn.setEnabled(false);
        deleteFinancialBtn.setEnabled(false);
        // enable Add button
        addFinancialBtn.setEnabled(true);
        // show available balance
        int balance=0, thu=0, chi=0;
        List<Financial> list = financialDao.getListFinancials();
        for (Financial financial : list) {
            if (financial.getType().equals("Thu")) {
                balance += financial.getAmount();
                thu+=financial.getAmount();
            } else {
                balance -= financial.getAmount();
                chi+=financial.getAmount();
            }
        }
        this.showAvailableBalance(balance);
        this.showTotalThuBalance(thu);
        this.showTotalChiBalance(chi);
    }

    public void showAvailableBalance(int balance){
        balanceField.setText(""+balance);
    }

    public void showTotalThuBalance(int thu){
        totalThuField.setText(""+thu);
    }

    public void showTotalChiBalance(int chi){
        totalChiField.setText(""+chi);
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    /**
     * hiển thị list financial vào bảng financialThuTable
     */
    public void showListFinancials(List<Financial> list) {
        // với bảng financialThuTable có 5 cột,
        // khởi tạo mảng 2 chiều financials, trong đó:
        // số hàng: là kích thước của list financial chia làm 2 phần thu và chi
        // số cột: là 5
        int countThu=0, countChi=0, balance=0, thu=0, chi=0;
        for (Financial financial : list) {
            if (financial.getType().equals("Thu")) {
                thu+=financial.getAmount();
                balance+=financial.getAmount();
                countThu++;
            } else {
                chi+=financial.getAmount();
                balance-=financial.getAmount();
                countChi++;
            }
        }
        showAvailableBalance(balance);
        showTotalChiBalance(chi);
        showTotalThuBalance(thu);
        Object [][] financialsThu = new Object[countThu][5];
        Object [][] financialsChi = new Object[countChi][5];
        countThu=0;countChi=0;
        for (Financial financial : list) {
            if (financial.getType().equals("Thu")) {
                financialsThu[countThu][0] = financial.getId();
                financialsThu[countThu][1] = financial.getDate().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                financialsThu[countThu][2] = financial.getType();
                financialsThu[countThu][3] = financial.getDetails();
                financialsThu[countThu][4] = financial.getAmount();
                countThu++;
            } else {
                financialsChi[countChi][0] = financial.getId();
                financialsChi[countChi][1] = financial.getDate().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                financialsChi[countChi][2] = financial.getType();
                financialsChi[countChi][3] = financial.getDetails();
                financialsChi[countChi][4] = financial.getAmount();
                countChi++;
            }
        }
        financialThuTable.setModel(new DefaultTableModel(financialsThu, columnNames));
        financialThuTable.getColumnModel().getColumn(0).setMaxWidth(30);
        financialThuTable.getColumnModel().getColumn(1).setMaxWidth(70);
        financialThuTable.getColumnModel().getColumn(2).setMaxWidth(40);
        financialThuTable.getColumnModel().getColumn(3).setMaxWidth(240);
        financialThuTable.getColumnModel().getColumn(4).setMaxWidth(100);
        financialChiTable.setModel(new DefaultTableModel(financialsChi, columnNames));
        financialChiTable.getColumnModel().getColumn(0).setMaxWidth(30);
        financialChiTable.getColumnModel().getColumn(1).setMaxWidth(70);
        financialChiTable.getColumnModel().getColumn(2).setMaxWidth(40);
        financialChiTable.getColumnModel().getColumn(3).setMaxWidth(240);
        financialChiTable.getColumnModel().getColumn(4).setMaxWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < 3; i++) {
            financialThuTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            financialThuTable.getColumnModel().getColumn(i).setResizable(false);
            financialChiTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            financialChiTable.getColumnModel().getColumn(i).setResizable(false);
        }
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        financialThuTable.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
        financialChiTable.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng financial 
     * vào các trường tương ứng của financial.
     */
    public void fillFinancialFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = financialThuTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(financialThuTable.getModel().getValueAt(row, 0).toString());
            dateField.setText(financialThuTable.getModel().getValueAt(row, 1).toString());
            if (financialThuTable.getModel().getValueAt(row, 2).toString().equals("Thu")){
                typeThuRadio.doClick();
            } else typeChiRadio.doClick();
            detailsTA.setText(financialThuTable.getModel().getValueAt(row, 3).toString());
            amountField.setText(financialThuTable.getModel().getValueAt(row, 4).toString());
            // enable Edit and Delete buttons
            editFinancialBtn.setEnabled(true);
            deleteFinancialBtn.setEnabled(true);
            // disable Add button
            addFinancialBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin financial
     */
    public void clearFinancialInfo() {
        idField.setText("");
        dateField.setText("");
        typeRadio.clearSelection();
        detailsTA.setText("");
        amountField.setText("");
        searchFinancialDateField.setText("");
        searchFinancialFromAmountField.setText("");
        searchFinancialToAmountField.setText("");
        // disable Edit and Delete buttons
        editFinancialBtn.setEnabled(false);
        deleteFinancialBtn.setEnabled(false);
        // enable Add button
        addFinancialBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin financial
     */
    public void showFinancial(Financial financial) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        idField.setText("" + financial.getId());
        dateField.setText(financial.getDate().format(formatter));
        if (financial.getType().equals("Thu")){
            typeThuRadio.doClick();
        } else typeChiRadio.doClick();
        detailsTA.setText(financial.getDetails());
        amountField.setText("" + financial.getAmount());
        // enable Edit and Delete buttons
        editFinancialBtn.setEnabled(true);
        deleteFinancialBtn.setEnabled(true);
        // disable Add button
        addFinancialBtn.setEnabled(false);
    }
    
    /**
     * lấy thông tin financial
     */
    public Financial getFinancialInfo() {
        // validate financial
        if (!validateDate() || !validateType() || !validateDetails() || !validateAmount()) {
            return null;
        }
        try {
            Financial financial = new Financial();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                financial.setId(Integer.parseInt(idField.getText()));
            }
            String[] date = dateField.getText().split("/");
            int day=Integer.parseInt(date[0]);
            int month=Integer.parseInt(date[1]);
            int year=Integer.parseInt(date[2]);
            financial.setDate(LocalDate.of(year, month, day));
            if(typeThuRadio.isSelected()) financial.setType("Thu");
            else financial.setType("Chi");
            financial.setDetails(detailsTA.getText().trim());
            financial.setAmount(Integer.parseInt(amountField.getText().trim()));
            return financial;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public int[] getSearchDate(){
        if (searchFinancialDateField.getText()==null) return null;
        String[] date = searchFinancialDateField.getText().split("/");
        int[] int_date = new int[date.length];
        try {
            for (int i = 0; i < date.length; i++) {
                int_date[i] = Integer.parseInt(date[i]);
            }
            return int_date;
        } catch (Exception e){
            return new int[0];
        }
    }

    public int getSearchMinAmount(){
        if (searchFinancialFromAmountField.getText().equals("")) return -999;
        else return Integer.parseInt(searchFinancialFromAmountField.getText());
    }

    public int getSearchMaxAmount(){
        if (searchFinancialToAmountField.getText().equals("")) return -999;
        else return Integer.parseInt(searchFinancialToAmountField.getText());
    }

    private boolean validateDetails() {
        return true;
    }

    private boolean validateDate() {
        try {
            String date = dateField.getText();
            if (date == null || "".equals(date.trim())) {
                dateField.requestFocus();
                showMessage("Date is empty!");
                return false;
            }
            String[] split_date = date.split("/");
            if (split_date.length!=3) {
                showMessage("Date must be day/month/year");
                return false;
            }
        } catch (Exception e) {
            dateField.requestFocus();
            showMessage("Invalid Date, Date must be day/month/year!");
            return false;
        }
        return true;
    }
    
    private boolean validateType() {
        if (!typeThuRadio.isSelected() && !typeChiRadio.isSelected()) {
            showMessage("Must select Type!");
            return false;
        }
        return true;
    }
    
    private boolean validateAmount() {
        try {
            int amount = Integer.parseInt(amountField.getText().trim());
            if (amount < 0) {
                amountField.requestFocus();
                showMessage("Invalid Amount, Amount should be greater than or equal to 0");
                return false;
            }
        } catch (Exception e) {
            amountField.requestFocus();
            showMessage("Invalid Amount");
            return false;
        }
        return true;
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddFinancialListener(ActionListener listener) {
        addFinancialBtn.addActionListener(listener);
    }
    
    public void addEditFinancialListener(ActionListener listener) {
        editFinancialBtn.addActionListener(listener);
    }
    
    public void addDeleteFinancialListener(ActionListener listener) {
        deleteFinancialBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortFinancialAmountListener(ActionListener listener) {
        sortFinancialAmountBtn.addActionListener(listener);
    }
    
    public void addSortFinancialDateListener(ActionListener listener) {
        sortFinancialDateBtn.addActionListener(listener);
    }

    public void addSortFinancialIdListener(ActionListener listener) {
        sortFinancialIdBtn.addActionListener(listener);
    }

    public void addSearchFinancialDateListener(ActionListener listener) {
        searchFinancialDateBtn.addActionListener(listener);
    }

    public void addSearchFinancialAmountListener(ActionListener listener) {
        searchFinancialAmountBtn.addActionListener(listener);
    }
    
    public void addListFinancialSelectionListener(ListSelectionListener listener) {
        financialThuTable.getSelectionModel().addListSelectionListener(listener);
    }
}
