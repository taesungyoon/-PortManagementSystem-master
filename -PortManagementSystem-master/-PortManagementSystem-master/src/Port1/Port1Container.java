package Port1;
import Super.Container;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Port1Container extends Container {
    private String ID;
    private String Type;
    private Double Weight;
    private Double consumptionFuel;
    Port1Container(){}
    protected ArrayList<String> Types = new ArrayList<>(List.of(new String[]{"dry storage", "open top", "open side", "refrigerated", "liquid"}));

    public Port1Container(String ID, String type, Double Weight) throws IOException{
        if(InTypes(type)){
            this.ID = ID;
            this.Type = type;
            this.Weight = Weight;
            save("Port1",""+this.ID,this);
        }else{
            System.out.println("Your type is not include in container's type.");
        }


    }

    @Override
    public Double getWeight() {
        return Weight;
    }

    public String getType() {
        return Type;
    }

    public Double getConsumptionFuel() {
        return consumptionFuel;
    }

    public String getID() {
        return ID;
    }

    @Override
    public void setType(String type) {
        if(InTypes(type) == true){
            this.Type = type;
        }
    }

    @Override
    public boolean InTypes(String Type) {
        return super.InTypes(Type);
    }

    public void SavePort1Container(String filepath, String ObjectID, Container container ){

    }

}


