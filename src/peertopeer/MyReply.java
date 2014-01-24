package peertopeer;

import java.util.Iterator;
import java.util.LinkedList;
import static peertopeer.MyServer.linkB;

public class MyReply {
    String myVersion = "P2P-CI/1.0";
    String[] myStatusCode = {"200 OK","400 Bad Request","404 Not Found","505 P2P-CI Version Not Supported"};
    String sp = " ";
    String lf = "\n";
    String cr = "\r";
   
    
    public String addReply(String[] myStr){
        String rfcNumber = myStr[0];
        String rfcTitle = myStr[1];
        String hostName = myStr[2];
        String portNumber = myStr[3];
        String[] addStr = myStr;
        String addString;
        addString = myVersion + sp + myStatusCode[0] + cr + lf + "RFC" + sp + rfcNumber + sp + rfcTitle + sp + hostName + sp + portNumber + cr + lf;
        return addString;
    } 
    
    public String notFound(String rfc, String title, String host, String port){
        String notFoundString = myVersion + sp + myStatusCode[2] + cr + lf + "RFC" + sp + rfc + sp + title + sp + host + sp + port + cr + lf;
        return notFoundString;
    }
    
    public String sendLookup(LinkedList mylookuplist){
        String lookupString = myVersion + sp + myStatusCode[0] + cr + lf;
        Iterator<MyLinkedListB> itr = mylookuplist.iterator();
        while (itr.hasNext()) {
            MyLinkedListB element = itr.next();
            lookupString = lookupString + "RFC" + sp + element.rfcNumber + sp + element.rfcTitle + sp + element.hostName + sp + element.hostPort+ cr + lf;
        }
        return lookupString;
    }
    
    public String notCorrect(String rfc, String title, String host, String port){
        String notCorrectString = myVersion + sp + myStatusCode[1] + cr + lf + "RFC" + sp + rfc + sp + title + sp + host + sp + port + cr + lf;
        return notCorrectString;
    }
    
    public String sendList(LinkedList mylookuplist){
        String lookupString = myVersion + sp + myStatusCode[0] + cr + lf;
        Iterator<MyLinkedListB> itr = mylookuplist.iterator();
        while (itr.hasNext()) {
            MyLinkedListB element = itr.next();
            lookupString = lookupString + "RFC" + sp + element.rfcNumber + sp + element.rfcTitle + sp + element.hostName + sp + element.hostPort+ cr + lf;
        }
        return lookupString;
    }
    
}
