import java.util.Random;

public class Device extends Thread {
    private String deviceName, deviceType;
    Router router;
    int numberOfConnection;
    Device(){}
    Device(String name, String type, Router router){
        this.deviceName = name; 
        this.deviceType = type;
        this.router = router;
    }
    
    @Override
    public void run(){
    	router.addConnection(this);
        //numberOfConnection = router.addConnection(this);
    	numberOfConnection = router.getConnections();
        Random random = new Random();
        int onlineTime = random.nextInt();
        // make sure it's a positive number
        if(onlineTime < 0) onlineTime *= -1;
        onlineTime %= 5000;
        try {
            Thread.sleep(onlineTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        router.logOut(this);
    }
	public String getDeviceName() {
		return deviceName;
	}

	public void setNumberOfConnection(int numberOfConnection) {
		this.numberOfConnection = numberOfConnection;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public int getNumberOfConnection() {
		return numberOfConnection;
	}
}