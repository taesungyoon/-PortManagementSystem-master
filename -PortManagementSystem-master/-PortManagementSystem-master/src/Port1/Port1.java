package Port1;
import Super.*;

import java.io.IOException;
import java.io.Serializable;

public class Port1 extends Port{
    private static final long serialVersionUID = 12345L;
    private String name;
    private String ID;
    private Double latitude;
    private Double longitude;
    private Double storingCapacity;
    private boolean landingAbility;
    public Port1(){
        name = null;
        ID = null;
        storingCapacity = null;
    }
    public Port1(Double latitude,Double longitude, Double storingCapacity, boolean landingAbility) throws IOException {
        this.name = "호치민";   // 항구 이름 "호치민 항구"로 지정
        this.ID = "p0001";  // 항구 아이디 p0001로 지정
        this.latitude = 10.7688;
        this.longitude = 106.6958;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        save("Port1",this.ID,this);
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude(){
        return longitude;
    }

    public String Port1PrintAllFilesStartWith(String startWord){
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot+"/-PortManagementSystem-master/-PortManagementSystem-master/src/";
        return printAllFilesStartWith(startWord,path+"Port1/Data");
    }
    public void printAllTrucks(){
        System.out.println( Port1PrintAllFilesStartWith("T-"));
    }
    public void printAllContainers(){

        System.out.println(Port1PrintAllFilesStartWith("C-"));
    }
    public void printAllShips(){
        System.out.println(Port1PrintAllFilesStartWith("S-"));
    }
    public void printAllVehicles(){
        System.out.println("--------Trucks-------");
        System.out.println(Port1PrintAllFilesStartWith("T-"));
        System.out.println("--------Ships--------");
        System.out.println(Port1PrintAllFilesStartWith("S-"));
    }

}

