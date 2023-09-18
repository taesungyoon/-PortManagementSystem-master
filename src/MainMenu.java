import Super.*;
import UserInterface.Users.Admin;
import UserInterface.Users.PortManager.PortManager;
import UserInterface.Users.User;
import UserInterface.login;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.time.LocalDate;
import java.util.Scanner;

public class MainMenu implements PortLoader {

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Welcome to port management system interface");
        initalization();

    }

    // Admin 메뉴
    private static void initalization() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while(true){

            System.out.println("1. Login");
            System.out.println("e. 종료");
            System.out.print("선택: ");
            String roleChoice = scanner.next();
            switch (roleChoice) {
                case "1":
                    loginMenu();
                case "e":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            }

        }
    }
    private static void loginMenu() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        login login = new login();
        String password;
        String username;
        int Count = 0;
        while(true){
            if(Count>=5){
                System.out.println("시도 횟수를 초과했습니다. 프로그램을 종료합니다.");
                System.exit(0);
            }
            System.out.println("역할을 선택하세요:");
            System.out.println("1. Admin");
            System.out.println("2. Port Manager");
            System.out.println("e. 종료");
            System.out.print("선택: ");
            String roleChoice = scanner.nextLine();
            switch (roleChoice) {
                case "1":
                    System.out.println("Enter your username.");
                    System.out.print("선택: ");
                    username = scanner.nextLine();
                    System.out.println("Enter your password.");
                    System.out.print("선택: ");
                    password = scanner.nextLine();
                    if(login.Validation("Admin",username,password)){
                        login login1 = new login("Admin",username,password);
                        adminMenu(login1);
                    }else{
                        System.out.println("Your username or password is incorrect");
                        System.out.println("If count is more than 5 times. System will be closed");
                        Count +=1;
                        switch (Count) {
                            case 1:
                                System.out.printf("You have failed to log in %d time%n", Count);
                                break;
                            default:
                                System.out.printf("You have failed to log in %d times%n", Count);
                        }
                        System.out.printf("Your left trial is %d%n%n", 5 - Count);
                        break;
                    }
                case "2":
                    System.out.println("Enter your username.");
                    System.out.print("선택: ");
                    username = scanner.nextLine();
                    System.out.println("Enter your password.");
                    System.out.print("선택: ");
                    password = scanner.nextLine();
                    if(login.Validation("PortManager",username,password)){
                        login login1 = new login("PortManager",username,password);
                        portManagerTaskMenu(scanner,login1.getPortNum(),login1);
                    }else{
                        System.out.println("Your username or password is incorrect");
                        System.out.println("If count is more than 5 times. System will be closed");
                        switch (Count) {
                            case 1:
                                System.out.printf("You have failed to log in %d time%n", Count);
                                break;
                            default:
                                System.out.printf("You have failed to log in %d times%n", Count);
                        }
                        System.out.printf("Your left trial is %d%n%n", 5 - Count);
                        break;
                    }
                case "e":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            }
        }
    }
    private static void adminMenu(login login) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Choose the menu:");
            System.out.println("1. Admin");
            System.out.println("2. Port Manager");
            System.out.println("e. 종료");
            System.out.print("선택: ");
            String roleChoice = scanner.nextLine();
            switch (roleChoice) {
                case "1":
                    adminTaskMenu(scanner, login);
                case "2":
                    adminPortManagerTaskMenu(scanner, login);
                case "e":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            }}}
    private static void adminPortManagerTaskMenu(Scanner scanner, login login) throws IOException, ClassNotFoundException {

        System.out.println("아래는 포트 리스트입니다.");
        while (true){
            System.out.println("1. Port1 ");
            System.out.println("2. Port2 ");
            System.out.println("3. Port3 ");
            System.out.println("4. Port4 ");
            System.out.println("5. Port5 ");
            System.out.println("0. Exit ");
            System.out.println("엑세스할 포트를 선택하세요: ");

            int adminChoice = 0;

            if (scanner.hasNextInt()) {
                adminChoice = scanner.nextInt();
                scanner.nextLine(); // 줄 바꿈 문자 처리
            } else {
                // 숫자가 아닌 입력 처리
                scanner.nextLine(); // 잘못된 입력을 제거하기 위해 사용
                System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                continue;
            }

            switch (adminChoice) {

                case 1:
                    portManagerTaskMenu(scanner,"Port1",login);
                    return;
                case 2:
                    portManagerTaskMenu(scanner,"Port2",login);
                    return;
                case 3:
                    portManagerTaskMenu(scanner,"Port3",login);
                    return;
                case 4:
                    portManagerTaskMenu(scanner,"Port4",login);
                    return;
                case 5:
                    portManagerTaskMenu(scanner,"Port5",login);
                    return;
                case 0: // 0을 선택할 때 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            }
        }


    }
    private static void loadContainers(Scanner scanner, String PortNum) throws IOException {
        User user = new User();
        System.out.println("Please Type the number of container id");
        String containerNum = scanner.next();
        System.out.println("Please Type the number of vehicle id");
        String vehicleID = scanner.next();
        System.out.println("Please Type the number of vehicle type");
        String type = scanner.next();

        Container container = user.searchingContainer(PortNum,"C-"+containerNum);
        if(type.equals("Ship")){
            Ship ship = user.searchingShip(PortNum,"Sh-"+vehicleID);
            ship.loadContainer(container);
            System.out.println(ship);
        } else if (type.equals("Truck")) {
            Truck truck = user.searchingTruck(PortNum,"Tr-"+vehicleID);
            truck.loadContainer(container);
            System.out.println(truck);
        }
    } // 테스트 완료
    private static void unloadContainers(Scanner scanner,String PortNum) throws IOException {
        User user = new User();

        System.out.println("Please Type the number of vehicle id");
        String vehicleID = scanner.nextLine();
        System.out.println("Please Type the number of vehicle type");
        String type = scanner.nextLine();

        System.out.println("Please Type the number of container id");
        String containerNum = scanner.next();

        Container container = user.searchingContainer(PortNum,"C-"+containerNum);
        if (container != null) {
            // container가 null이 아닌 경우에만 작업을 수행
            if (type.equals("Ship")) {
                Ship ship = user.searchingShip(PortNum, "Sh-"+vehicleID);
                if (ship != null) {
                    ship.unloadContainer(container);
                } else {
                    // ship이 null인 경우 처리
                    System.out.println("Ship not found.");
                }
            } else if (type.equals("Truck")) {
                Truck truck = user.searchingTruck(PortNum, "Tr-"+vehicleID);
                if (truck != null) {
                    truck.unloadContainer(container);
                } else {
                    // truck이 null인 경우 처리
                    System.out.println("Truck not found.");
                }
            }
        } else {
            // container가 null인 경우 처리
            System.out.println("Container not found.");
        }
    } // 테스트 완료
    private static void listTripsInDate(Scanner scanner,login login, String PortNum) throws IOException, ClassNotFoundException {
        PortManager portManager = new PortManager();
        System.out.println("Type the departure date month");
        int ddm1 = scanner.nextInt();
        System.out.println("Type the departure date day");
        int ddd1 = scanner.nextInt();
        String departureDate = "2023-"+ddm1+"-"+ddd1;

        System.out.println("Type the arrival date month");
        int adm1 = scanner.nextInt();
        System.out.println("Type the arrival date day");
        int add1 = scanner.nextInt();
        String arrivalDate = "2023-"+adm1+"-"+add1;


        System.out.println("조건부로 History를 불러옵니다.");
        portManager.ListTripsinSpecificDays(departureDate,arrivalDate);
        portManagerTaskMenu(scanner,PortNum, login);
    }

    private static void portManagerTaskMenu(Scanner scanner,String PortNum, login login) throws IOException, ClassNotFoundException {
        while (true){
            User user = new User();
            PortManager portManager = new PortManager();
            System.out.println("Port Manager 메뉴:");
            System.out.println("ac. Container Vehicle 넣기");
            System.out.println("dc. Container Vehicle 빼기");
            System.out.println("r. Vehicle refuel하기");
            System.out.println("v. 모든 Vehicle 리스트");
            System.out.println("t. 모든 Truck 리스트");
            System.out.println("as. 모든 Ship 리스트");
            System.out.println("h. History 불러오기 (현재 기준 7일)");
            System.out.println("td 오늘이 포함되어있는 Trip 불러오기");
            System.out.println("q. 조건부 History 불러오기");
            System.out.println("tw. Type별로 무게 불러오기");
            System.out.println("aw. 모든 컨테이너들 무게 불러오기");
            System.out.println("s. 현재 남아있는 storage 출력하기");
            System.out.println("df. 하루에 사용하는 연료량 불러오기");
            System.out.println("tr. Trip 생성하기(History에 관련내용이 저장됩니다.)");
            System.out.println("b. 뒤로 가기");
            System.out.print("선택: ");
            String portManagerChoice = scanner.next();

            switch (portManagerChoice) {
                case "ac":
                    loadContainers(scanner,PortNum);
                    // Container Vehicle에 넣기 기능 구현
                    System.out.println("Container를 Vehicle에 넣습니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "dc":
                    unloadContainers(scanner,PortNum);
                    // Container Vehicle에서 빼기 기능 구현
                    System.out.println("Container를 Vehicle에서 뺍니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "r":
                    // Trip 관리 기능 구현
                    System.out.println("Trip을 관리합니다.");
                    portManagerTaskMenu(scanner,PortNum, login);

                    break;
                case "v":
                    portManager.listAllVehicle(PortNum);
                    System.out.println("모든 Vehicle 리스트를 불러옵니다.");
                    portManagerTaskMenu(scanner,PortNum, login);

                    break;
                case "t":
                    portManager.listAllTruck(PortNum);
                    System.out.println("모든 Truck 리스트를 불러옵니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "as":
                    portManager.listAllShip(PortNum);
                    System.out.println("Ship 리스트를 불러옵니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "h":
                    portManager.ListTripsin7Days(PortNum);
                    System.out.println("7일 이내의 History를 불러옵니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "td":
                    portManagerTaskMenu(scanner,PortNum, login);
                case "q":
                    listTripsInDate(scanner,login,PortNum);
                    break;
                case "tw":
                    System.out.println("Type별로 컨테이너 무게 총합을 불러옵니다.");
                    user.weightCalculate(PortNum);
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "aw":
                    Port port = new Port();
                    port.TotalWeightCalculate(PortNum);
                    System.out.println("모든 컨테이너들의 무게 총합을 불러옵니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "s":
                    Port port1 = new Port();
                    double remainStorage =user.TotalWeightCalculate(PortNum) - port1.TotalWeightCalculate(PortNum); // 오류, 고쳐야됨
                    System.out.println("현재 남아있는 총량은 "+remainStorage+" 입니다.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "tr":
                    System.out.println("Type the number of Trip ID");
                    int tripID = scanner.nextInt();
                    System.out.println("Type the departure date month");
                    int ddm2 = scanner.nextInt();
                    System.out.println("Type the departure date day");
                    int ddd2 = scanner.nextInt();
                    System.out.println("Type the arrival date month");
                    int adm2 = scanner.nextInt();
                    System.out.println("Type the arrival date day");
                    int add2 = scanner.nextInt();

                    System.out.println("Type the type of Vehicle. Departure port should have this vehicle. 1.Truck 2.Ship");
                    int Type = scanner.nextInt();
                    String T;
                    if(Type == 1){
                        T = "Truck";
                    }else{
                        T= "Ship";
                    }
                    System.out.println("Type the number of Vehicle ID. Departure port should have this vehicle.");
                    String VehicleIDNumber = scanner.next();
                    System.out.println("Type the departure port number");
                    String departueIDNumber = scanner.next();
                    System.out.println("Type the arrival port number");
                    String arrivalIDNumber = scanner.next();

                    LocalDate departureDate2 = portManager.StringToDate("2023-"+ddm2+"-"+ddd2);
                    LocalDate arrivalDate2 = portManager.StringToDate("2023-"+adm2+"-"+add2);


                    Vehicle vehicle = portManager.searchVehicle("P-"+departueIDNumber,T,VehicleIDNumber);
                    Trip trip = new Trip("Tr-"+tripID,departureDate2,arrivalDate2,T,vehicle,"Port"+departueIDNumber,"Port"+arrivalIDNumber);
                    portManagerTaskMenu(scanner,PortNum, login);
                case "b":
                    // 뒤로 가기 역할에 따라 다르게 나옴.
                    if(login.getRole().equals("Admin")){
                        adminMenu(login);
                    }else{
                        main(null);
                    }
                    break;
                case"df":
                    break;
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            }
        }
    }



    private static void adminTaskMenu(Scanner scanner,login login) throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        System.out.println("Admin 메뉴");
        System.out.println("1. Adding (Port, Container, Truck, Ship 등 생성)");
        System.out.println("2. Delete (Port, Container, Truck, Ship, user 등 제거)");
        System.out.println("3. 모든 Port 불러오기");
        System.out.println("4. 회원가입하기");
        System.out.println("5. 모든 유저 불러오기");
        System.out.println("6. 모든 port manager 불러오기");
        System.out.println("7. 모든 admin 불러오기");
        System.out.println("8. 뒤로 가기");
        System.out.print("선택: ");
        int adminChoice = scanner.nextInt();

        switch (adminChoice) {
            case 1:
                addingMenu(scanner,login);
                break;
            case 2:
                deletingMenu(scanner,login);
                break;
            case 3:
                try {
                    System.out.println(admin.loadAllPorts());
                } catch (ClassNotFoundException e) {
                    System.out.println("There is no ports in this system");
                    throw new RuntimeException(e);
                }

                break;
            case 4:
                signUp(scanner,login);
                adminTaskMenu(scanner, login);
                break;
            case 5:
                admin.LoadAllUsers();
                adminTaskMenu(scanner, login);
                break;
            case 6:
                admin.LoadAllPortManagers();
                adminTaskMenu(scanner, login);
                break;
            case 7:
                admin.LoadAllAdmins();
                adminTaskMenu(scanner, login);
                break;
            case 8:
                // 뒤로 가기
                main(null);
                break;
            default:
                System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
        }
    }

    private static void addingMenu(Scanner scanner,login login) throws IOException, ClassNotFoundException {
        if(login.getRole().equals("Admin") ){
            System.out.println("Adding 메뉴:");
            System.out.println("1. Container");
            System.out.println("2. Truck");
            System.out.println("3. Ship");
            System.out.println("4. Port");
            System.out.println("5. 뒤로가기");
            System.out.print("선택: ");
            int addingChoice = scanner.nextInt();

            while (true){
                switch (addingChoice) {
                    case 1:
                        addingMenuContainer(scanner,login);
                        addingMenu(scanner,login);
                        break;
                    case 2:
                        addingMenuVehicle(scanner,"Truck",login);
                        addingMenu(scanner,login);
                        break;
                    case 3:
                        // Trip 관리 기능 구현
                        addingMenuVehicle(scanner,"Ship",login);
                        addingMenu(scanner,login);
                        break;
                    case 4:
                        adminAddingMenuPort(scanner);
                        addingMenu(scanner,login);
                        break;
                    case 5:
                        if(login.getRole().equals("Admin")){
                            adminTaskMenu(scanner,login);
                        }else{
                            portManagerTaskMenu(scanner,login.getPortNum(),login);
                        }
                        break;
                    default:
                        System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                }
            }
        }else{
            System.out.println("Adding 메뉴:");
            System.out.println("1. Container");
            System.out.println("2. 뒤로가기");
            System.out.print("선택: ");
            int addingChoice = scanner.nextInt();

            while (true){
                switch (addingChoice) {
                    case 1:
                        addingMenuContainer(scanner,login);
                        addingMenu(scanner,login);
                        break;
                    case 2:
                        addingMenu(scanner,login);
                        break;
                    default:
                        System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                }
            }
        }

    } // admin port-manager 둘다 사용
    private static void deletingMenu(Scanner scanner,login login) throws IOException, ClassNotFoundException { // admin port-manager 둘다 사용
        if(login.getRole().equals("Admin")){
            System.out.println("Deleting 메뉴:");
            System.out.println("1. Container");
            System.out.println("2. Truck");
            System.out.println("3. Ship");
            System.out.println("4. Port");
            System.out.println("5. User");
            System.out.println("6. 뒤로가기");
            System.out.print("선택: ");
            int addingChoice = scanner.nextInt();

            while (true){
                switch (addingChoice) {
                    case 1:
                        deletingMenuTask(scanner,"Container",login);
                        deletingMenu(scanner,login);
                        break;
                    case 2:
                        deletingMenuTask(scanner,"Truck",login);
                        deletingMenu(scanner,login);
                        break;
                    case 3:
                        // Trip 관리 기능 구현
                        deletingMenuTask(scanner,"Ship",login);
                        deletingMenu(scanner,login);
                        break;
                    case 4:
                        deletingMenuTask(scanner,"Port",login);
                        deletingMenu(scanner,login);
                        break;
                    case 5:
                        deletingMenuTask(scanner,"PortManager",login);
                        break;
                    case 6:
                        adminTaskMenu(scanner,login);
                    default:
                        System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                }
            }
        }else{
            System.out.println("Deleting 메뉴:");
            System.out.println("1. Container");
            System.out.println("2. 뒤로가기");
            String choice = scanner.next();
            while (true){
                switch (choice){
                    case "1": deletingMenuTask(scanner,"Container",login);
                        portManagerTaskMenu(scanner, login.getPortNum(),login);
                    case "2":
                        portManagerTaskMenu(scanner, login.getPortNum(),login);

                }
            }
        }
    }

    private static void addingMenuVehicle(Scanner scanner, String type,login login) throws IOException {
        System.out.println("Enter the ID number");
        int idNumber = scanner.nextInt();
        System.out.println("Enter the capacity");
        double capacity = scanner.nextDouble(); // Double값이 아닐경우 전에 메뉴로 돌아가게
        if(login.getRole().equals("Admin")){
            if(type.equals("Truck")){
                System.out.println("Select the truck's type.");
                System.out.println("1. Basic");
                System.out.println("2. Reefer");
                System.out.println("3. Tanker");
                int typeNum = scanner.nextInt();
                switch (typeNum) {
                    case 1:
                        type = "basic";
                        break;
                    case 2:
                        type = "reefer";
                        break;
                    case 3:
                        type = "Tanker";
                        break;
                    default:
                }

                System.out.println("Type the port number you want to place.");
                int portNumber = scanner.nextInt();
                Truck truck = new Truck("Tr-"+idNumber,type,capacity,"Port"+portNumber);
            }else{
                System.out.println("Type the port number you want to place.");
                String portNumber = scanner.next();
                Ship ship = new Ship("Sh-"+idNumber,capacity,"Port"+portNumber);
            }
        }else{
            if(type.equals("Truck")){
                System.out.println("Select the truck's type. ");
                String truckType = scanner.next();
                Truck truck = new Truck("Tr-"+idNumber,truckType,capacity,login.getPortNum());
            }else{
                Ship ship = new Ship("Sh-"+idNumber,capacity,login.getPortNum());
            }


        }
    }
    private static void adminAddingMenuPort(Scanner scanner){
        System.out.println("Enter the name of port");
        String portName = scanner.next();
        System.out.println("Enter the ID number");
        int idNumber = scanner.nextInt();
        System.out.println("Enter the capacity");
        Double capacity = scanner.nextDouble(); // Double값이 아닐경우 전에 메뉴로 돌아가게
        System.out.println("Enter the port's Longtitude");
        Double Longtitude = scanner.nextDouble();
        System.out.println("Enter the port's Latitude");
        Double Latitude = scanner.nextDouble();
        System.out.println("Enter the port's landing ability. 0 - No, 1- Yes");
        int landingAbility = scanner.nextInt(); // 0과 1값이 안들어왔을때 대응
        boolean landing = true;
        if(landingAbility == 0){
            landing = false;
        }
        Port port = new Port(portName,"Port"+idNumber,capacity,Longtitude,Latitude,landing);


    }
    private static void addingMenuContainer(Scanner scanner,login login) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Enter the number of containerID");
            int containerNum = scanner.nextInt();
            System.out.println("Select the Type of container");
            System.out.println("1.dry storage, 2.open top, 3.open side, 4.refrigerated, 5.liquid, 9.exit"); // 5이외의 값 혹은 String이 들어왔을때 대처
            int typeNum = scanner.nextInt();
            String type = null;
            switch (typeNum) {
                case 1:
                    type = "dry storage";
                    break;
                case 2:
                    type = "open top";
                    break;
                case 3:
                    type = "open side";
                    break;
                case 4:
                    type = "refrigerated";
                    break;
                case 5:
                    type = "liquid";
                    break;
                case 9:
                    addingMenu(scanner,login);
                    break;
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                    addingMenuContainer(scanner,login);
            }
            System.out.println("Enter the weight");
            Double weight = scanner.nextDouble();
            if(login.getRole().equals("Admin")){
                System.out.println("Enter the port number you want to position");
                String portNum1 = scanner.next();
                Container container = new Container("C-" + containerNum, type, weight, "Port"+portNum1);
            }else{
                Container container = new Container("C-" + containerNum, type, weight, login.getPortNum());
            }
            break;
        }
    }
    private static void signUp(Scanner scanner, login login) throws IOException, ClassNotFoundException {
        System.out.println("Please select the role. ");
        System.out.println("1. Admin ");
        System.out.println("2. PortManager");
        int addingChoice = scanner.nextInt();
        String username=null;
        String password = null;
        while (true){
            switch (addingChoice) {
                case 1:
                    System.out.println("Type the user name."); // String이 아닐경우 예외처리
                    username = scanner.next();
                    System.out.println("Type the password."); // String이 아닐경우 예외처리
                    password = scanner.next();
                    Admin admin1 = new Admin(username,password);
                    break;
                case 2:
                    System.out.println("Type the user name."); // String이 아닐경우 예외처리
                    username = scanner.next();
                    System.out.println("Type the password."); // String이 아닐경우 예외처리
                    password = scanner.next();
                    System.out.println("Type the port number want to set in.");
                    int num = scanner.nextInt();
                    PortManager portManager = new PortManager(username,password,"Port"+num); // 만약 해당 port가 없을때 예외처리
                    break;
                default:
                    System.out.println("Please type available number.");
            }
            addingMenu(scanner,login);
        }

    }

    // Port Manager 메뉴
    private static void deletingMenuTask(Scanner scanner, String type, login login) {
        Admin admin = new Admin();
        if(login.getRole().equals("Admin")){
            if(type.equals("Container")){ // 밑에 요소들 예외처리 필요
                System.out.println("Enter the port number");
                int portNumber = scanner.nextInt();

                System.out.println("Enter the id number");
                int idNumber = scanner.nextInt();
                admin.deleteFile("Port"+portNumber,"C-"+idNumber+".txt");


            } else if (type.equals("Truck")) {
                System.out.println("Enter the port number");
                int portNumber = scanner.nextInt();

                System.out.println("Enter the id number");
                int idNumber = scanner.nextInt();
                admin.deleteFile("Port"+portNumber, "Tr-" + idNumber+".txt");
            } else if (type.equals("Ship")) {
                System.out.println("Enter the port number");
                int portNumber = scanner.nextInt();

                System.out.println("Enter the id number");
                int idNumber = scanner.nextInt();
                admin.deleteFile("Port"+portNumber, "Sh-" + idNumber+".txt");

            } else if (type.equals("Port")) {
                System.out.println("Enter the port number");
                int portNumber = scanner.nextInt();
                admin.deletePort("Port"+portNumber);



            } else if (type.equals("PortManager")) {

                System.out.println("Enter the user name");
                String userName = scanner.next();
                User user = new User();
                user.removePortManagerID(userName);


            }
        }else{

            System.out.println("Enter the id number");
            String idNumber = scanner.nextLine();
            admin.deleteFile(login.getPortNum(), "C-"+idNumber);


        }

    }}