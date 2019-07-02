/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find.pi;

import javax.swing.SwingUtilities;
import find.pi.ui.UserInterface;
/**
 *
 * @author Sachintha Jinadasa
 */
public class FindPi {

    public static void main(String[] args) {
        // Program to find Pi to a given number of decimal places
        // Has a user interface using Swing
        SwingUtilities.invokeLater(new UserInterface());
        
    }
    
}
