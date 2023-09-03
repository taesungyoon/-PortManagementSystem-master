package Super;

import java.io.*;
public interface Save {
    default void save(String PortNum, String ObjectID, Object obj) throws IOException{
        try {
//            String projectRoot = System.getProperty("-PortManagementSystem-master.dir");
            String path = projectRoot+"/src/" + PortNum + "/Data/" + ObjectID + ".txt";
            System.out.println(path);
//            File file = new File(path);
//            file.createNewFile();
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
//            fileOut.close();
            System.out.printf("Serialized data for %s is saved ", ObjectID);
            System.out.println(" ");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
