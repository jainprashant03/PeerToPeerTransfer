package peertopeer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTokenizer{
       
    String str;
    
    //Contructor
    public MyTokenizer(String myString){
        str = myString;
    }
    
    public String[] tokenizeHostPort(){
        String[] myHostPort = new String[2];
        String[] lines = str.split("\\r?\\n");
        //System.out.println(lines[1]);
        //Lets pick the Host name now
        //String[] words = lines[1].split(" ");
        //System.out.println(words[1]);
        Pattern patternHost = Pattern.compile("Host: (.*)");
        Matcher matcherHost = patternHost.matcher(lines[1]);
        if (matcherHost.find()){
            myHostPort[0] = matcherHost.group(1).toString(); 
        }
        else{
            System.out.println("Error! Unable to decode Host in TOKENIZER , please retry");
        }
        //myHostPort[0]=words[1];
        //Lets pick the Port number now
        Pattern patternPort = Pattern.compile("Port: (.*)");
        Matcher matcherPort = patternPort.matcher(lines[2]);
        if (matcherPort.find()){
           myHostPort[1] = matcherPort.group(1).toString();
           //System.out.println(myHostPort[0] + myHostPort[1]);
        }
        else{
            System.out.println("Error! Unable to decode Port in TOKENIZER , please retry");
        }
        return myHostPort;
        }

    public String[] tokenizeRfcTitle(){
        String[] myRfcTitle = new String[2];
        String lines[] = str.split("\\r?\\n");
        //Lets pick the RFC number now
        //Pattern patternRfc = Pattern.compile("(?<=RFC)(.*)(?=P2P-CI/1.0)");
        //Matcher matcherRfc = patternRfc.matcher(lines[0]);
        //if (matcherRfc.find()){
           //myRfcTitle[0] = matcherRfc.group().toString(); 
        //}
        //else{
          //  System.out.println("Error! Unable to decode RFC in ADD , please retry");
        //}
        String[] lines1 = lines[0].split(" ");
        myRfcTitle[0] = lines1[2];
        //Lets pick the Title now
        Pattern patternTitle = Pattern.compile("Title: (.*)");
        Matcher matcherTitle = patternTitle.matcher(lines[3]);
        if (matcherTitle.find()){
           myRfcTitle[1] = matcherTitle.group(1).toString(); 
        }
        else{
            System.out.println("Error! Unable to decode Title in TOKENIZER , please retry");
        }
        return myRfcTitle;
        }
    
    public String[] tokenizeStatus(){
        String[] part = str.split(" ");
        
        return part;
    }
    
//    //This pseudo main is for debug only
//   public static void main(String[] args) {
//        // TODO code application logic here
//    
//        String sp = " ";
//        String lf = "\n";
//        String cr = "\r";
//        String teststring = "ADD" + sp + "RFC" + sp + "1234" + sp + "2P-CI/1.0" + cr + lf
//                    + "Host:" + sp + "my.host" + cr + lf + "Port:" + sp + "5678" + cr + lf + "Title:" + sp + "A Proferred Official ICP" + cr + lf;
//        //System.out.print(teststring);
//        MyTokenizer a = new MyTokenizer(teststring);
//        String[] hostport = a.tokenizeHostPort();
//        System.out.println("Printing host: "+hostport[0]);
//        System.out.println("Printing port: "+hostport[1]);
//        String[] rfctitle = a.tokenizeRfcTitle();
//        System.out.println("Printing rfc: "+rfctitle[0]);
//        System.out.println("Printing title: "+rfctitle[1]);
//    }

}
