   import java.io.*;
   import java.util.*;

    public class NodeInfo {
   
      protected String name;
      protected ArrayList<NodeInfo> Neighbor;
      protected Boolean change;
      protected Integer color; // 0 = not colored, 1-4 = colors
      public Boolean Terminate;
      public Random r = new Random(); // used to select a color
      public NodeProcess node;
      public Integer cycle = 0;
      public Boolean working = false;
   	
       public NodeInfo(String name){
         this.Neighbor = new ArrayList<NodeInfo>();
         this.name = name;
      }
   	
       public String getName(){
         return this.name;
      }
   	
       public void setflagUp(){
         this.change = true;
      }
   	
       public void setflagDown(){
         this.change = false;
      }
   	
       public Boolean getFlag(){
         return this.change;
      }
   	
       public void addNeighbor(NodeInfo n){
         Neighbor.add(n);
      }
   	
       public NodeInfo getNeighbor(Integer i){
         return Neighbor.get(i);
      }
   	
       public Integer noOfNeighbors(){
         return this.Neighbor.size();
      }
   	
       protected void setColor(Integer i){
         this.color = i;
      }
   	
       public Integer getColor(){
         return this.color;
      }
   	
       public Boolean[] availableColors(){
         Boolean[] avail = new Boolean[5];
         for(int i=0; i<5; i++) 
            avail[i] = true;
         for(int i=0; i<this.Neighbor.size(); i++)
            avail[this.Neighbor.get(i).getColor()] = false; // set false if neighbor has this color
         return avail; 
      }
   	
       public Integer numberAvailable(Boolean[] a){
         Integer count = 0;
         for(int i=1; i<5; i++){
            if(a[i]) count++; // add 1 to count if color i is available
         }
         return count;
      }
   	
       public Boolean validColoring(){ // return false if a neighbor has this color
         Boolean ans = true;
         for(int i=0; i<this.Neighbor.size(); i++)
            if(this.getColor() == this.Neighbor.get(i).getColor())
               return false;
         return ans;
      }
   	
       public Integer pickColor(){
         Boolean[] avail = availableColors();
         Integer tot = numberAvailable(avail);
         if(tot == 0) 
            return r.nextInt(4)+1; // No color available, make a random pick
         else {
            Integer no = r.nextInt(tot);
            Integer current = no;
            for(int i = 1; i<5; i++){ // randomly select an available color
               if(avail[i]){
                  if(current == 0)
                     return i;
                  else
                     current--;
               }
            }
         }
         assert true: name+" Random selection error"; // algorithm error - fix it
         return 0;
      }
   	
   }