/*
 * Test4.java
 *
 * Created on November 11, 2013, 11:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.util.*;
import java.text.*;
/**
 *
 * @author PRASHANT
 */
public class Test4 {
    
    /** Creates a new instance of Test4 */
    public Test4() {
    }
    
    public static void main(String args[]){
        Date d1 = new Date();
        DateFormat df= new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz ");      
        String s = df.format(d1);
        System.out.println(s);
    }
    
}
