/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find.pi.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 *
 * @author sjinadasa
 */
public class UserInterface implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        this.frame = new JFrame("Find Pi to Nth Decimal Place");

        this.frame.setPreferredSize(new Dimension(800, 400));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void createComponents(Container container) {

        GridLayout layoutUI = new GridLayout(4, 2);
        layoutUI.setHgap(5);
        layoutUI.setVgap(5);
        container.setLayout(layoutUI);

        JTextField inputField = new JTextField();
        JLabel inputLabel = new JLabel("<html> <body style='text-align:center'>Enter the number of "
                + "decimal places to find Pi to </body> </html>", JLabel.CENTER);
        
        JTextField precField = new JTextField();
        JLabel precLabel = new JLabel("Enter the precision level (1 - 1000 ONLY in this edition)", JLabel.CENTER);

        JTextArea outputField = new JTextArea();
        JScrollPane outputScroll = new JScrollPane(outputField);
        outputField.setEnabled(false);
        JLabel outputLabel = new JLabel("Pi is ", JLabel.CENTER);

        JButton findBtn = new JButton("Find Pi");
        ButtonListener findButtonListener = new ButtonListener(findBtn, "process", inputField,outputField,precField);
        findBtn.addActionListener(findButtonListener);
        
        JButton resetBtn = new JButton ("Reset");
        ButtonListener resetButtonListener = new ButtonListener(resetBtn, "reset", inputField,outputField, precField);
        resetBtn.addActionListener(resetButtonListener);
        
        container.add(inputLabel);
        container.add(inputField);
        container.add(precLabel);
        container.add(precField);
        container.add(outputLabel);
        container.add(outputScroll);

        container.add(findBtn);
        container.add(resetBtn);
    }

}
