package Super;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Vehicle implements Save{
    private static final long serialVersionUID = 1L;
    public String ID;
    public ArrayList<Container> Containers = new ArrayList<>();
    public Double Capacity;
    public String currentPortNum;
    public Double fuel = 100.0;
    private Double kmPerFuel = null;
    public Vehicle(){}
    public Vehicle(String name, String ID, double Capacity, String currentPortNum) throws IOException {
        this.ID = ID;
        this.Capacity = Capacity;
        this.fuel = 100.0;
        this.currentPortNum = currentPortNum;
    }

    abstract String getID();

    abstract void loadContainer(Container container) throws IOException;

    abstract void unloadContainer(Container container) throws IOException;

    abstract Double getFuel();
    abstract void setCurrentPortNum(String currentPortNum) throws IOException;

    abstract Double FuelConsumption(Double consumption) throws IOException;

    abstract void Refuel() throws IOException;
    abstract double CapacityCal(Vehicle vehicle);
    abstract Double FuelCalculation();
}