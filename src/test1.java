import java.io.*;
import java.util.*;

public class test1{

public static void main(String args[]) {        
    try {
        File input = new File("C:\\Users\\PRASHANT\\IPProject\\RFCs\\HostSoftware.txt");
        File output = new File("C:\\Users\\PRASHANT\\IPProject\\RFCsRead\\HostSoftware.txt");
        Scanner sc = new Scanner(input);
        PrintWriter printer = new PrintWriter(output);
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            printer.write(s);
        }
    }
    catch(FileNotFoundException e) {
        System.err.println("File not found. Please scan in new file.");
    }
}
}