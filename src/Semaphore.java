public class Semaphore {
	
    protected int numberOfConnection = 0 ;

    protected Semaphore() { numberOfConnection = 0 ; }

    protected Semaphore(int initial) { 
    	numberOfConnection = initial ; 
    }
    
    synchronized void connect() {
    	numberOfConnection-- ;
        if (numberOfConnection < 0)
            try {
                wait();
            } catch(  InterruptedException e ) { }
    }
    
    public synchronized void release() {
    	numberOfConnection++ ; 
    	if (numberOfConnection <= 0) 
    		notify();
    }
}