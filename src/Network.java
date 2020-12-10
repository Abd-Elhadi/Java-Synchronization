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
        int cnt = 1;
        while(numOfDev-- >0){
        	System.out.print("Device "+ (cnt) + ": ");
        	deviceInfo = input.nextLine();
			infoSplit = deviceInfo.split(" ");
            Device d = new Device(infoSplit[0], infoSplit[1], router);
            devices.add(d);
            cnt++;
        }
        for(Device d:devices){
            d.start();
        }
    }
}