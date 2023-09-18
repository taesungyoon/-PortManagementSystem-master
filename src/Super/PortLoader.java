package Super;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public interface PortLoader {
    default List<Port> loadAllPorts() throws IOException, ClassNotFoundException {
        List<Port> ports = new ArrayList<>();
        String projectRoot = System.getProperty("user.dir");
        String dirPath = projectRoot + "/src/Data/";

        // 데이터 폴더에서 파일 목록 가져오기
        File dataDir = new File(dirPath);
        if (dataDir.exists() && dataDir.isDirectory()) {
            File[] portDirs = dataDir.listFiles();
            for (File portDir : portDirs) {
                if (portDir.isDirectory()) {
                    File[] filesInDir = portDir.listFiles();
                    for (File file : filesInDir) {
                        if (file.isFile() && file.getName().endsWith(".txt") && file.getName().equals(portDir.getName() + ".txt")) {
                            String portNum = portDir.getName();
                            String path = dirPath + portNum + "/" + file.getName();
                            FileInputStream fileIn = new FileInputStream(path);
                            ObjectInputStream in = new ObjectInputStream(fileIn);
                            Port port = (Port) in.readObject();
                            in.close();
                            fileIn.close();

                            ports.add(port);
                        }
                    }
                }
            }
        } else {
            System.out.println("There is no port directory");
        }

        return ports;
    }
}


