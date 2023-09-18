package Super;

import java.io.*;
public class Port implements Serializable , Save {
    private static final long serialVersionUID = 1L;
    private String Name;
    private String portNum;
    private Double StoringCapacity;
    private Double Longtitude;
    private Double Latitude;
    private boolean LandingAbility;
    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongtitude() {
        return Longtitude;
    }

    public String getPortNum() {
        return portNum;
    }

    public String getName() {
        return Name;
    }

    public Double getStoringCapacity() {
        return StoringCapacity;
    }
    public boolean getLandingAbility() {
        return LandingAbility;
    }
    public Port(){
        Name = null;
        portNum = null;
        StoringCapacity = null;

    }
    public Port( String Name, String PortNum,Double StoringCapacity, Double Longtitude, Double Latitude, boolean LandingAbility){
        this.Name = Name;
        this.portNum = PortNum;
        this.Longtitude = Longtitude;
        this.Latitude = Latitude;
        this.StoringCapacity = StoringCapacity;
        this.LandingAbility = LandingAbility;
        try {
            save(PortNum,PortNum,this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/src/Data/" + PortNum +"/";
        try {
            File file = new File(filePath + "/History.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public double TotalWeightCalculate(String portnum){
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portnum;
        File folder = new File(path);
        File[] fileList = folder.listFiles();
        double totalResult = 0.0;
        if (fileList != null) {
            for (File f : fileList) {
                String name = f.getName();
                if (f.isFile() && name.startsWith("C-")) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        Container c = (Container) input.readObject();
                        double weight = c.getWeight();
                        totalResult+=weight;
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return totalResult;
    }




    @Override
    public void save(String PortNum, String ObjectID, Object obj) throws IOException {
        Save.super.save(PortNum, ObjectID, obj);
    }

    @Override
    public String toString() {
        return "Port{" +
                "Name='" + Name + '\'' +
                ", portNum='" + portNum + '\'' +
                ", StoringCapacity=" + StoringCapacity +
                ", Longtitude=" + Longtitude +
                ", Latitude=" + Latitude +
                ", LandingAbility=" + LandingAbility +
                '}';
    }
}

