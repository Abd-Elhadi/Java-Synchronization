import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String deviceInfo;
		String [] infoSplit;
        
        System.out.println("What is number of WI-FI Connections?");

        int numberOfDevices = input.nextInt();

        Router router = new Router(numberOfDevices);
        System.out.println("What is number of devices Clients want to connect?");
        int numOfDev = input.nextInt();

        input.nextLine();
        ArrayList<Device> devices = new ArrayList<>();
        for (int i = 0; i < numOfDev; i++) {
        	System.out.print("Device "+ (i + 1) + ": ");
        	deviceInfo = input.nextLine();
			infoSplit = deviceInfo.split(" ");
			Device newDevice = new Device(router);
			newDevice.setDeviceName(infoSplit[0]);
			newDevice.setDeviceType(infoSplit[1]);
            devices.add(newDevice);
        }
        for(Device newDevice:devices){
            newDevice.start();
        }
    }
}