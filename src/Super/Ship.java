package Super;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Ship extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ID; // Ship ID should be a combination with "Sh" and numbers
    private Double fuel = 360000.0;
    private Double capacity;
    private ArrayList<Container> containers = new ArrayList<>();
    private Double kmPerFuel;
    private String currentPortNum =null;


    public Double getKmPerFuel() {
        return kmPerFuel;
    }

    public Ship(){
        this.ID = null;
        this.fuel = 360000.0; // 일반적인 기름량...
        this.capacity = null;
        this.Containers = new ArrayList<>();
        this.kmPerFuel = null;
    }

    @Override
    String getID() {
        return this.ID;
    }

    public Ship(String ID, Double capacity,String currentPortNum) throws IOException {
        this.ID = ID;
        this.capacity = capacity;
        this.currentPortNum = currentPortNum;
        this.containers = new ArrayList<>();
        save(currentPortNum,ID,this);
    }
    @Override
    public void loadContainer(Container container) throws IOException {
        CapacityCal(this);
        this.containers.add(container);
        this.kmPerFuel = FuelCalculation();
        try {
            save(currentPortNum,this.ID,this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unloadContainer(Container container) throws IOException {
        for(Container c : containers){
            if(c.getID().equals(container.getID())){
                containers.remove(c);
                this.kmPerFuel = FuelCalculation();
                try {
                    save(currentPortNum,this.ID,this);
                } catch (Exception e){
                    System.out.println("There is no matched container");
                }
            }else{
                System.out.println("There is no matched container");
            }
        }
    }

    @Override
    public Double getFuel() {
        return fuel;
    }

    @Override
    void setCurrentPortNum(String currentPortNum) throws IOException {
        this.currentPortNum = currentPortNum;
        save(currentPortNum,this.ID,this);
    }

    @Override
    public Double FuelConsumption(Double consumption) throws IOException {
        this.fuel -= consumption;
        save(currentPortNum,this.ID,this);

        return fuel;
    }

    @Override
    public void Refuel() {
        this.fuel = 360000.0;
        try {
            save(currentPortNum,this.ID,this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double CapacityCal(Vehicle vehicle) {
        Double totalWeight = 0.0;
        for(Container container :vehicle.Containers){
            totalWeight += container.getWeight();
            if(totalWeight>vehicle.Capacity){
                System.out.println("Capacity is full!");
                System.out.println("Last container is not loaded");
                Containers.remove(this.Containers.size()-1);

            }

        }return totalWeight;
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

    @Override
    public void save(String PortNum, String ObjectID, Object obj) throws IOException {
        super.save(currentPortNum, this.ID, this);
    }

    @Override
    public Double FuelCalculation() {
        Double total = 0.0;
        for (Container container : this.Containers) {
            String containerType = container.getType();
            if (containerType == "dry storage") {
                total += 3.5;
            } else if (containerType == "open top") {
                total += 2.8;
            } else if (containerType == "open side") {
                total += 2.7;
            } else if (containerType == "refrigerated") {
                total += 4.5;
            } else if (containerType == "liquid") {
                total += 4.8;
            }
        }
        return total;
    }
}
