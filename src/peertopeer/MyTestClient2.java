package peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyTestClient2 {

    public void go(){
        //Lot of things screw up here. SO lets have a catch
        try {
            //Create a new socket connection here
            Socket socket = new Socket("127.0.0.1", 7734);                //creating socket connection with server
            PrintStream output = new PrintStream(socket.getOutputStream());         //creating print stream      
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //creating input stream
            
            String sp = " ";
            String lf = "\n";
            String cr = "\r";
//            String addCommand = "ADD" + sp + "RFC" + sp + "1234" + sp + "2P-CI/1.0" + cr + lf + "Host:" + sp + "my.host2" + cr + lf + "Port:" + sp + "5678" + cr + lf + "Title:" + sp + "A Proferred Official ICP" + cr + lf;            
//            System.out.println("printing client message to server "+ addCommand);
//            String addCommand1 = "ADD" + sp + "RFC" + sp + "1235" + sp + "2P-CI/1.0" + cr + lf + "Host:" + sp + "my.host2" + cr + lf + "Port:" + sp + "5678" + cr + lf + "Title:" + sp + "A Proferred Official ICP" + cr + lf;            
//            System.out.println("printing client message to server "+ addCommand1);
//            String lookupCommand = "LOOKUP" + sp + "RFC" + sp + "134" + sp + "2P-CI/1.0" + cr + lf + "Host:" + sp + "my.host2" + cr + lf + "Port:" + sp + "5678" + cr + lf + "Title:" + sp + "A Proferred Official ICP" + cr + lf;            
//            System.out.println("printing client message to server "+ lookupCommand);
//            String lookupCommand1 = "LOOKUP" + sp + "RFC" + sp + "1234" + sp + "2P-CI/1.0" + cr + lf + "Host:" + sp + "my.host2" + cr + lf + "Port:" + sp + "5678" + cr + lf + "Title:" + sp + "A Proferred Official ICP" + cr + lf;            
//            System.out.println("printing client message to server "+ lookupCommand1);
            String listCommand = "LIST" + sp + "ALL" + sp + "2P-CI/1.0" + cr + lf + "Host:" + sp + "my.host2" + cr + lf + "Port:" + sp + "5678" + cr + lf;       
            //String logoffCommand ="LOGOFF" + sp + "my.host2" + cr + lf;
            //output.println(addCommand);
            //output.flush();
            //output.println(lookupCommand);
            //output.flush();
            //output.println(addCommand1);
            //output.println(lookupCommand1);
            while(true){
            output.println(listCommand);
            }
            //output.println(logoffCommand);
            //output.println(listCommand);
            //String serverResponse = input.readLine();
           // System.out.println("printing client message FROM server "+ serverResponse);
            
        }catch(IOException io){
            System.out.println("Unexpected error in MyClient constructor while registering with server. Error Stack: " + io);
        }
        //receiving response from the server
    }           
            
    public static void main(String[] args){
        // TODO code application logic here
//        String sp = " ";
//            String lf = "\n";
//            String cr = "\r";
//        String addCommand = "ADD" + sp + "RFC" + sp + "1234" + "P2P-CI/1.0" + cr + lf
//                    + "Host: my.host" + cr + lf + "Port: 5678"  + cr + lf + "Title: A Proferred Official ICP" + cr + lf;
//            System.out.println("printing client message to server "+ addCommand);
        MyTestClient2 client1 = new MyTestClient2();
        client1.go();
    }


}