/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find.pi.ui;

import find.pi.logic.findPiToDecimalPlace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sjinadasa
 */
public class ButtonListener implements ActionListener {

    private JButton button;
    private String btnType;
    
    private JTextField input;
    private JTextArea output;
    private JTextField precision;
    
    public ButtonListener(JButton button, String buttonType, JTextField inputField, JTextArea outputField, JTextField precision){
        this.button = button;
        this.btnType = buttonType;
        this.input = inputField;
        this.output = outputField;
        this.precision = precision;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(btnType == "process"){
            try{
                int decPlace = Integer.parseInt(this.input.getText());
                
                int precisionInt = Integer.parseInt(this.precision.getText());
                if(precisionInt <= 0 || precisionInt > 1000){
                    throw new IllegalArgumentException();
                }
                // Do Pi finding process and print output
                findPiToDecimalPlace processPi = new findPiToDecimalPlace(decPlace,precisionInt);
                String piSol = processPi.run();               
                
                this.output.setLineWrap(true);
                this.output.setText(piSol);
                
            }catch (NumberFormatException x){
                this.output.setText("Please enter an Integer");
            }catch  (IllegalArgumentException I){
                this.output.setText("Please enter a number between 1 - 1000");
            }
            
        }else if(btnType == "reset"){
            this.input.setText("");
            this.output.setText("");
            this.precision.setText("");
        }
    }
    
    
}
