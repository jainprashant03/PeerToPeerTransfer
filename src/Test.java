import java.io.*;
import java.text.*;

public class Test {
    
    public static void main (String args[]){
        try{
            FileReader fr = new FileReader("C:\\Users\\PRASHANT\\IPProject\\RFCs\\HostSoftware.txt");
            BufferedReader br = new BufferedReader(fr);
                       
            File f = new File("C:\\Users\\PRASHANT\\IPProject\\RFCsRead\\HostSoftware.txt");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            
            String s;
            while ((s = br.readLine()) != null){
                pw.println(s);
            }
            System.out.println(f.length()); 
            DateFormat df= new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz ");
            System.out.println(df.format(f.lastModified()));
        }catch (Exception e){
            System.out.println("Unexpected Exception : " + e);
        }
    }
    
}
