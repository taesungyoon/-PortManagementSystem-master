package Super;

import java.io.*;
public interface Save {
    default void save(String PortNum, String ObjectID, Object obj) throws IOException {
        String projectRoot = System.getProperty("user.dir");
        String dirPath = projectRoot + "/src/Data/" + PortNum +"/";
        File Folder = new File(dirPath);
        if(!Folder.exists()){
            try{
                Folder.mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        String path = projectRoot + "/src/Data/" + PortNum  +"/"+ ObjectID + ".txt";

        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data for %s is saved ", ObjectID);
            System.out.println(" ");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}