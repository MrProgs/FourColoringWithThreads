   import java.io.*;
   import java.util.*;

    public class NodeProcess extends Thread{
   
      NodeInfo info;
   
       public NodeProcess(NodeInfo n)throws IOException{
      
         this.info = n; // access to NodeInfo
         this.info.node = this; // access to thread via node information
         this.info.setflagDown(); // wake up flag off (down)
         this.info.setColor(0); // set node as not colored
         this.info.Terminate = false; 
      }
   	
       public synchronized void waitForNotification(){
         while((!this.info.Terminate)&&(!info.getFlag())){
            try{
               wait();
            } 
                catch (Exception e){}
            	// System.out.println(info.getName()+" "+Terminate+" "+info.flagIsUp());
         }
      }
   	
       public synchronized void  notifyNode(){
         notify();
      }
   	
       public void run(){
         // System.out.println(info.getName()+ " started");
         while(!this.info.Terminate){
            waitForNotification();
            info.working = true;
            info.setflagDown();
            if(!this.info.Terminate){ // color node
               info.cycle++;
               info.setColor(info.pickColor());  // select a color
               for(int i = 0; i < info.Neighbor.size(); i++){
                  if((info.Neighbor.get(i).getColor() == 0)
                  	|| (info.Neighbor.get(i).getColor() == this.info.getColor())){
                     info.Neighbor.get(i).setflagUp();
                     info.Neighbor.get(i).node.notifyNode();  // wake up neighbor
                     System.out.print("-->>"+info.getName()+"\t"+info.Neighbor.get(i).getName()+"\n");
                  }
               }
               System.out.print(info.getName()+"["+info.cycle+"]\t"+info.getColor()+"\n");
            } 
            info.working = false;
         }
         // System.out.println(info.getName()+ " terminating");
      }
   
   }