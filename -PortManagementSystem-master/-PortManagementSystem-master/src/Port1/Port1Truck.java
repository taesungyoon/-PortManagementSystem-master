package Port1;
import Super.Container;

import Super.Save;
import Super.Truck;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Port1Truck extends Truck implements Serializable{
    private static final long serialVersionUID = 14L;
    private String Type;
    private String ID;
    private Double Fuel;
    private Double Capacity;
    private ArrayList<Container> Containers;
    public Port1Truck(){}
    public Port1Truck(String ID, Double capacity) throws IOException{
        this.ID = ID;
        this.Capacity = capacity;
        this.Containers = new ArrayList<>();
        save("Port1",this.ID,this);
    }    @Override
    public Double FuelConsumption(Double consumption) {
        return this.FuelConsumption(consumption);
    }

    @Override
    public void loadContainer(Container container) throws IOException {
        if(Containers.size()>1){
            CapacityCal(this);
        }
        this.Containers.add(container);
        save("Port1",this.ID,this);
    }


    @Override
    public void Refuel() {
        this.Fuel = 100.0;
    }
}
