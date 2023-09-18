package Super;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trip implements PortLoader{
    private String ID;
    private LocalDate DepartureDate;
    private LocalDate ArrivalDate;
    private String VehicleID;
    private String DeparturePortNum;
    private String ArrivalPortNum;
    private ArrayList<Container> Containers;
    private Double Distance;
    private Vehicle Vehicle;
    private static String Status;
/*    enum Status{
        Anchored,Delivering,Arrived, // 시간지나면 Anchored -> Delivering -> Arrived 되게 바꿀것, Vehicle 위치는 배송시작하면
        // null로 바꾸고 도착하는날에 도착하는 장소 port로 바꿀것
    }*/

    public Trip(){
        ID = null;
        DepartureDate = null;
        ArrivalDate = null;
        VehicleID = null;
        DeparturePortNum = null;
        ArrivalPortNum = null;
        Vehicle = null;
        Status = null;
    }
    public Trip(String ID, LocalDate DepartureDate, LocalDate ArrivalDate, String Type,Vehicle Vehicle, String DeparturePortNum, String ArrivalPortNum) throws IOException, ClassNotFoundException {
        // ArrivalDate는 distance에 따라 예상 도착시간으로 바꿀예정
        LocalDate now = LocalDate.now();
        if(DepartureDate.isBefore(now)){
            this.Status = "Anchored";
        } else if (DepartureDate.equals(now)&&ArrivalDate.isAfter(now)) {
            this.Status = "Delivering";
        }else{
            System.out.println("You can't to manage the trip departure date before now.");
            return;
        }
        double vehicleWeight =Vehicle.CapacityCal(Vehicle);
        this.ID = ID;
        this.DepartureDate = DepartureDate;
        this.ArrivalDate = ArrivalDate;
        this.Vehicle = Vehicle;
        this.VehicleID = Vehicle.ID;
        this.DeparturePortNum = DeparturePortNum;
        this.ArrivalPortNum = ArrivalPortNum;
        this.Containers = Vehicle.Containers;
        double Distance = Calculator.calculateDistance(DeparturePortNum,ArrivalPortNum,Vehicle);
        Port target = loadPort();
        if(Type.equals("Ship")){
            if(target.getLandingAbility()){
                double fuelConsumeExpect = Vehicle.FuelCalculation()*Distance;
                if (Vehicle.getFuel() > fuelConsumeExpect) {
                    Vehicle.setCurrentPortNum(this.ArrivalPortNum);
                    moveFile(DeparturePortNum, VehicleID, DeparturePortNum);
                    for (Container container : Containers) {
                        moveFile(DeparturePortNum, container.getID(), DeparturePortNum);
                        container.setCurrentPortNum(this.DeparturePortNum);
                    }
                    Vehicle.FuelConsumption(Vehicle.FuelCalculation());
                    save(DeparturePortNum);
                    save(ArrivalPortNum);
                } else {
                    System.out.println("Vehicle needs to be refuel");
                }
            }else{
                System.out.println("There is no landing ability in arrival port");
                return;
            }
        } else if (target.TotalWeightCalculate(ArrivalPortNum) +vehicleWeight > target.getStoringCapacity() ) {
            System.out.println("Arrival port's condition will be saturation, we can't manage the trip.");
        } else{ double fuelConsumeExpect = Vehicle.FuelCalculation()*Distance;
            if (Vehicle.getFuel() > fuelConsumeExpect) {
                Vehicle.setCurrentPortNum(this.ArrivalPortNum);
                moveFile(DeparturePortNum, VehicleID, DeparturePortNum);
                for (Container container : Containers) {
                    moveFile(DeparturePortNum, container.getID(), DeparturePortNum);
                    container.setCurrentPortNum(this.DeparturePortNum);
                }
                Vehicle.FuelConsumption(Vehicle.FuelCalculation());
                save(DeparturePortNum);
                save(ArrivalPortNum);
            } else {
                System.out.println("Vehicle needs to refuel");
            }


        }



    }



    public void save(String portID) throws IOException {
        String projectRoot = System.getProperty("user.dir");
        try {
            PrintWriter output = new PrintWriter(new FileWriter(projectRoot+"/src/"+portID+"/Data/History.txt", true));
            output.println(this.ID+":"+this.VehicleID+":"+DepartureDate+":"+this.ArrivalDate+":"+this.DeparturePortNum+":"+this.ArrivalPortNum+":"+this.Distance+":"+this.Containers);
            output.flush();
            output.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void moveFile(String portID, String fileName, String ArrivalPortName){
        String projectRoot = System.getProperty("user.dir");
        String departurePath = projectRoot+"/src/" + portID + "/Data/" + fileName;
        String arrivalPath = projectRoot+"/src/" + ArrivalPortName + "/Data/" + fileName;
        try {
            Path filePath = Paths.get(departurePath);
            Path filePathToMove = Paths.get(arrivalPath);
            Files.move(filePath, filePathToMove);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Port loadPort() throws IOException, ClassNotFoundException {
        ArrayList<Port> allPorts = (ArrayList<Port>) loadAllPorts();
        Port target = new Port();
        for(Port port :allPorts){
            if(port.getPortNum().equals(ArrivalPortNum)){
                port = target;
            }
        }
        return target;
    }

}