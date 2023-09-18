package UserInterface.Users.PortManager;

import Super.Vehicle;
import UserInterface.Users.User;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class PortManager extends User {
    public PortManager(String userName,String password,String portNum) throws IOException {
        super("PortManager",userName,password,portNum);
    }
    private Scanner scanner;

    public PortManager() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        // Using a HashMap to store port information

        // Input for Departure Port
        System.out.println("Enter the Departure: ");
        String departurePortName = scanner.nextLine();

        // Input for Arrival Port
        System.out.println("Enter the Arrival: ");
        String arrivalPortName = scanner.nextLine();

        // Retrieve the corresponding port objects based on the entered port names.
//        Port departurePort = PortList.get(departurePortName);
//        Port arrivalPort = PortList.get(arrivalPortName);

//        if (departurePort != null && arrivalPort != null) {
            // Create an instance of the operation processor
////            operationProcessor processor = new distanceCalculator();
//            double distance = processor.performOperation(departurePort, arrivalPort);
//            System.out.printf("The distance from %s to %s is %.3f km%n", departurePort.getName(), arrivalPort.getName(), distance);
//        } else {
//            System.out.println("The entered port name is not valid.");
//        }
    }
    public LocalDate StringToDate(String string1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(string1, formatter);
        return date;
    }
    public void ListTripsin7Days(String PortID) throws IOException{
        deleteMorethan7days(PortID);
        LocalDate now = LocalDate.now();
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/"+PortID+"/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    if(!(now.isBefore(StringToDate(DepartureDate).minusDays(8)))){
                        System.out.println(line);
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListTripsinSpecificDays(String PortID,String Date) throws IOException{
        deleteMorethan7days(PortID);
        LocalDate specificDate = StringToDate(Date);
        LocalDate now = LocalDate.now();
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/"+PortID+"/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    LocalDate D1 = StringToDate(DepartureDate);
                    String ArrivalDate = lines[3];
                    LocalDate A1 = StringToDate(ArrivalDate);
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void ListTripsinDays(String PortID,String departureDate,String arrivalDate) throws IOException{
        deleteMorethan7days(PortID);
        LocalDate departure = StringToDate(departureDate);
        LocalDate arrival = StringToDate(arrivalDate);
        LocalDate now = LocalDate.now();
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/"+PortID+"/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    LocalDate D1 = StringToDate(DepartureDate);
                    String ArrivalDate = lines[3];
                    LocalDate A1 = StringToDate(ArrivalDate);
                    if((!(now.isBefore(StringToDate(DepartureDate).minusDays(8))))&&departure.isAfter(D1) && arrival.isBefore(A1)){
                        System.out.println(line);
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        public void listAllVehicle(String portNumberID){
            listAllTruck(portNumberID);
            listAllShip(portNumberID);
        }
    public void listAllTruck(String portNumberID){
        printAllFilesStartWith("Tr-",portNumberID);
    }
    public void listAllShip(String portNumberID){
        printAllFilesStartWith("Sh-",portNumberID);
    }
    public Vehicle searchVehicle(String PortNum,String type, String vehicleIDNumber){
        String searchType = null;
        Vehicle result = null;
        if(type.equals("Ship")){
            searchType = "Sh-";
        }else{
            searchType = "Tr-";
        }

        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot+"/src/Data/"+PortNum;
        File folder = new File(path);
        File[] FileList = folder.listFiles();
        for (File f : FileList) {
            String name = f.getName();
            if (name.equals(searchType + vehicleIDNumber+".txt")) {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                     result = (Vehicle) input.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    public void deleteMorethan7days(String portNum) { // variable 대체 필요, 테스트 필요
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data" + portNum + "/History.txt";
        File tempFile = new File(projectRoot + "/src/Data" + portNum + "/tmp.txt");
        try {
            FileReader fr = new FileReader(path);
            FileWriter tempFileWriter = new FileWriter(tempFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            BufferedWriter bufferedWriter = new BufferedWriter(tempFileWriter);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(":");
                LocalDate departureDate = StringToDate(lines[2]);
                LocalDate arrivalDate = StringToDate(lines[3]);
                LocalDate now = LocalDate.now();
                if (!(departureDate.isBefore(now.plusDays(7)) && arrivalDate.isBefore(now.plusDays(7)))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            File originalFile = new File(path);
            if (originalFile.delete()) {
                if (tempFile.renameTo(originalFile)) {
                    System.out.println("Delete complete");
                } else {
                    System.out.println("Failed to delete");
                }
            } else {
                System.out.println("Failed to delete original file");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

//    public void deleteMorethan7days(String portNum){ // GPT 꺼라서 variable, 로직 몇개 수정 필요
//        String projectRoot = System.getProperty("user.dir");
//        String path = projectRoot+"/src/Data"+portNum+"/History.txt";
//        try {
//            FileReader fr = new FileReader(path);
//            FileWriter tempFileWriter = new FileWriter("tmp.txt");
//            BufferedReader bufferedReader = new BufferedReader(fr);
//            BufferedWriter bufferedWriter = new BufferedWriter(tempFileWriter);
//            String line;
//
//
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] lines = line.split(":");
//                LocalDate departureDate = StringToDate(lines[2]);
//                LocalDate arrivalDate = StringToDate(lines[3]);
//                LocalDate now = LocalDate.now();
//                if (!(departureDate.isBefore(now.plusDays(7))&&arrivalDate.isBefore(now.plusDays(7)))) {
//                    bufferedWriter.write(line);
//                    bufferedWriter.newLine();
//                }
//            }
//            bufferedReader.close();
//            bufferedWriter.close();
//            File originalFile = new File(path);
//            File tempFile = new File("temp.txt");
//            if (tempFile.renameTo(originalFile)) {
//                System.out.println("Delete complete");
//            } else {
//                System.out.println("Failed to delete");
//            }
//
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }

}


//    public class trip {
//        private String filePath; // 여행 기록이 저장된 파일 경로
//
//        public trip(String filePath) {
//            this.filePath = filePath;
//        }
//
//        public List<String> searchTripsByDate(LocalDate targetDate) throws IOException {
//            List<String> matchingTrips = new ArrayList<>();
//            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // 파일에서 한 줄씩 읽어와서 날짜와 비교
//                    if (line.contains(targetDate.toString())) {
//                        matchingTrips.add(line);
//                    }
//                }
//            }
//            return matchingTrips;
//        }
//
//        public List<String> searchTripsByDateRange(LocalDate startDate, LocalDate endDate) throws IOException {
//            List<String> tripsInRange = new ArrayList<>();
//            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // 파일에서 한 줄씩 읽어와서 날짜 범위와 비교
//                    if (isDateInRange(line, startDate, endDate)) {
//                        tripsInRange.add(line);
//                    }
//                }
//            }
//            return tripsInRange;
//        }
//
//        private boolean isDateInRange(String line, LocalDate startDate, LocalDate endDate) {
//            // 여기에서 기록된 날짜를 파싱하고 startDate와 endDate 사이에 있는지 확인
//            // 예: "2023-09-08:Trip123:Vehicle456:Location1"
//            String[] parts = line.split(":");
//            if (parts.length >= 4) { // 최소한 출발일과 도착일이 존재해야 함
//                LocalDate tripDate = LocalDate.parse(parts[0]); // 여기서 첫 번째 요소를 날짜로 파싱
//                return !tripDate.isBefore(startDate) && !tripDate.isAfter(endDate);
//            }
//            return false;
//        }
//
//        public static void main(String[] args) {
//            String projectRoot = System.getProperty("user.dir");
//            String path = projectRoot+"/src/"+"History.txt";
//
//
//            String filePath = "/Users/jimincho/IdeaProjects/untitled6/src/History.txt"; // 여행 기록이 저장된 파일 경로
//
//            trip historySearch = new trip(filePath);
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Select an option:");
//            System.out.println("1. Search by specific date");
//            System.out.println("2. Search by date range");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    // 특정 날짜 검색
//                    System.out.println("Enter a date (yyyy-MM-dd): ");
//                    String dateInput = scanner.next();
//                    LocalDate targetDate = LocalDate.parse(dateInput);
//                    try {
//                        List<String> tripsOnDate = historySearch.searchTripsByDate(targetDate);
//
//                        if (!tripsOnDate.isEmpty()) {
//                            System.out.println("Trips on " + targetDate + ":");
//                            for (String trip : tripsOnDate) {
//                                System.out.println(trip);
//                            }
//                        } else {
//                            System.out.println("No trips found on " + targetDate + ".");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//
//                case 2:
//                    // 날짜 범위 검색
//                    System.out.println("Enter start date (yyyy-MM-dd): ");
//                    String startDateInput = scanner.next();
//                    LocalDate startDate = LocalDate.parse(startDateInput);
//                    System.out.println("Enter end date (yyyy-MM-dd): ");
//                    String endDateInput = scanner.next();
//                    LocalDate endDate = LocalDate.parse(endDateInput);
//                    try {
//                        List<String> tripsInRange = historySearch.searchTripsByDateRange(startDate, endDate);
//
//                        if (!tripsInRange.isEmpty()) {
//                            System.out.println("Trips in the date range " + startDate + " to " + endDate + ":");
//                            for (String trip : tripsInRange) {
//                                System.out.println(trip);
//                            }
//                        } else {
//                            System.out.println("No trips found in the date range " + startDate + " to " + endDate + ".");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//
//                default:
//                    System.out.println("Invalid option.");
//                    break;
//            }
//
//            scanner.close();
//        }
//
////    public void ListAllTrips(String PortID) throws IOException {
////        try {
////            String projectRoot = System.getProperty("user.dir");
////            String path = projectRoot + "/src/Data/"+PortID+"/History.txt";
////            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
////                String line;
////                if ((line = reader.readLine()) == null) {
////                    System.out.println("It is empty");
////                }
////                while (line != null) {
////                    String[] lines = line.split(":");
////                    System.out.println(line);
////                    line = reader.readLine();
////                }
////            }
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
//    public void PortManagerMenu(){
//
//    }
////    public void PortManagerMenu(String portID) throws IOException {
////        System.out.println("============================== Port Management System ==========================================================");
////        String menu = strInput("메뉴[1. Storing Capacity, 2. Manage Container, 3. Manage Vehicle, 4.Trip, 5. View History, E. 프로그램종료]");
////        System.out.println("================================================================================================================");
////        switch (menu) {
////            case "E":
////                return; // 종료
////            case "1":
////                ListAllTrips(portID);
////                break;
////            case "2":
////                // 2번 메뉴 코드를 여기에 추가
////                break;
////            case "3":
////                // 3번 메뉴 코드를 여기에 추가
////                break;
////            case "4":
////                // 4번 메뉴 코드를 여기에 추가
////                break;
////            case "5":
////                // 5번 메뉴 코드를 여기에 추가
////                break;
////            case "6":
////                start(); // 로그아웃
////                break;
////            default:
////                System.out.println("Invalid input.");
////                break;
////        }
////
//
//
//    public void closeScanner() {
//        scanner.close();
//    }
//
//}
