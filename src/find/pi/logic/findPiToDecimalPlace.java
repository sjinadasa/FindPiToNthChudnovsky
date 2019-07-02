/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find.pi.logic;

import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.swing.JTextArea;

/**
 *
 * @author sjinadasa
 */
public class findPiToDecimalPlace {
    
    private int decPlace;
    
    // for Chudnovsky Algorithm
    private BigDecimal K = new BigDecimal("6");
    private BigDecimal L = new BigDecimal("13591409");
    private BigDecimal X = new BigDecimal("1");
    private BigDecimal M = new BigDecimal("1");
    private BigDecimal S = new BigDecimal("13591409");
    
    private int k;
    
    
    public findPiToDecimalPlace (int decPlace, int precision){
        this.decPlace = decPlace;
        this.k = precision;
    }
    
    public String run(){
        
        for(int i = 1; i <= k; i++ ){
            
            // L(k+1) = L(k) + 545,140,134
            this.L = this.L.add(BigDecimal.valueOf(545140134));
            //System.out.println("L: " + this.L);
            
            // X(k+1) = X(k) * (-262,537,412,640,768,000)
            this.X = this.X.multiply(new BigDecimal("-262537412640768000"));
              
            // Because I used BigDecimal, all this had to be done. No regrets?
            // This is to get (K(k)^3 - 16K(k))/(k+1)^3
            // This is K(k)^3
            BigDecimal K3 = new BigDecimal(this.K.pow(3).toString());
            //This is 16K(k)
            BigDecimal K16 = new BigDecimal(this.K.multiply(BigDecimal.valueOf(16)).toString());
            //This is (K(k)^3 - 16K(k))
            BigDecimal K3MinusK16 = K3.subtract(K16);        
            // This is (k+1)^3
            double kPlus1To3 = Math.pow((i), 3);
            //Finally we get (K(k)^3 - 16K(k))/(k+1)^3
            BigDecimal finalKforM = K3MinusK16.divide(BigDecimal.valueOf(kPlus1To3),this.decPlace, RoundingMode.HALF_UP);
            
            
            
            // M(k+1) = M(k)(K(k)^3 - 16K(k))/(k+1)^3
            this.M = this.M.multiply(finalKforM);
            
            // New K is set up using K(k+1) = K(k) + 12
            this.K = this.K.add(BigDecimal.valueOf(12));
            
            //So now we add the whole iteration in the form;
            // M(k) * L(k) / X(k)
            //We need helpers to get this done too due to BigDecimal
            BigDecimal stepSOne = this.M.multiply(this.L);

            
            BigDecimal stepSTwo = stepSOne.divide(this.X,this.decPlace,RoundingMode.HALF_UP);
            
            // Now we can update S
            this.S = this.S.add(stepSTwo);

        }
        
        // Once the iterations are done, we can get a final S which has to be multiplied by a C
        double C = 426880*Math.sqrt(10005);

        BigDecimal pi = BigDecimal.valueOf(C).divide(this.S, this.decPlace, RoundingMode.HALF_UP);
        
        // Get only the required decimal places
        try{
            String piSol = pi.toString().substring(0, (decPlace + 2));
            return piSol;
        }catch(StringIndexOutOfBoundsException e){
            String piSol = "3";
            return piSol;
        }  
    }
}
