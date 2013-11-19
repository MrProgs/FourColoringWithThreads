   import java.io.*;
import java.util.*;

import scranton.visual.*;

    public class Monitor extends Thread{
    	 // VJFrame frame;
    	  //JMap J;
      Hashtable<String, NodeInfo> H;
      Boolean terminate;
      String startNode;
      WaitNotify synch;
      Grapher G;
      Random r;
   
       public Monitor(String fileName) throws IOException{
    	   	// frame = new VJFrame("US Map via Threads");
    	   	 //J = new JMap("USMap0.txt");
         G = new Grapher(fileName);
         this.terminate = false;
         this.H = G.refTable();
         this.startNode = startNode;
         this.r = new Random();
      	
         for (Enumeration<String> E = this.H.keys(); E.hasMoreElements(); ){
            String name = E.nextElement();
            // System.out.println("Initialize "+G.refNode(name).getName());
            NodeProcess np = new NodeProcess(G.refNode(name));
            np.start();
         }
      }
   	
       public Boolean stillWorking(){
         for (Enumeration<NodeInfo> E = this.H.elements(); E.hasMoreElements(); ){
            NodeInfo n = E.nextElement();
            if(n.getFlag() || n.working) 
               return true;  // as long as one flag is up the nodes are still processing.
         }
         return false; // All flags down implies the process is done.
      }
   	
       public void run(){
      
       	/*  One state start */
         NodeInfo st = G.refNode("WA");
         st.setflagUp();
			st.node.notifyNode();
          st = G.refNode("ME");
         st.setflagUp();
			st.node.notifyNode();
          st = G.refNode("FL");
         st.setflagUp();
			st.node.notifyNode();
          st = G.refNode("CA");
      	// Start the process
         st.setflagUp();
			st.node.notifyNode();
      
         // System.out.println(st.getName()+" "+st.getFlag());
      	/* */
      	
      	/* Explosion start 
         for (Enumeration<NodeInfo> E = this.H.elements(); E.hasMoreElements(); ){
            NodeInfo n = E.nextElement();
            n.setflagUp();
            n.node.notifyNode();
         }
      	/* */
      	
         while(!this.terminate){
            try {
               wait(500);
            } 
                catch (Exception e){}
            //  System.out.println("** Monitor");
            if(stillWorking()){
               // System.out.print("*** Monitor: still working\n");
            } 
            else {
               System.out.println("==== All flags down.");
               for (Enumeration<NodeInfo> E = this.H.elements(); E.hasMoreElements(); ){
                  NodeInfo n = E.nextElement();
                  n.Terminate = true;
                  n.node.notifyNode();
               }
               this.terminate = true;
            }
         }
         // display solution.
			Integer HitCount = 0;
         for (Enumeration<NodeInfo> E = this.H.elements(); E.hasMoreElements(); ){
            NodeInfo N = E.nextElement();
            System.out.print(N.getName()+"["+N.cycle+"] "+N.getColor()+" ");
				HitCount += N.cycle;
            for(int i = 0; i < N.Neighbor.size(); i++){
               System.out.print(N.Neighbor.get(i).getColor());
            }
            System.out.print("\t"+N.validColoring()+"\n");
         }
      	System.out.print("Total Cycles = "+HitCount+"\n");
      }
   	
       public Integer randomColor(){
         return r.nextInt(4)+1;
      }
   
   }