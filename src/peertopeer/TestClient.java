/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import static peertopeer.MyClient.num;

/**
 *
 * @author PRASHANT
 */
public class TestClient {
     public static void main(String[] args){
        
        BufferedReader mainBr = new BufferedReader(new InputStreamReader(System.in));
                
        String userFilePath = "", userOutFilePath = "";
        String userInput = "";
        String s ="", rfc = "",rfcindex = "";
        int port;
        
        try{
            System.out.println(" Enter the input file path: \n");
            userFilePath = mainBr.readLine();
            
            System.out.println(userFilePath);
            
            System.out.println(" Enter the output file path: \n");
            userOutFilePath = mainBr.readLine();
            
           // mainBr.close();
        }catch(IOException e){
            System.out.println("Error while reading user Input: " + e);
        }
        
        
        MyClient c = new MyClient(userFilePath, userOutFilePath);
        Runnable r1 = new P2P(c);
        Thread t1 = new Thread(r1);
        t1.start();
        c.addClient();
        while(true){
            try{
            System.out.println(" Enter command to call: \n");
            userInput = mainBr.readLine();
            
            if(userInput.equalsIgnoreCase("AddRFC")){
                System.out.println(" Enter rfc name: \n");
                rfc = mainBr.readLine();
                System.out.println(" Enter rfc index: \n");
                rfc index = mainBr.readLine();
                RFC
                c.addRFC(null);
            }else if(userInput.equalsIgnoreCase("Lookup")){
                c.lookupRFC();
            }if(userInput.equalsIgnoreCase("List")){
                c.listRFCs();
            }if(userInput.equalsIgnoreCase("get")){
                System.out.println(" Enter Ipaddress: \n");
                s = mainBr.readLine();
                System.out.println(" Enter Ipaddress: \n");
                port = Integer.parseInt(mainBr.readLine());
                System.out.println(" Enter RFC: \n");
                rfc = mainBr.readLine();
                
                Socket client = new Socket(s, port);
            }
            
            
           mainBr.close();
            }catch(IOException e){
                System.out.println("Error while reading user Input: " + e);
            }
        }
                
        
    }

}
