import java.util.Random;

public class Device extends Thread {
    private String deviceName, deviceType;
    Router router;
    int numberOfConnection;
    Random sleepTime = new Random();
    
    
    Device(){
    	numberOfConnection = 0;
    }
    Device(Router router){
        this.router = router;
        numberOfConnection =  router.getConnections();
    }
    
    public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Override
	public void run() {
		connect();
		router.addConnection(this);
		//System.out.println();
		try {
			Thread.sleep(sleepTime.nextInt(1000 - 100 + 1) + 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		activity();
		try {
			Thread.sleep(sleepTime.nextInt(1000 - 100 + 1) + 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exit();
		router.logOut(this);
	}
	private void connect() {
		System.out.print(getDeviceName() + " (" + this.getType() + ") arrived\n");
	}
	private void activity() {
		System.out.println("Connection " + getNumberOfConnection() + ": "+ getDeviceName() + " performs online activity");
	}
	private void exit() {
		System.out.println("Connection " + getNumberOfConnection() + ": "+ getDeviceName() + " Logged out");
	}
	public String getType() {
		return deviceType;
	}
	public void setType(String type) {
		this.deviceType = type;
	}

	public void setNumberOfConnection(int numberOfConnection) {
		this.numberOfConnection = numberOfConnection;
	}
	
	public int getNumberOfConnection() {
		return numberOfConnection;
	}
}