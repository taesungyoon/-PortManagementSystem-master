package Super;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Vehicle implements Save{
    private static final long serialVersionUID = 1L;
    public String ID;
    public ArrayList<Container> Containers = new ArrayList<>();
    public Double Capacity;

    abstract void loadContainer(Container container) throws IOException;

    abstract void unloadContainer(Container container);

    abstract Double getFuel();

    abstract Double FuelConsumption(Double consumption);

    abstract void Refuel();
    abstract void CapacityCal(Vehicle vehicle);

}
