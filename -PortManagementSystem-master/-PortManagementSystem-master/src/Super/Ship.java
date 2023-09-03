package Super;

import java.io.Serializable;
import java.util.ArrayList;

public class Ship extends Vehicle implements Serializable {
    private String ID;
    private Double fuel;
    private Double capacity;
    private ArrayList<Container> containers;

    public Ship(){
        this.ID = null;
        this.fuel = 100.0;
        this.capacity = null;
        this.Containers = new ArrayList<>();
    }
    public Ship(String ID, Double capacity){
        this.ID = ID;
        this.capacity = capacity;
    }
    @Override
    public void loadContainer(Container container) {
        CapacityCal(this);
        containers.add(container);
    }

    @Override
    public void unloadContainer(Container container) {
        containers.remove(container);
    }

    @Override
    public Double getFuel() {
        return fuel;
    }

    @Override
    public Double FuelConsumption(Double consumption) {
        this.fuel -= consumption;
        return fuel;
    }

    @Override
    public void Refuel() {
        this.fuel = 100.0;
    }

    @Override
    public void CapacityCal(Vehicle vehicle) {
        Double totalWeight = 0.0;
        for(Container container :vehicle.Containers){
            totalWeight += container.getWeight();
            if(totalWeight>vehicle.Capacity){
                System.out.println("Capacity is full!");
                System.out.println("Last container is not loaded");
                Containers.remove(this.Containers.size()-1);
                return;
            }
        }
    }


    @Override
    public String toString() {
        return "Ship{" +
                "ID='" + ID + '\'' +
                ", fuel=" + fuel +
                ", capacity=" + capacity +
                ", containers=" + containers +
                '}';
    }
}
