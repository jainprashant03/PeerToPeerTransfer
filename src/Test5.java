/*
 * Test5.java
 *
 * Created on November 14, 2013, 10:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author PRASHANT
 */
public class Test5 {
    
    /** Creates a new instance of Test5 */
    public Test5() {
    }
    
    public static void main(String args[]){
        BufferedReader mainBr = new BufferedReader(new InputStreamReader(System.in));
                
        String userFilePath = "", userOutFilePath = "";
        
        try{
            System.out.println(" Enter the input file path: \n");
            userFilePath = mainBr.readLine();
            
            System.out.println(userFilePath);
            
            System.out.println(" Enter the output file path: \n");
            userOutFilePath = mainBr.readLine();
        }catch(IOException e){
            System.out.println("Error while reading user Input: " + e);
        }
        try{
            FileReader fr = new FileReader(userFilePath + "HostSoftware.txt");
            BufferedReader br = new BufferedReader(fr);
                       
            File f = new File(userOutFilePath + "HostSoftware.txt");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            
            String s;
            while ((s = br.readLine()) != null){
                pw.println(s);
            }
            
        }catch (Exception e){
            System.out.println("Unexpected Exception : " + e);
        }
        
    }
    
}
