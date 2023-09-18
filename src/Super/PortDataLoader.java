package Super;

import java.io.IOException;
import java.util.List;

public class PortDataLoader implements PortLoader {

    public static void main(String[] args) {
        try {
            PortLoader loader = new PortDataLoader();
            List<Port> ports = loader.loadAllPorts();

            for (Port port : ports) {
                System.out.println("Port Name: " + port.getName());
                System.out.println("Port Number: " + port.getPortNum());
                System.out.println("Storing Capacity: " + port.getStoringCapacity());
                System.out.println("Latitude: " + port.getLatitude());
                System.out.println("Longitude: " + port.getLongtitude());
                System.out.println("Landing Ability: " + port.getLandingAbility());
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
