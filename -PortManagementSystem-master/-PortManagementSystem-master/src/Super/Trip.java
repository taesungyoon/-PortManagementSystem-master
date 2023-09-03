package Super;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Trip {
    private String ID;
    private LocalDate DepartureDate;
    private LocalDate ArrivalDate;
    private String VehicleID;
    private String Status;
    private Port DeparturePort;
    private Port ArrivalPort;
    private ArrayList<Container> Containers;

    public Trip(){
        ID = null;
        DepartureDate = null;
        ArrivalDate = null;
        VehicleID = null;
        Status = null;
        DeparturePort = null;
        ArrivalPort = null;
    }
    public Trip(String ID, LocalDate DepartureDate, LocalDate ArrivalDate, Vehicle Vehicle, Port DeparturePort, Port ArrivalPort){
        this.ID = ID;
        this.DepartureDate = DepartureDate;
        this.ArrivalDate = ArrivalDate;
        this.VehicleID = Vehicle.ID;
        this.DeparturePort = DeparturePort;
        this.ArrivalPort = ArrivalPort;
        this.Containers = Vehicle.Containers;

    }

    @Override
    public String toString() {
        return "Trip{" +
                "ID='" + ID + '\'' +
                ", DepartureDate=" + DepartureDate +
                ", ArrivalDate=" + ArrivalDate +
                ", VehicleID='" + VehicleID + '\'' +
                ", Status='" + Status + '\'' +
                ", DeparturePort=" + DeparturePort +
                ", ArrivalPort=" + ArrivalPort +
                ", Containers=" + Containers +
                '}';
    }

    public void save(String portNumber) throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter("./src/"+portNumber+"/Data/History.txt", true));
        output.println(this.ID+":"+this.VehicleID+":"+this.Status+":"+DepartureDate+":"+this.ArrivalDate+":"+this.DeparturePort+":"+this.ArrivalPort+":"+this.Containers);
        output.flush();
        output.close();
    }
    public void moveFile(String portName, String fileName, String ArrivalPortName){
        try {
            Path filePath = Paths.get("./src/"+portName+"/Data/"+fileName);
            Path filePathToMove = Paths.get("./src/+"+ArrivalPortName+"/Data/"+fileName);
            Files.move(filePath, filePathToMove);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void save() throws IOException;
}
