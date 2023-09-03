package Port1;

import Super.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;


public class Port1Trip extends Trip{
    private String ID;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String vehicleID;
    private String Status;
    private Port departurePort;
    private Port arrivalPort;
    private ArrayList<Container> Containers;

    public Port1Trip(){
        ID = null;
        departureDate = null;
        arrivalDate = null;
        vehicleID = null;
        Status = null;
        departurePort = null;
        arrivalPort = null;
    }
    public Port1Trip(String ID, LocalDate DepartureDate, LocalDate ArrivalDate, Vehicle Vehicle, Port DeparturePort, Port ArrivalPort){
        this.ID = ID;
        this.departureDate = DepartureDate;
        this.arrivalDate = ArrivalDate;
        this.vehicleID = Vehicle.ID;
        this.departurePort = DeparturePort;
        this.arrivalPort = ArrivalPort;
        this.Containers = Vehicle.Containers;
        Port1MoveFile(""+vehicleID,""+ArrivalPort);
        for(Container container :Containers){
            String ContainerID = container.getID();
            Port1MoveFile(""+ContainerID+".txt",""+ArrivalPort);
        }
    }

    @Override
    public String toString() {
        return "Port1Trip{" +
                "ID='" + ID + '\'' +
                ", DepartureDate=" + departureDate +
                ", ArrivalDate=" + arrivalDate +
                ", VehicleID='" + vehicleID + '\'' +
                ", Status='" + Status + '\'' +
                ", DeparturePort=" + departurePort +
                ", ArrivalPort=" + arrivalPort +
                ", Containers=" + Containers +
                '}';
    }
    public void Port1MoveFile(String fileName, String ArrivalPortName){
        moveFile("Port1",fileName,ArrivalPortName);
    }


    @Override
    public void save() throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter("./src/Port1/Data/History.txt", true));
        output.println(this.ID+":"+this.vehicleID+":"+this.Status+":"+departureDate+":"+this.arrivalDate+":"+this.departurePort+":"+this.arrivalPort+":"+this.Containers);
        output.flush();
        output.close();
    }

    public ArrayList<Container> getContainers() {
        return Containers;
    }

    public void moveFile(String portName, String fileName, String ArrivalPortName){
        try {
            Path filePath = Paths.get("./src/"+portName+"/Data/"+fileName);
            Path filePathToMove = Paths.get("./src/"+ArrivalPortName+"/Data/"+fileName);
            if (Files.exists(filePath)) {
                Files.move(filePath, filePathToMove);
            } else {
                System.err.println("The file does not exist: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
