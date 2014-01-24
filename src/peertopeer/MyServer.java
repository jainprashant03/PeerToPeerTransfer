package peertopeer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import static peertopeer.MyServer.linkA;
import static peertopeer.MyServer.linkB;

public class MyServer {
    
    //Okay so we have a linked list to store hostnames and port numbers. Active hosts list
    static LinkedList<MyLinkedListA> linkA = new LinkedList<MyLinkedListA>();
    //One more to hold our rfc data and host and port data
    static LinkedList<MyLinkedListB> linkB = new LinkedList<MyLinkedListB>();
    
    public String respond(String myClientOp){
        //This method will find the client  request and respond to it
        String[] request = myClientOp.split(" ");
        MyReply reply = new MyReply();
        if ("LIST".equals(request[0])){
            myClientOp = myClientOp + "Title: This is a padding";
            System.out.println("Printing LIST myclientop "+ myClientOp);
        }
        //We need to tokenize the input request
        //We would need it later
        MyTokenizer token = new MyTokenizer(myClientOp);
        //System.out.println(request[0]);
        String[] myHostPort=token.tokenizeHostPort();
        String[] myRfcTitle=token.tokenizeRfcTitle();
                
        if ("ADD".equals(request[0]))
        {
            String[] addedRFC = myAdd(myClientOp);
            return reply.addReply(addedRFC);
        }
        else if ("LOOKUP".equals(request[0]))
        {   //System.out.println("Inside lookup");
            LinkedList<MyLinkedListB> lookup = new LinkedList<MyLinkedListB>(); 
            lookup = myLookup(request[2]); //this is the RFC number //myLookup will return a linked list.
            if (lookup.size()==0)
            {
                return reply.notFound(myRfcTitle[0], myRfcTitle[1], myHostPort[0], myHostPort[1]);
            }
            else
            {
                return reply.sendLookup(lookup);
            }
        }
        else if ("LIST".equals(request[0]))
        {
            //myList(); not needed. just displaying all the contents anyway. nothing to process
            return reply.sendList(linkB);
        }
        else
        {
            return reply.notCorrect(myRfcTitle[0], myRfcTitle[1], myHostPort[0], myHostPort[1]);
        }
    }
    
    public synchronized void go(){  //making linked list thread safe
        try{
            ServerSocket serverSock = new ServerSocket(7734);
            while(true){
                Socket sock = serverSock.accept();
                InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
                BufferedReader reader = new  BufferedReader(streamReader);
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                while(true){
                    String myString = "";
                    String myClientOp = "";
                    //System.out.println("Printing one" + myClientOp);
                    while( !"".equals(myString = reader.readLine())) {
                        myClientOp = myClientOp + myString + "\n";
                        
                      }
                    //System.out.println("Printing myclientop " + myClientOp);
                //String myClientOp = reader.readLine()+ "\n" + reader.readLine()+ "\n" + reader.readLine()+ "\n" + reader.readLine();
                System.out.println("Printing REQUEST\n" + myClientOp);
                String[] myTempString = myClientOp.split(" ");
                
                if ("LOGOFF".equals(myTempString[0]))
                    {
                            Iterator<MyLinkedListB> itr = linkB.iterator();
                            while (itr.hasNext()) 
                            {
                            MyLinkedListB element = itr.next();
                            myTempString[1]=myTempString[1].replace("\n", "");
                            if (element.hostName.equals(myTempString[1]))
                            {
                                itr.remove();
                                System.out.println("Removing object related to " + myTempString[1] );
                            }
                            }
                    }
                else
                {
                String myResponse = respond(myClientOp);
                System.out.println("\nPrinting RESPONSE\n"+ myResponse);
                writer.println(myResponse);
                }
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
            }   
    }
    
    public synchronized String[] myAdd(String myClientOp){
        
        MyTokenizer token = new MyTokenizer(myClientOp);
        String[] myHostPort=token.tokenizeHostPort();
        String[] myRfcTitle=token.tokenizeRfcTitle();
        
        MyLinkedListA hostport = new MyLinkedListA(myHostPort[0],myHostPort[1]);
        linkA.addFirst(hostport);
        
        MyLinkedListB rfctitle = new MyLinkedListB(myRfcTitle[0], myRfcTitle[1], myHostPort[0],myHostPort[1] );
        linkB.addFirst(rfctitle);
        
        String[] addedString = {myRfcTitle[0], myRfcTitle[1], myHostPort[0],myHostPort[1]};
        return addedString;
    }
    
    public synchronized LinkedList<MyLinkedListB> myLookup(String myRfc){
        Iterator<MyLinkedListB> itr = linkB.iterator();
        LinkedList<MyLinkedListB> lookuplist = new LinkedList<MyLinkedListB>(); //to store the output of lookup
        while (itr.hasNext()) {
            MyLinkedListB element = itr.next();
            if(element.rfcNumber.equals(myRfc))
            {
               lookuplist.add(element);           
            }
        }
        return lookuplist;  //this has our search results
    }      
        
    public static void main(String[] args) {

    try{
      ServerSocket listener = new ServerSocket(7734);
      Socket server;
      while(true){
        server = listener.accept();
        doComms conn_c= new doComms(server);
        new Thread(conn_c).start();
        }
        } catch (IOException ioe) {
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
        }
    } 
    }

    class doComms implements Runnable {
        private Socket server;
        doComms(Socket server) {
        this.server=server;
        }

    public  void run (){
          try {
        // Get input from the client
                InputStreamReader streamReader = new InputStreamReader(server.getInputStream());
                BufferedReader reader = new  BufferedReader(streamReader);
                PrintWriter writer = new PrintWriter(server.getOutputStream());
                while(true){
                    String myString = "";
                    String myClientOp = "";
                    //System.out.println("Printing one" + myClientOp);
                    while( !"".equals(myString = reader.readLine())) {
                        myClientOp = myClientOp + myString + "\n";
                      }
                    //System.out.println("Printing myclientop " + myClientOp);
                //String myClientOp = reader.readLine()+ "\n" + reader.readLine()+ "\n" + reader.readLine()+ "\n" + reader.readLine();
                System.out.println("Printing REQUEST\n" + myClientOp);
                String[] myTempString = myClientOp.split(" ");
                if ("LOGOFF".equals(myTempString[0]))
                    {
                        removeElement(myTempString);
                        break;
                    }
                else
                {
                String myResponse = respond(myClientOp);
                //we need to send eom
                String myFinalResponse = myResponse +"eom"; 
                System.out.println("\nPrinting RESPONSE\n"+ myFinalResponse);
                writer.println(myFinalResponse);
                writer.flush();
                }
                }
      } catch (IOException ioe) {
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
        
    public String respond(String myClientOp){
        //This method will find the client  request and respond to it
        String[] request = myClientOp.split(" ");
        MyReply reply = new MyReply();
        if ("LIST".equals(request[0])){
            myClientOp = myClientOp + "Title: This is a padding";
            System.out.println("Printing LIST myclientop "+ myClientOp);
        }
        //We need to tokenize the input request
        //We would need it later
        MyTokenizer token = new MyTokenizer(myClientOp);
        //System.out.println(request[0]);
        String[] myHostPort=token.tokenizeHostPort();
        String[] myRfcTitle=token.tokenizeRfcTitle();
                
        if ("ADD".equals(request[0]))
        {
            String[] addedRFC = myAdd(myClientOp);
            return reply.addReply(addedRFC);
        }
        else if ("LOOKUP".equals(request[0]))
        {   //System.out.println("Inside lookup");
            LinkedList<MyLinkedListB> lookup = new LinkedList<MyLinkedListB>(); 
            lookup = myLookup(request[2]); //this is the RFC number //myLookup will return a linked list.
            if (lookup.size()==0)
            {
                return reply.notFound(myRfcTitle[0], myRfcTitle[1], myHostPort[0], myHostPort[1]);
            }
            else
            {
                return reply.sendLookup(lookup);
            }
        }
        else if ("LIST".equals(request[0]))
        {
            //myList(); not needed. just displaying all the contents anyway. nothing to process
            return reply.sendList(linkB);
        }
        else
        {
            return reply.notCorrect(myRfcTitle[0], myRfcTitle[1], myHostPort[0], myHostPort[1]);
        }
    }
        
    public synchronized String[] myAdd(String myClientOp){
        
        MyTokenizer token = new MyTokenizer(myClientOp);
        String[] myHostPort=token.tokenizeHostPort();
        String[] myRfcTitle=token.tokenizeRfcTitle();
        
        MyLinkedListA hostport = new MyLinkedListA(myHostPort[0],myHostPort[1]);
        linkA.addFirst(hostport);
        
        MyLinkedListB rfctitle = new MyLinkedListB(myRfcTitle[0], myRfcTitle[1], myHostPort[0],myHostPort[1] );
        linkB.addFirst(rfctitle);
        
        String[] addedString = {myRfcTitle[0], myRfcTitle[1], myHostPort[0],myHostPort[1]};
        return addedString;
    }
    
    public synchronized LinkedList<MyLinkedListB> myLookup(String myRfc){
        Iterator<MyLinkedListB> itr = linkB.iterator();
        LinkedList<MyLinkedListB> lookuplist = new LinkedList<MyLinkedListB>(); //to store the output of lookup
        while (itr.hasNext()) {
            MyLinkedListB element = itr.next();
            if(element.rfcNumber.equals(myRfc))
            {
               lookuplist.add(element);           
            }
        }
        return lookuplist;  //this has our search results
    }     
    
    public synchronized void removeElement(String[] myTempString){
        int i;
        for(i =0; i< linkB.size(); i++){
            if (linkB.get(i).hostName.equals(myTempString[1]))
            {
                linkB.remove(i);
            }
        }
  }
        
}