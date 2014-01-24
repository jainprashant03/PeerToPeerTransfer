package peertopeer;
import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;

public class MyClient {
    
    static class RFC{                                              //Class RFC - with index and name
        String index;
        String name;
        
        public RFC(String index, String name){
            this.index = index;
            this.name = name;
        }
        
        public String toString(){                                   //Overriding toString method to print RFC object
            return (index + " : " + name);
        }
        public boolean equals(Object o){                            //Overriding equals method to enable RFC object comparison
            if(index.equals (((RFC)o).index)){
                return true;
            }else{
                return false;
            }
        }
        
        //bean methods
        protected void setIndex (String i){
            index = i;
        }
        public String getIndex(){
            return this.index;
        }
        protected void setName (String n){
            name = n;
        }
        public String getName(){
            return this.name;
        }
    }
    
     static class Status{                                              //Class RFC - with index and name
        String code;
        String statusMsg;
        
        public Status(String cde, String msg){
            code = cde;
            statusMsg = msg;
        }
        
        public String toString(){                                   //Overriding toString method to print RFC object
            return (code + " : " + statusMsg);
        }
        public boolean equals(Object o){                            //Overriding equals method to enable RFC object comparison
            if(code.equals (((Status)o).code)){
                return true;
            }else{
                return false;
            }
        }
        
        //bean methods
        protected void setCode (String c){
            code = c;
        }
        public String getCode(){
            return this.code;
        }
        protected void setStatusMsg(String m){
            statusMsg = m;
        }
        public String getStatusMsg(){
            return this.statusMsg;
        }
    }
     
    static LinkedList <RFC> rfcList = new LinkedList<RFC> ();      //Linked list maintaining RFC list from which client can select the RFC they want to store
    static LinkedList <Integer> num = new LinkedList<Integer> ();   //List to obtain unique host ID
    static LinkedList <Status> status = new LinkedList<Status> ();  //List for the error status
    
    //Static Block for initializing rfcList and num linked list
    static{
      
      rfcList.add(new RFC("0001", "HostSoftware"));
      rfcList.add(new RFC("0002", "Host software"));
      /*rfcList.add(new RFC("0003", "Documentation conventions"));
      rfcList.add(new RFC("0004", "Host software"));
      rfcList.add(new RFC("0005", "Network Timetable"));
      rfcList.add(new RFC("0006", "CONVERSATION WITH BOB KAHN"));
      //rfcList.add(new RFC("0007", "Host-Imp Interface"));*/
      //rfcList.add(new RFC("0008", "DEL"));
      //rfcList.add(new RFC("0009", "DEL"));
      //rfcList.add(new RFC("0010", "DOCUMENTATIONcONVENTIONS"));
             
      for (int x = 0; x < 100; x++){                     //Populating list for unique IDs
          num.add((x+1));
      }
      
      status.add(new Status("200","OK"));
      status.add(new Status("400","Bad Request"));
      status.add(new Status("404","Not Found"));
      status.add(new Status("505","P2P-CI Version Not Supported"));
    }
    
    //Duplicating the RFC List
    LinkedList<RFC> dupRFCList = new LinkedList<RFC>();
    
    //Init Block for initializing duplicate RFC list
    {
        for(int x = 0; x < rfcList.size(); x++){
            dupRFCList.add(rfcList.get(x));
        }
        
    }
    
    //MyClient class Parameters
    private String hostName;
    private int portNumber, portAdd;
    private LinkedList<RFC> rfc = new LinkedList<RFC>();
    private int id, mul;
    public String filePath, fileName, fileExtentsion, outFilePath;
    private File rfcFile;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private PrintStream output;
    private BufferedReader input;
    private String OS;
    private String noErrorStatus = "200";
    private String eom = "eom";
    public ServerSocket myService = null;
    public Socket serviceSocket = null;
    //MyClient Constructor
    public MyClient(String inPath, String outPath, String host, int port) {
        mul = dupRFCList.size();                            //setting multiplier when we have 10 clients
        portAdd = 50000;                                    //port number following portAdd should be used
        filePath = inPath;                                  //filepath where the RFCs are placed
        outFilePath = outPath;                              //filepath where the RFCs are placed after reading
        fileExtentsion = ".txt";                            //extentsion for the files
        serverName = "10.139.64.236";                           //server to connect to
        serverPort = 7734;                                  //server port to connect to
        
        //obtaining id for client and ensuring it is not used by another client
       //id = clientid;
        
        //Initializing hostName and portNumber for client
        hostName = host;
        portNumber = port;
        
        //Initializing RFC for client and remove it from static list
        //rfc.add(rfcList.removeFirst());
                
        //populating fileName and creating rfcFile
        /*fileName = filePath + rfc.getName() + fileExtentsion;
        rfcFile = new File(fileName);*/
        
        //creating connection with Server
        try{
            socket = new Socket(serverName, serverPort);                //creating socket connection with server
            output = new PrintStream(socket.getOutputStream());         //creating print stream      
            input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //creating input stream
            //this.addClient();
        }catch(IOException io){
            System.out.println("Unexpected error in MyClient constructor while registering with server. Error Stack: " + io);
        }
    }
    
    //Bean methods
    protected void setHostName(String name){
        hostName = name;
    }
    
    public String getHostName(){
        return hostName;
    }
    
    protected void setPortNumber(int port){
        portNumber = port;
    }
    
    public int getPortNumber(){
        return portNumber;
    }
    
    protected void setRfc(LinkedList<RFC> r){
        rfc = r;
    }
    
    protected void setRfc(RFC r){
        rfc.add(r);
    }
    
    protected void setRfc(String index, String name){
        rfc.add(new RFC(index, name));
    }
    
    public LinkedList<RFC> getRfc(){
        return rfc;
    }
    
    public RFC getRfc(int listIndex){
        return rfc.get(listIndex);
    }
    
    protected void setRfcFile(String fileName){
        rfcFile = new File(fileName);
    }
    
    protected void setRfcFile(File file){
        rfcFile = file;
    }
    
    public File getRfcFile(){
        return rfcFile;
    }
    
    protected void setSocket(String name, int port){
        try{
            socket = new Socket(name, port);
        }catch(IOException io){
            System.out.println("Unexpected error in setSocket while creating socket. Error Stack: " + io);
        }
    }
    
    protected void setSocket(Socket s){
       socket = s;
    }
    
    public Socket getSocket(){
       return socket;
    }
    
    protected void setOS(String OS){
        this.OS = OS;
    }
    
    public String getOS(){
        return OS;
    }
    
    protected void setOutput(Socket s){
       try{
           output = new PrintStream(s.getOutputStream());
       }catch(IOException io){
           System.out.println("Unexpected error in setOutput while creating OutputStream. Error Stack: " + io);
       }
    }
    
    public PrintStream getOutput(){
        return output;
    }
    
    protected void setInput(Socket s){
       try{
           input = new BufferedReader(new InputStreamReader(s.getInputStream()));
       }catch(IOException io){
           System.out.println("Unexpected error in setInput while creating InputStream. Error Stack: " + io);
       }
    }
    
    public BufferedReader getInput(){
        return input;
    }
    
    //adding client to server
    protected void addClient(){
        String addCommand = "";
        String serverResponse = "";
        String method = "ADD";
        String rFC = "RFC";
        String version = "P2P-CI/1.0";
        String host = "Host: " + this.hostName;
        String port = "Port: " + this.portNumber;
        String title = "Title: ";
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        
        for(int x = 0; x < rfc.size(); x++){
            addCommand = addCommand + method + sp + rFC + sp + rfc.get(x).getIndex() + sp + version + cr + lf
                    + host + cr + lf + port + cr + lf + title + rfc.get(x).getName() + cr + lf;
        }
        output.println(addCommand);
        output.flush();
        
        //receiving response from the server
        try{
            serverResponse = input.readLine();
            MyTokenizer token = new MyTokenizer(serverResponse);
            
            if(noErrorStatus.equals((token.tokenizeStatus())[1])){
                System.out.println("Client successfully added to server");
            }else{
                System.out.println("Client couldnot be added to server as: "+ (token.tokenizeStatus())[2]);
            }
            
            while(!((serverResponse = input.readLine()).equalsIgnoreCase(eom)));
            
        }catch(IOException e){
            System.out.println("Error in AddClient while reading response from server: "+ e);
        }
        
    }
    
    //adding new RFC information to the server
     protected void addRFC(RFC r){
        
        rfc.add(r);
         
        String addCommand = "";
        String serverResponse = "";
        String method = "ADD";
        String rFC = "RFC";
        String version = "P2P-CI/1.0";
        String host = "Host: " + this.hostName;
        String port = "Port: " + this.portNumber;
        String title = "Title: ";
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        
        //Constructing add command
        addCommand = addCommand + method + sp + rFC + sp + r.getIndex() + sp + version + cr + lf
                    + host + cr + lf + port + cr + lf + title + r.getName() + cr + lf;
            
        output.println(addCommand);
        output.flush();
        
        //receiving response from the server
        try{
            serverResponse = input.readLine();
            MyTokenizer token = new MyTokenizer(serverResponse);
            
            if(noErrorStatus.equals((token.tokenizeStatus())[1])){
                System.out.println("RFC details updated to server");
            }else{
                System.out.println("RFC couldnot be added to server as: "+ (token.tokenizeStatus())[2]);
            }
            
            while(!((serverResponse = input.readLine()).equalsIgnoreCase(eom)));
            
        }catch(IOException e){
            System.out.println("Error in Add RFC while reading response from server: "+ e);
        }
     
     }
    
     //requesting a List of RFC available with Server
     protected void listRFCs(){
        String listCommand = "";
        String serverResponse = "";
        String method = "LIST";
        String rFC = "ALL";
        String version = "P2P-CI/1.0";
        String host = "Host: " + this.hostName;
        String port = "Port: " + this.portNumber;
        //String title = "Title: ";
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        
        //Constructing add command
        listCommand = listCommand + method + sp + rFC + sp + version + cr + lf
                    + host + cr + lf + port + cr + lf;
        
        //sending list command to the server
        output.println(listCommand);
        output.flush();
        
        //receiving response from the server
        try{
            serverResponse = input.readLine();
            MyTokenizer token = new MyTokenizer(serverResponse);
            
            if(noErrorStatus.equals((token.tokenizeStatus())[1])){
                System.out.println("RFC List obtained from the server");
                System.out.println("\n Printing List: \n" + serverResponse);
            }else{
                System.out.println("RFC List couldnot be obtained from the server as: "+ (token.tokenizeStatus())[2]);
            }
            
            while(!((serverResponse = input.readLine()).equalsIgnoreCase(eom))){
                System.out.println(serverResponse);
            }
            
        }catch(IOException e){
            System.out.println("Error in ListRFC while reading response from server: "+ e);
        }
    }
     
    //lookup a client with a particular RFC on server, P2S, updating the info with server.
    protected void lookupRFC(RFC r){
        String lookupCommand = "";
        String serverResponse = "";
        String method = "LOOKUP";
        String rFC = "RFC";
        String version = "P2P-CI/1.0";
        String host = "Host: " + this.hostName;
        String port = "Port: " + this.portNumber;
        String title = "Title: ";
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        
        /*int rFCid = 0;
        
        //obtaining random RFC not present in client sending request
        do{
            rFCid = (int)(Math.random() * dupRFCList.size());
        }while(!(rfc.contains(dupRFCList.get(rFCid))));*/
        
        //constructing lookup command
        lookupCommand = lookupCommand + method + sp + rFC + sp + r.getIndex() + sp + version + cr + lf
                    + host + cr + lf + port + cr + lf + title + r.getName() + cr + lf;
        
        //sending loopup command to server
        output.println(lookupCommand);
        output.flush();
        
        
        //receiving response from the server
        try{
            serverResponse = input.readLine();
            MyTokenizer token = new MyTokenizer(serverResponse);
            
            if(noErrorStatus.equals((token.tokenizeStatus())[1])){
                System.out.println("RFC successfully lookedup on server");
                System.out.println("\n Lookup Result: \n" + serverResponse);
            }else{
                System.out.println("RFC couldnot be lookedup on server as: "+ (token.tokenizeStatus())[2]);
            }
            while(!((serverResponse = input.readLine()).equalsIgnoreCase(eom))){
                System.out.println(serverResponse);
            }
            
            
        }catch(IOException e){
            System.out.println("Error in Lookup RFC while reading response from server: "+ e);
        }
        
        //obtaining the connectivity details of the client and connecting to client
        /*String clientHost = "";
        int clientPort;
        Socket clientSocket = null;
        
        try{
            serverResponse = input.readLine();
            String[] clientDetail = serverResponse.split(" ");
            
            int len = clientDetail.length;
            clientHost = clientDetail[(len - 2)];
            clientPort = Integer.parseInt(clientDetail[(len - 1)]);
            
            clientSocket = new Socket("10.139.64.236", clientPort);
            
            while(!((serverResponse = input.readLine()).equalsIgnoreCase(eom)));
        }catch(IOException e){
            System.out.println("Error while connecting to the client: "+ e);
        }*/
        
        //P2P transfer
        //getRFC(clientSocket,dupRFCList.get(rFCid));
        
        //adding the RFC to client's list and updating the same with the server
        //this.addRFC(dupRFCList.get(rFCid));
    }
    
    public void getRFC(Socket s, RFC r){
        String getCommand = "";
        String closeCommand = "LOGOFF";
        String serverResponse = "";
        String method = "GET";
        String rFC = "RFC";
        String version = "P2P-CI/1.0";
        String host = "Host: " + this.hostName;
        //String port = "Port: " + this.portNumber;
        String os = "OS: " + this.OS;
        //String title = "Title: ";
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        String eom = "eom";
        String myTemp = "";
        String eof = "eof";
        String outFolder = "";
        int inputHDSize = 6;
        
        try{
            PrintStream coutput = new PrintStream(s.getOutputStream());         //creating print stream
            BufferedReader cinput = new BufferedReader(new InputStreamReader(s.getInputStream())); //creating input stream
            
            //Constructing get command
            myTemp = myTemp + method + sp + rFC + sp + r.getIndex() + sp + version + cr + lf
                    + host + cr + lf + os + cr + lf;
            
            getCommand = myTemp + eom;
            
            coutput.println(getCommand);
            coutput.flush();
            
            //reading response from client
            serverResponse = cinput.readLine();
            MyTokenizer token = new MyTokenizer(serverResponse);
            
            boolean read = noErrorStatus.equals((token.tokenizeStatus())[1]);
            
            if(read){
            /*for(int i = 0; i < inputHDSize; i++){
                serverResponse = cinput.readLine();
            }*/
            
            cinput.readLine();
            cinput.readLine();
            cinput.readLine();
            cinput.readLine();
            cinput.readLine();
            
            outFolder = outFilePath + this.hostName + "_" + this.portNumber;
            File dir= new File(outFolder);
            dir.mkdir();
            fileName = outFolder + "\\" + r.getName() + fileExtentsion;
            
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            
            while (!((serverResponse = cinput.readLine()).equals(eof))){
                pw.println(serverResponse);
                pw.flush();
            }
            
            
                System.out.println("RFC read from another client");
                
                //pw.close();
            //fw.close();
            }else{
                System.out.println("RFC couldnot be read from another client as: "+ (token.tokenizeStatus())[2]);
            }
            
            
            
            
            closeCommand = closeCommand + cr + lf;
            
            //closing connection with the other client
            coutput.println(closeCommand);
            coutput.flush();
            
            coutput.close();
            cinput.close();
            s.close();
            
        }catch(IOException e){
            System.out.println("Error while reading the RFC from the client. " + e);
        }      
        
    }

    //closing the connection with server
    public void close(){
        
        String closeCommand = "";
        String method = "LOGOFF";
        String host = this.hostName;
        String port = Integer.toString(this.portNumber);
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        
        //Constructing close command
        closeCommand = closeCommand + method + sp + host + sp + port + cr + lf;
        
        try{
            //logging off from server
            output.println(closeCommand);
            output.flush();
            
            //serviceSocket.close();
            //myService.close();
            
            output.close();
            input.close();
            //socket.close();
            System.out.println("Logging Off");
        }catch(IOException e){
            System.out.println("Error while closing connection with the sever: "+ e);
        }
        
    }
    
    public static void main(String[] args){
        
        BufferedReader mainBr = new BufferedReader(new InputStreamReader(System.in));
                
        String userFilePath = "", userOutFilePath = "", myip = "";
        String userInput = "";
        String s ="", rfc = "",rfcindex = "";
        int port, cid =0;
        
        try{
            System.out.println(" Enter the input file path: \n");
            userFilePath = mainBr.readLine();
            
            System.out.println(" Enter the output file path: \n");
            userOutFilePath = mainBr.readLine();
            
            System.out.println(" Enter the hostname: \n");
            myip = mainBr.readLine();
            
            System.out.println(" Enter client port number: \n");
            cid = Integer.parseInt(mainBr.readLine());
            
           // mainBr.close();
        }catch(IOException e){
            System.out.println("Error while reading user Input: " + e);
        }
        
        
        MyClient c = new MyClient(userFilePath, userOutFilePath, myip, cid);
        Runnable r1 = new P2P(c);
        Thread t1 = new Thread(r1);
        t1.start();
        //c.addClient();
        while(true){
            try{
            System.out.println(" \n Enter command to call: \n");
            userInput = mainBr.readLine();
            
            if(userInput.equalsIgnoreCase("AddRFC")){
                System.out.println(" Enter rfc name: \n");
                rfc = mainBr.readLine();
                System.out.println(" Enter rfc index: \n");
                rfcindex = mainBr.readLine();
                RFC newrfc = new RFC(rfcindex, rfc); 
                c.addRFC(newrfc);
            }else if(userInput.equalsIgnoreCase("Lookup")){
                System.out.println(" Enter rfc name: \n");
                rfc = mainBr.readLine();
                System.out.println(" Enter rfc index: \n");
                rfcindex = mainBr.readLine();
                RFC lookrfc = new RFC(rfcindex, rfc);
                c.lookupRFC(lookrfc);
            }else if(userInput.equalsIgnoreCase("List")){
                c.listRFCs();
            }else if(userInput.equalsIgnoreCase("get")){
                System.out.println(" Enter hostname: \n");
                s = mainBr.readLine();
                System.out.println(" Enter port: \n");
                port = Integer.parseInt(mainBr.readLine());
                System.out.println(" Enter rfc name: \n");
                rfc = mainBr.readLine();
                System.out.println(" Enter rfc index: \n");
                rfcindex = mainBr.readLine();
                RFC getrfc = new RFC(rfcindex, rfc);
                Socket client = new Socket(s, port);
                c.getRFC(client, getrfc);
            }else if(userInput.equalsIgnoreCase("logoff")){
                c.close();
            }else if(userInput.equalsIgnoreCase("exit")){
                System.exit(0);
            }else{
                System.out.println("Bad Command. Try Again \n");
            }
            
            
           //mainBr.close();
            }catch(IOException e){
                System.out.println("Error while reading user Input: " + e);
            }
        }
    }
}

class P2P implements Runnable{
    
    MyClient mc = null;
    public P2P(MyClient c){
        mc = c;
    }
    
    public void run(){
       try{
            mc.myService = new ServerSocket(mc.getPortNumber());         //Making the client listen to its port
             while(true){
                mc.serviceSocket = mc.myService.accept();                          //Creating a socket connection for another client
                ClientServer conn_c= new ClientServer(mc, mc.serviceSocket);
                new Thread(conn_c).start();
            }
       }catch(Exception e){
            System.out.println(e);
       }
       
    }
}

class ClientServer implements Runnable{
    MyClient cS = null;
    Socket sck = null;
    
    public ClientServer(MyClient c, Socket s){
        cS = c;
        sck = s;
    }
    
    public void run(){
        String method = "Get";
        String closeMethod = "LOGOFF";
        String version = "P2P-CI/1.0";
        String os = "OS: " + cS.getOS();
        String sp = " ";
        String lf = "\n";
        String cr = "\r";
        String eom = "eom";
        int ok = 0, badReq = 1, notFnd = 2, invalidVersion = 3;
        
        Date d1 = new Date();
        DateFormat df= new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz ");      
        String currentDate = "Date: " + df.format(d1);
        
        try{            
            
            
            PrintStream soutput = new PrintStream(sck.getOutputStream());         //creating print stream
            BufferedReader sinput = new BufferedReader(new InputStreamReader(sck.getInputStream())); //creating input stream
            while(true){
            //listening to the other client and verifying his request
            String clientRequest = "";
            clientRequest = sinput.readLine();
            
            if(clientRequest.equals("")){
                continue;
            }
            
            String[] line = clientRequest.split(" ");
            
            String response = "";
            
            int ind = 0;
            boolean rFCExists = false;
            while(ind < cS.getRfc().size()){
                if(line[2].equals(cS.getRfc().get(ind).getIndex())){
                    rFCExists = true;
                    break;
                }ind++;
            }
            
            if(!(method.equalsIgnoreCase(line[0]))){
                response = response + version + sp + cS.status.get(badReq).getCode() + sp + cS.status.get(badReq).getCode() + cr + lf
                    + currentDate + cr + lf + os + cr + lf;
                soutput.println(response);
                soutput.flush();
            }else if(!(rFCExists)){
                response = response + version + sp + cS.status.get(notFnd).getCode() + sp + cS.status.get(notFnd).getCode() + cr + lf
                    + currentDate + cr + lf + os + cr + lf;
                soutput.println(response);
                soutput.flush();
            }else if(!(line[3].equals(version))){
                response = response + version + sp + cS.status.get(invalidVersion).getCode() + sp + cS.status.get(invalidVersion).getCode() + cr + lf
                    + currentDate + cr + lf + os + cr + lf;
                soutput.println(response);
                soutput.flush();
            }else{
                String fileName = cS.filePath + cS.getRfc().get(ind).getName() + cS.fileExtentsion;
                File f = new File(fileName);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String fileSize = "Content-Length: " + f.length();
                String lastModified = "Last-Modified: " + df.format(f.lastModified());
                String contentType = "Content-Type: text/text";
                
                response = response + version + sp + cS.status.get(ok).getCode() + sp + cS.status.get(ok).getStatusMsg() + cr + lf
                    + currentDate + cr + lf + os +lastModified+ cr + lf + fileSize + cr + lf + contentType + cr + lf;
                
                
                
                String data = "";
                while ((data = br.readLine()) != null){
                    response = response + data + cr + lf;
                }
                String eof = "eof";
                response = response + eof;
                
                soutput.println(response);
                soutput.flush();
                //br.close();
                //fr.close();
                
                
            }
            while(!((clientRequest = sinput.readLine()).equalsIgnoreCase(eom)));
            if(closeMethod.equals(sinput.readLine())){
                    sinput.close();
                    soutput.close();
            
            }
            }
           
        }catch(IOException e){
            //System.out.println();
        }
    
    }
}