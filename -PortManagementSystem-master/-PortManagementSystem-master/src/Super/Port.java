package Super;
import java.io.*;
import java.util.*;
public class Port implements Serializable , Save {
    private String Name;
    private String ID;
    private Double StoringCapacity;
    private Double Longtitude;
    private Double Latitude;
    private boolean LandingAbility;
    public Port(){
        Name = null;
        ID = null;
        StoringCapacity = null;

    }
    public Port(String Name, String ID, Double StoringCapacity, Double Longtitude, Double Latitude, boolean LandingAbility){
        this.Name = Name;
        this.ID = ID;
        this.LandingAbility = LandingAbility;
        this.Longtitude = Longtitude;
        this.Latitude = Latitude;
        this.StoringCapacity = StoringCapacity;
    }
    public String printAllFilesStartWith(String startWord,String filepath) {
        File folder = new File(filepath);
        File[] FileList = folder.listFiles();
        ArrayList<Object> results = new ArrayList<>();
        for (File f : FileList) {
            String name = f.getName();
            if (f.isFile() && name.startsWith(startWord)) {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                    Object obj = input.readObject();
                    results.add(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return "" + results;

    }
}
