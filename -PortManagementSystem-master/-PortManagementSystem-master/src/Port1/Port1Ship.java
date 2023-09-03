package Port1;

import Super.Container;
import Super.Ship;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Port1Ship extends Ship implements Serializable{
    private static final long serialVersionUID = 13L;
    private String ID;
    private Double Fuel = 100.0;
    private Double Capacity;
    private ArrayList<Container> Containers;

    public Port1Ship(){
        this.ID = null;
        this.Fuel = 100.0;
        this.Capacity = null;
    }
    public Port1Ship(String ID, Double capacity)throws IOException{
        this.ID = ID;
        this.Capacity = capacity;
        this.Containers = new ArrayList<>();
        save("Port1",this.ID,this);
    }
    @Override
    public void loadContainer(Container container)throws IOException{
        Containers.add(container);
        CapacityCal(this);
        save("Port1",this.ID,this);
    }

    @Override
    public void unloadContainer(Container container) {
        Containers.remove(container);
    }

    @Override
    public Double getFuel() {
        return Fuel;
    }

    public Double getCapacity() {
        return Capacity;
    }

    public String getID() {
        return ID;
    }

    public ArrayList<Container> getContainers() {
        return Containers;
    }


    @Override
    public Double FuelConsumption(Double consumption) {
        this.Fuel -= consumption;
        return Fuel;
    }

    @Override
    public void Refuel() {
        this.Fuel = 100.0;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "ID='" + ID + '\'' +
                ", fuel=" + Fuel +
                ", capacity=" + Capacity +
                ", containers=" + Containers +
                '}';
    }


}
