package Super;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Truck extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Type;
    private String ID; // Ship ID should be a combination with "Tr-" and numbers
    private Double fuel = 700.0;
    private Double capacity;
    private ArrayList<Container> containers = new ArrayList<>();
    private Double kmPerFuel = null;
    private String currentPortNum =null;

    public Truck(){
        fuel = 700.0;//일반적인 기름량
    }

    public String getID() {
        return ID;
    }


    public Truck(String ID, String Type, Double Capacity,String currentPortNum) throws IOException {
        this.ID = ID;
        this.Type = Type;
        this.Capacity = Capacity;
        this.currentPortNum = currentPortNum;
        this.containers = new ArrayList<>();
        save(currentPortNum,this.ID,this);
    }

    public String getType() {
        return Type;
    }

    @Override
    public void loadContainer(Container container) throws IOException {
        if(container.getType().equals("liquid")){
            if(this.Type.equals("tanker")){
                CapacityCal(this);
                this.containers.add(container);
                this.kmPerFuel = FuelCalculation();
                try {
                    save(currentPortNum,this.ID,this);
                    System.out.println("Truck load container.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Truck doesn't have liquid storage.");
            }

        } else if (container.getType().equals("refrigerated")) {
            if(this.Type.equals("reefer")){
                CapacityCal(this);
                this.containers.add(container);
                this.kmPerFuel = FuelCalculation();
                try {
                    save(currentPortNum,this.ID,this);
                    System.out.println("Truck load container.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Truck doesn't have refrigerator storage.");
            }

        }else{
            CapacityCal(this);
            this.containers.add(container);
            this.kmPerFuel = FuelCalculation();
            try {
                save(currentPortNum,this.ID,this);
                System.out.println("Truck load container.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        this.kmPerFuel = FuelCalculation();
        save(currentPortNum,this.ID,this);
    }

    @Override
    public Double getFuel() {
        return fuel;
    }

    @Override
    public Double FuelConsumption(Double consumption) throws IOException {
        if((this.fuel -= consumption)<0 ){
            System.out.println("Fuel will be lower than 0! Please refuel.");
        }else{
            this.fuel -= consumption;
        }
        save(currentPortNum,this.ID,this);
        return this.fuel;
    }

    @Override
    public void Refuel() throws IOException {
        this.fuel = 700.0;
        save(currentPortNum,this.ID,this);
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
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "Type='" + Type + '\'' +
                ", ID='" + ID + '\'' +
                ", fuel=" + fuel +
                ", capacity=" + capacity +
                ", containsers=" + containers +
                '}';
    }

    @Override
    Double FuelCalculation() {
        Double total = 0.0;
        for (Container container : this.Containers) {
            String containerType = container.getType();
            if (containerType.equals("dry storage")) {
                total += 4.6;
            } else if (containerType.equals("open top")) {
                total += 3.2;
            } else if (containerType.equals("open side")) {
                total += 3.2;
            } else if (containerType.equals("refrigerated")) {
                total += 5.4;
            } else if (containerType.equals("liquid")) {
                total += 5.3;
            }
        }
        return total;
    }

    public String getCurrentPortNum() {
        return currentPortNum;
    }

    @Override
    public void setCurrentPortNum(String currentPortNum) throws IOException {
        this.currentPortNum = currentPortNum;
        save(currentPortNum,this.ID,this);

    }
}