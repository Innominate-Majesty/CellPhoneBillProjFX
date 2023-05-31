package CellPhoneBillPackage;

//Venus Ho
//1048875
//CIS 084
//Java Programming
//May 28, 2023

//Assignment: Create a JavaFX program which has 4 options for a Cell Phone Plan
//Used JavaSwing instead because JavaFX did not work with IDE

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

public class CellPhoneBill extends JFrame{

    //top pane
    JLabel lblTitle;

    //left pane
    JTextArea lblPlansAndPrices;

    //center pane
    JTextField txtName;
    JTextField txtPlan;
    JTextField txtGBused;

    //right pane
    JLabel lblConsumerName;
    JLabel lblTotalBill;


    //constants for plans
    final double PRICE_PER_GB = 15.00;
    final double PLAN_A_PRICE = 50.00;
    final double PLAN_B_PRICE = 60.00;
    final double PLAN_B_GB = 2;
    final double PLAN_C_PRICE = 70.00;
    final double PLAN_C_GB = 4;
    final double PLAN_D_PRICE = 90.00;
    final double Plan_D_GB = 10;


    //constructor
    public CellPhoneBill() {
        
        setTitle("Cell Phone Bill by Venus");

        setLayout(new BorderLayout());

        add(createTop(), BorderLayout.NORTH);
        add(createLeft(), BorderLayout.WEST);
        add(createCenter(), BorderLayout.CENTER);
        add(createRight(), BorderLayout.EAST);
        add(createBottom(), BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private JPanel createTop() { 

        JPanel panel = new JPanel();
        lblTitle = new JLabel("Cell Phone Billing");
        lblTitle.setFont(new Font("Monospaced", Font.PLAIN, 24));
        panel.add(lblTitle);
        return panel;

    }

    private JPanel createLeft() {

        JPanel panel = new JPanel();
        lblPlansAndPrices = new JTextArea( "Available Plans and Prices \n" +
        "--------------------------\n" +
        "Plan A: 0 GB $50.00\n" +
        "Plan B: 2 GB $60.00\n" +
        "Plan C: 4 GB $70.00\n" +
        "Plan D: 10 GB $90.00\n" +
        "+$15.00/GB over plan limit");
        lblPlansAndPrices.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPlansAndPrices.setSize(150,100);
        lblPlansAndPrices.setEditable(false);
        panel.add(lblPlansAndPrices);
        return panel;

    }

    private JPanel createCenter() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        txtName = new JTextField();
        txtPlan = new JTextField();
        txtGBused = new JTextField();

        Dimension minSize = new Dimension(100, txtName.getPreferredSize().height);
        Dimension prefSize = new Dimension (200, txtName.getPreferredSize().height);
        txtName.setMinimumSize(minSize);
        txtName.setPreferredSize(prefSize);
        txtPlan.setMinimumSize(minSize);
        txtPlan.setPreferredSize(prefSize);
        txtGBused.setMinimumSize(minSize);
        txtGBused.setPreferredSize(prefSize);


        panel.add(new JLabel("Name: "));
        panel.add(txtName);
        panel.add(new JLabel("Plan: "));
        panel.add(txtPlan);
        panel.add(new JLabel("GB Used: "));
        panel.add(txtGBused);

        return panel;
    }

    private JPanel createRight() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblCustomerBill = new JLabel("Customer Bill");
        lblCustomerBill.setFont(new Font("Monospaced", Font.PLAIN, 12));

        lblConsumerName = new JLabel("Name: ");
        lblConsumerName.setFont(new Font("Arial", Font.PLAIN, 12));

        lblTotalBill = new JLabel("Please pay: ");
        lblTotalBill.setFont(new Font("Arial", Font.PLAIN, 12));

        //add spacing
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        panel.add(lblCustomerBill);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(lblConsumerName);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(lblTotalBill);

        return panel;
        
    }

    private JPanel createBottom() {

        JPanel panel = new JPanel();

        JButton btnCompute = new JButton("Compute");
        btnCompute.addActionListener(e -> compute());

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clear());

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(btnCompute);
        panel.add(btnClear);
        panel.add(btnExit);

        return panel;

    }

    private void compute() {

        try { //try, throw, and catch invalid inputs

            String name = txtName.getText();
            String plan = txtPlan.getText().toUpperCase();
            double gbUsed = Double.parseDouble(txtGBused.getText());
            double bill = 0;

            if(name.isEmpty()) throw new Exception("Name must be filled");
            if(plan.isEmpty()) throw new Exception("Plan must be filled");
            if (plan.matches("[^ABCD]")) throw new Exception("Invalid plan. Please pick A, B, C, or D");

            switch(plan) { //plans

                case "A":
                    bill = PLAN_A_PRICE;
                    if(gbUsed > 0) bill += gbUsed * PRICE_PER_GB;
                    break;

                case "B":
                    bill = PLAN_B_PRICE ;
                    if(gbUsed > 2) bill += (gbUsed - PLAN_B_GB) * PRICE_PER_GB;
                    break;

                case "C":
                    bill = PLAN_C_PRICE;
                    if(gbUsed > 4) bill += (gbUsed - PLAN_C_GB) * PRICE_PER_GB;
                    break;

                case "D":
                    bill = PLAN_D_PRICE;
                    if(gbUsed > 10) bill += (gbUsed - Plan_D_GB) * PRICE_PER_GB;
                    break;

                default:
                    throw new Exception("Plan entered is invalid. Please pick A, B, C, or D");

        }

        lblConsumerName.setText("Name: " + name);
        lblTotalBill.setText("Please pay $" + String.format("%.2f", bill));

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Invalid number of GB used. Please enter a positive integer");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void clear() {

        txtName.setText("");
        txtPlan.setText("");
        txtGBused.setText("");
        lblConsumerName.setText("Name: ");
        lblTotalBill.setText("Please pay:");

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
        CellPhoneBill frame = new CellPhoneBill();
        frame.setSize(900,800);
        frame.pack();
        frame.setVisible(true); 
        });

    }


}

