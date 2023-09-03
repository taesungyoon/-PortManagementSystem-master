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
        PrintWriter output = new PrintWriter(new FileWriter("./src/Data/Users.txt", true));
        output.println(this.role + " "+ this.userName+ " "+ this.password);
        output.flush();
        output.close();

    }
    public void LoadAllUsers() throws IOException {
        LoadAllAdmins();
        LoadAllPortManagers();
    }
    public void LoadAllAdmins() throws IOException {
        ListByRoles("Admin");
    }
    public void  LoadAllPortManagers() throws IOException{
        ListByRoles("PortManager");
    }
    public void ListByRoles(String role) throws IOException{
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/Data/Users.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(" ");
                if (lines[0] == " "+role) {
                    String username = lines[1];
                    System.out.println(" "+role+ "User name: " + username);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ListAllTrips() throws IOException{
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Port1/Data/Histoy.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lines = line.split(":");
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

