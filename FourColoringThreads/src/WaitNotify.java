    public class WaitNotify {
    
      protected Integer progress;
   	
       public WaitNotify(){
         progress = 0;
      }
   
       public synchronized void waitForNotification() {
         try { 
            wait();
         } 
             catch (Exception e){
               System.err.println("***** ERROR: " + e);
            }
      }
   	
       public synchronized void  notification() {
         notifyAll();
      }
   	
       public void madeProgress(){
         this.progress++;
      }
   	
       public Integer currentProgress(){
         return progress;
      }
   	
   }