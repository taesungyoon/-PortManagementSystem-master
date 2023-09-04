package login;

public class login {
    public static String userName;


    public static String password;
    public static String role;

    public static boolean login() {
        if (userName == "" || password == "") {
            System.out.println("Please enter your username and password.");
            return false;
        } else {
            if(userName.equals("admin1") && password.equals("1234") && role.equals("admin")) {
                return true;
            }else if(userName.equals("portManager1") && password.equals("5678") && role.equals("portManager")){
                return true;
            }else {
                return false;
            }
        }
    }
}
