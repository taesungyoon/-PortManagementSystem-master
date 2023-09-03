import Port1.*;
import Super.Container;
import Super.Trip;
import Port1.Port1Trip;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;

public class PortManager {
    private Scanner scanner;

    public PortManager() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        // Using a HashMap to store port information
        Map<String, Port> portMap;
        portMap = new HashMap<>();
        portMap.put("Ho Chi Minh City", new Port("Ho Chi Minh City", 10.7688, 106.6958));
        portMap.put("Da Nang", new Port("Da Nang", 16.0544, 108.2022));
        portMap.put("Haiphong", new Port("Haiphong", 20.9500, 107.0733));
        portMap.put("Phu Quoc", new Port("Phu Quoc", 10.3792, 105.4434));
        portMap.put("Halong Bay", new Port("Halong Bay", 20.9100, 107.1833));

        // Input for Departure Port
        System.out.println("Enter the Departure: ");
        String departurePortName = scanner.nextLine();

        // Input for Arrival Port
        System.out.println("Enter the Arrival: ");
        String arrivalPortName = scanner.nextLine();

        // Retrieve the corresponding port objects based on the entered port names.
        Port departurePort = portMap.get(departurePortName);
        Port arrivalPort = portMap.get(arrivalPortName);

        if (departurePort != null && arrivalPort != null) {
            // Create an instance of the operation processor
            OperationProcessor processor = new DistanceCalculator();
            double distance = processor.performOperation(departurePort, arrivalPort);
            System.out.printf("The distance from %s to %s is %.3f km%n", departurePort.name(), arrivalPort.name(), distance);
        } else {
            System.out.println("The entered port name is not valid.");
        }
    }

    public void closeScanner() {
        scanner.close();
    }


        public static void main(String[] args) throws IOException {
            Port1Container port1Container = new Port1Container("C-001","dry storage",500.0);

            // Distance Calculate
            PortManager manager = new PortManager();
            manager.start();
            manager.closeScanner();


            // Port1 객체 생성 및 테스트
            Port1 port1 = new Port1("호치민 항구", "p0001", 10.7688, 106.6958, 1000.0, true);

            // Port1Container 객체 생성 및 테스트
            Port1Container container1 = new Port1Container("C-001", "dry storage", 500.0);
            Port1Container container2 = new Port1Container("C-002", "liquid", 800.0);

            // Port1Ship 객체 생성 및 테스트
            Port1Ship ship1 = new Port1Ship("S-001", 2000.0);
            ship1.loadContainer(container1);
            ship1.loadContainer(container2);

            // Port1Trip 객체 생성 및 테스트
            Port1Trip trip1 = new Port1Trip("T-001", LocalDate.now(), LocalDate.now().plusDays(7), ship1, port1, port1);

            // Port1Truck 객체 생성 및 테스트
            Port1Truck truck1 = new Port1Truck("T-001", 500.0);
            truck1.loadContainer(container1);
//
//             User 객체 생성 및 테스트
            User user1 = new User("Admin", "admin_user", "admin_password");
            User user2 = new User("PortManager", "manager_user", "manager_password");

//             사용자 정보 로드 및 출력
            user1.loadAllUsers();
            user2.loadAllUsers();

            // 모든 항구 파일 출력
            port1.printAllVehicles();

            // 모든 여행 내용 출력
            user1.ListAllTrips();
        }
    }
