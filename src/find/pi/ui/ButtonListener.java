/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find.pi.ui;

import find.pi.logic.findPiToDecimalPlace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.Thread.State;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

/**
 *
 * @author sjinadasa
 */
public class ButtonListener implements ActionListener, PropertyChangeListener {

    private JButton button;
    private String btnType;

    private JTextField input;
    private JTextArea output;
    private JTextField precision;
    private JProgressBar progressBar;

    private findPiToDecimalPlace processPi;
    private Thread t;

    // For the propertyChange listener
    public ButtonListener(JProgressBar progressBar, JButton button) {
        this.progressBar = progressBar;
    }

    // for Button Listener
    public ButtonListener(JButton button, String buttonType, JTextField inputField, JTextArea outputField, JTextField precision, JProgressBar progressBar) {
        this.button = button;
        this.btnType = buttonType;
        this.input = inputField;
        this.output = outputField;
        this.precision = precision;
        this.progressBar = progressBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (btnType == "process") {

            // Runs Pi finder in new thread
            findPiInNewThread();
            t.start();

            // What happens if reset is pressed
        } else if (btnType == "reset") {
            this.input.setText("");
            this.output.setText("");
            this.precision.setText("");
        }
    }

    private void findPiInNewThread() {
        // Running the pi finding class in a new thread - seperate from the UI
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int decPlace = Integer.parseInt(input.getText());

                    int precisionInt = Integer.parseInt(precision.getText());
                    if (precisionInt <= 0 || precisionInt > 1000) {
                        throw new IllegalArgumentException();
                    }

                    button.setEnabled(false);
                    // Do Pi finding process and print output
                    processPi = new findPiToDecimalPlace(decPlace, precisionInt);
                    processPi.addPropertyChangeListener(new ButtonListener(progressBar, button));
                    processPi.execute();

                    String piSol = processPi.get();

                    output.setLineWrap(true);
                    output.setText(piSol);
                    button.setEnabled(true);

                } catch (NumberFormatException x) {
                    output.setText("Please enter an Integer");
                } catch (IllegalArgumentException I) {
                    output.setText("Please enter a number between 1 - 1000");
                } catch (Exception I) {
                    output.setText("Interrupted - Try Again");
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            this.progressBar.setValue(progress);
        }
    }
}
