import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    private String role;
    public User(){
        role = null;
        userName = null;
        password = null;
    }
    public User(String role,String userName, String password) throws IOException {
        this.role = role;
        this.userName = userName;
        this.password = password;
        SaveUser();
    }

    public String getRole() {
        return role;
    }

    private void SaveUser() throws IOException {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot+"/-PortManagementSystem-master/-PortManagementSystem-master/src/";
        PrintWriter output = new PrintWriter(new FileWriter(path+"Data/Users.txt", true));
        output.println(this.role + " "+ this.userName+ " "+ this.password);
        output.flush();
        output.close();

    }
    public void LoadAllUsers() throws IOException{
        LoadAllAdmins();
        LoadAllPortManagers();
    }
    public void LoadAllAdmins() throws IOException {
        ListByRoles("Admin");
    }

    public void LoadAllPortManagers() throws IOException {
        ListByRoles("PortManager");
    }

    public void ListByRoles(String role) throws IOException {
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/-PortManagementSystem-master/-PortManagementSystem-master/src/";
            BufferedReader reader = new BufferedReader(new FileReader(path + "Data/Users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(" ");
                if (role.equals(lines[0])) { // 역할(role)과 일치하는 사용자만 출력
                    String username = lines[1];
                    System.out.println(role + " User name: " + username);
                }
            }
            reader.close(); // 파일 읽기 완료 후 리소스 닫기
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListAllTrips() throws IOException {
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/-PortManagementSystem-master/-PortManagementSystem-master/src/" + "Port1/Data/History.txt";
            System.out.println(path);
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    System.out.println(line);
                    line = reader.readLine(); // 다음 줄 읽기
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

