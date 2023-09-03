package Super;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable, Save{
    private static final long serialVersionUID = 1L;
    private String ID;
    private String Type;
    private Double Weight;
    private Double consumptionFuel;

    protected ArrayList<String> Types = new ArrayList<>(List.of(new String[]{"dry storage", "open top", "open side", "refrigerated", "liquid"}));
    public Container(){
        this.ID = null;
        this.Type = null;
        this.Weight = null;
        this.consumptionFuel = null;
    }

    public void setType(String type) {
        if(InTypes(type)){
            this.Type = Type;
        }
    }

    public Double getWeight() {
        return Weight;
    }

    public boolean InTypes(String type) {
        if(Types.contains(type)){
            return true;
        }
        return false;
    }
    public Container(String ID, String type, Double Weight){
        if(InTypes(type)){
            this.ID = ID;
            this.Type = type;
            this.Weight = Weight;
        }


    }

    public Double getConsumptionFuel() {
        return consumptionFuel;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Container{" +
                "ID='" + ID + '\'' +
                ", Type='" + Type + '\'' +
                ", Weight=" + Weight +
                ", consumptionFuel=" + consumptionFuel +
                '}';
    }
}
