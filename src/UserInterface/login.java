package UserInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class login {
    public login(){}
    String username;
    String password;
    String role;
    String portNum;

    public String getRole() {
        return role;
    }

    public String getPortNum() {
        return portNum;
    }

    public login(String role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/";
            BufferedReader reader = new BufferedReader(new FileReader(path + "Data/PortManagers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(" ");
                this.portNum = lines[3];
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Validation(String role,String ID, String password){
        if(role.equals("Admin")){
            try {
                String projectRoot = System.getProperty("user.dir");
                String path = projectRoot + "/src/";
                BufferedReader reader = new BufferedReader(new FileReader(path + "Data/Admins.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] lines = line.split(" ");
                    if(role.equals(lines[0]) && ID.equals(lines[1]) && password.equals(lines[2])){
                        System.out.println("Login complete");
                        return true;
                    }
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Please check your ID again.");
            return false;
        }else{
            try {
                String projectRoot = System.getProperty("user.dir");
                String path = projectRoot + "/src/";
                BufferedReader reader = new BufferedReader(new FileReader(path + "Data/PortManagers.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] lines = line.split(" ");
                    if(role.equals(lines[0]) && ID.equals(lines[1]) && password.equals(lines[2])){
                        System.out.println("Login complete");
                        this.portNum = (lines[3]);
                        return true;
                    }
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Please check your ID again.");
            return false;
        }

    }

//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
}
