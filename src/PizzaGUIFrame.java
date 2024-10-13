import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIFrame extends JFrame{
    JPanel main;
    JPanel crust;
    JPanel size;
    JPanel toppings;
    JPanel receipt;
    JPanel controlPnl;
    JComboBox comboBox;
    JRadioButton sml;
    JRadioButton med;
    JRadioButton large;
    JRadioButton sup;
    JCheckBox topping1;
    JCheckBox topping2;
    JCheckBox topping3;
    JCheckBox topping4;
    JCheckBox topping5;
    JCheckBox topping6;
    JTextArea receiptText;
    JButton printReceipt;
    JButton clear;
    JButton quit;
    private static double priceOne = 0;
    private static double priceTwo = 0;

    public PizzaGUIFrame(){
        main = new JPanel();
        main.setLayout(new BorderLayout());

        JLabel title = new JLabel("Pizza Orderer", JLabel.CENTER);
        title.setFont((new Font("Impact", Font.PLAIN, 36)));
        main.add(title, BorderLayout.NORTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        JPanel leftAndRight = new JPanel();
        leftAndRight.setLayout(new BoxLayout(leftAndRight, BoxLayout.X_AXIS));

        createCrust();
        leftAndRight.add(crust, JComboBox.CENTER_ALIGNMENT);

        createSizeSelection();
        leftAndRight.add(size);

        middlePanel.add(leftAndRight);

        createToppings();
        middlePanel.add(toppings);

        createReceiptArea();
        middlePanel.add(receipt);

        main.add(middlePanel, BorderLayout.CENTER);

        controlPanel();
        main.add(controlPnl, BorderLayout.SOUTH);

        add(main);
        setSize(650,800);
        setLocation(0,0);
        setTitle("Pizza Orderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createCrust(){
        crust = new JPanel();
        crust.setBorder(new TitledBorder(new EtchedBorder(),"Select Crust Type:"));

        comboBox = new JComboBox();
        comboBox.addItem("Thin");
        comboBox.addItem("Regular");
        comboBox.addItem("Deep-dish");
        comboBox.setSelectedItem("Regular");

        crust.add(comboBox);
    }
    private void createSizeSelection(){
        size = new JPanel();
        size.setLayout(new GridLayout(1,4));
        size.setBorder(new TitledBorder(new EtchedBorder(),"Select Size"));

        sml = new JRadioButton("Small");
        med = new JRadioButton("Medium");
        large = new JRadioButton("Large");
        sup = new JRadioButton("Super");

        size.add(sml);
        size.add(med);
        size.add(large);
        size.add(sup);

        med.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(sml);
        group.add(med);
        group.add(large);
        group.add(sup);
    }
    private void createToppings(){
        toppings = new JPanel();
        toppings.setLayout(new GridLayout(2,3));
        toppings.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));

        topping1 = new JCheckBox("Orange Peels");
        topping2 = new JCheckBox("Erasers");
        topping3 = new JCheckBox("Legos");
        topping4 = new JCheckBox("Tranquilizers");
        topping5 = new JCheckBox("Bat-teeth");
        topping6 = new JCheckBox("Used-tissues");

        toppings.add(topping1);
        toppings.add(topping2);
        toppings.add(topping3);
        toppings.add(topping4);
        toppings.add(topping5);
        toppings.add(topping6);
    }
    private void createReceiptArea(){
        receipt = new JPanel();
        receipt.setBorder(new TitledBorder(new EtchedBorder(), "Receipt"));

        receiptText = new JTextArea(12, 40);
        receiptText.setBorder(new LineBorder(Color.BLACK, 2));
        receiptText.setEditable(false);

        JScrollPane scroller = new JScrollPane(receiptText);
        receipt.add(scroller);

        //receipt.add(receiptText);
    }

    private void controlPanel(){
        controlPnl = new JPanel();
        controlPnl.setBorder(new TitledBorder(new EtchedBorder(), "Print Receipt/Quit"));

        printReceipt = new JButton("Order");
        printReceipt.addActionListener((ActionEvent ae) -> {
            String receipt = "=========================================\nCrust & Size: \n";

            //int itemCount = comboBox.getItemCount();
            //for (int i = 0; i < itemCount; i++) {
            //    if(comboBox.getItemAt(i) == "Thin"){
            //        receipt += "    Thin\n";
            //    }
            //    else if(comboBox.getItemAt(i) == "Regular"){
            //        receipt += "    Regular\n";
            //    }
            //    else if(comboBox.getItemAt(i) == "Deep-dish"){
            //        receipt += "    Deep-dish\n";
            //    }
            //}

            receipt += "    " + comboBox.getSelectedItem().toString() + ", "; //much easier way of getting the combobox
            if(comboBox.getSelectedItem().toString().equals("Thin")){
                priceOne += 3.00;
            }
            else if(comboBox.getSelectedItem().toString().equals("Regular")){
                priceOne += 4.00;
            }
            else if(comboBox.getSelectedItem().toString().equals("Deep-dish")){
                priceOne += 5.00;
            }

            if(sml.isSelected()){
                receipt += "Small\n";
                priceOne += 3.00;
            }
            else if(med.isSelected()){
                receipt += "Medium\n";
                priceOne += 3.50;
            }
            else if(large.isSelected()){
                receipt += "Large\n";
                priceOne += 4.50;
            }
            else if(sup.isSelected()){
                receipt += "Super\n";
                priceOne += 7.50;
            }

            receipt += "                                                                                Price: " + priceOne + "\n";
            receipt += "-----------------------------------------\n";
            receipt += "Toppings\n";

            if(topping1.isSelected()){
                receipt += "    Orange Peels\n";
                priceTwo += 1.50;
            }
            if(topping2.isSelected()){
                receipt += "    Erasers\n";
                priceTwo += 3.50;
            }
            if(topping3.isSelected()){
                receipt += "    Legos\n";
                priceTwo += 9.00;
            }
            if(topping4.isSelected()){
                receipt += "    Tranquilizers\n";
                priceTwo += 15.50;
            }
            if(topping5.isSelected()){
                receipt += "    Bat-teeth\n";
                priceTwo += .50;
            }
            if(topping6.isSelected()){
                receipt += "    Used-tissues\n";
                priceTwo += .01;
            }
            receipt += "                                                                                Price: " + priceTwo + "\n\n";
            receipt += "-----------------------------------------\n";

            double subtotal = Math.round((priceOne + priceTwo) * 100.0) / 100.0;
            double tax = Math.round((subtotal * .07) * 100.0) / 100.0;
            double total = Math.round((subtotal + tax) * 100.0) / 100.0;;

            receipt += "Subtotal:                                                                " + subtotal + "\n";
            receipt += "Tax:                                                                         " + tax + "\n";

            receipt += "-----------------------------------------\n";

            receipt += "Total:                                                                      " + total + "\n";

            receipt += "=========================================\n";

            receiptText.append(receipt);
        });

        clear = new JButton("Clear");
        clear.addActionListener((ActionEvent ae) -> {
            receiptText.setText("");
            if(topping1.isSelected()){
                topping1.setSelected(false);
            }
            if(topping2.isSelected()){
                topping2.setSelected(false);
            }
            if(topping3.isSelected()){
                topping3.setSelected(false);
            }
            if(topping4.isSelected()){
                topping4.setSelected(false);
            }
            if(topping5.isSelected()){
                topping5.setSelected(false);
            }
            if(topping6.isSelected()){
                topping6.setSelected(false);
            }
            med.setSelected(true);
            comboBox.setSelectedItem("Regular");
        });

        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent ae) -> {
            int getYN = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
            if(getYN == JOptionPane.YES_OPTION){
                System.exit(0);
            }
            else{
                return;
            }
        });

        controlPnl.add(printReceipt);
        controlPnl.add(clear);
        controlPnl.add(quit);
    }


}
