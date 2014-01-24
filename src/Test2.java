/*
 * Test2.java
 *
 * Created on October 18, 2013, 2:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author PRASHANT
 */
public class Test2{
     
    static class RFC{
        String index;
        String name;
        public RFC(String a, String b){
            index = a;
            name = b;
        }
        public String toString(){
            return (index + " : " + name);
        }
    }
    static LinkedList <RFC> l1 = new  LinkedList<RFC> ();
    static{
      RFC r1 = new RFC("0001", "HostSoftware");
      RFC r2 = new RFC("0002", "Host software" );
      l1.add(r1);
      l1.add(r2);           
    }   
    public static void main(String args[]){
       Test2 t21 = new Test2();
       for(int i = 0; i < t21.l1.size(); i++){
           System.out.println(t21.l1.get(i));
       }
    }
}
  
