import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Router {
    private Semaphore mySemaphore;
    private Queue<Integer> connectionsQueue;
    
    //object will be used to avoid conflict in connections
    // it'll be used to achieve synchronization 
    private final Object object = new Object();

    Router(int countingSemaphore){
        mySemaphore = new Semaphore(countingSemaphore);
        connectionsQueue = new LinkedList<>();
        for(int i = 1 ; i <= countingSemaphore ; i++){
            connectionsQueue.offer(i);
        }
    }
    void addConnection(Device device){
        BufferedWriter file = null;
        synchronized (object){
            try {
                file = new BufferedWriter(new FileWriter("outputfile.txt", true));
                file.write(device.getDeviceName() + " (" + device.getDeviceType() + ")" + " arrived\n");
                //System.out.println(device.getDeviceName() + " (" + device.getDeviceType() + ")" + " arrived");
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
        mySemaphore.connect();
        int connectedDevices = connectionsQueue.size();
        //device.getNumberOfConnection()
        
        synchronized (object) {
            //connectedDevices = connectionsQueue.poll();
            System.out.println("Connection " + connectedDevices + ": " + device.getDeviceName() + " Occupied");
            //System.out.println("Connection " + connectedDevices + ": " + device.getDeviceName() + " Performs online activity");
            
            try {
                file.write("Connection " + connectedDevices + ": " + device.getDeviceName() + " Occupied\n");
                file.write("Connection " + connectedDevices + ": " + device.getDeviceName() + " Performs online activity\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void logOut (Device device){
        synchronized(object){
            mySemaphore.release();
            int connections = device.getNumberOfConnection();
            connectionsQueue.offer(device.getNumberOfConnection());

            //System.out.println("Connection "+connections+": "+device.getDeviceName()+" Logged out");

            BufferedWriter file = null;
            try {
                file = new BufferedWriter(new FileWriter("outputfile.txt", true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.write("Connection "+connections+": "+device.getDeviceName()+" Logged out\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    int getConnections () {
    	return connectionsQueue.size();
    }
}