package Super;

import java.util.ArrayList;

public abstract class Vehicle implements Save{
    public String ID;
    public ArrayList<Container> Containers = new ArrayList<>();
    public Double Capacity;

    abstract void loadContainer(Container container);

    abstract void unloadContainer(Container container);

    abstract Double getFuel();

    abstract Double FuelConsumption(Double consumption);

    abstract void Refuel();
    abstract void CapacityCal(Vehicle vehicle);

}
