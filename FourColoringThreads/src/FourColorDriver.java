   import java.io.*;
import java.util.*;

import scranton.visual.JMap;

    class FourColorDriver {
/**    
       public static void startXing(PathInfo P) throws IOException{
         System.out.print("Enter state codes separated by a tab");
         BufferedReader Buffer = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer T = new StringTokenizer(Buffer.readLine());
         P.setStart(T.nextToken().toUpperCase());
         P.setStop(T.nextToken().toUpperCase());
         P.setAccess();
         System.out.println(P.getStart()+"\t"+P.getStop());
         P.start();
      }
*/   
       public static void main(String[] argv) throws IOException{
		   WaitNotify synch = new WaitNotify();
		   JMap J = new JMap("USMap0.txt");
		 	Monitor M = new Monitor("usa.txt");
			M.start();
      }
   
   }