package member;

public class UserVO {
    private String role;
    private String userName;
    private String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserVO() {

    }
    public UserVO(String role, String userName, String password){
        this.role=role;
        this.userName=userName;
        this.password=password;
    }
}


