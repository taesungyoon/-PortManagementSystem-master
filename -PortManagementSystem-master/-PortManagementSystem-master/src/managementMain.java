package portmanagement;
import java.util.Scanner;

import login.login;
import member.UserDataSet;

public class managementMain {
    Scanner scan = new Scanner(System.in);

    public managementMain() {

    }

    public void start() {
        login.userName = strInput("Enter your ID");
        login.password = strInput("Enter your Password");
        login.role = strInput("Enter 'admin' if you are an admin and enter 'portManager' if you are a port manager.");

        UserDataSet.setMemberList();
        while (true) {
            if (login.login() && login.userName.equals("admin1")) { //일반

                System.out.println("==============================PortManagement System==============================");
                String menu = strInput("메뉴[1.q, 2.w, 3.e, 4.r, 5.t, 6.y E 프로그램종료]");
                System.out.println("==========================================================================");
                switch (menu) {
                    case "E":
                        return; // 종료
                    case "1":
                        // 1번 메뉴 코드를 여기에 추가
                        break;
                    case "2":
                        // 2번 메뉴 코드를 여기에 추가
                        break;
                    case "3":
                        // 3번 메뉴 코드를 여기에 추가
                        break;
                    case "4":
                        // 4번 메뉴 코드를 여기에 추가
                        break;
                    case "5":
                        // 5번 메뉴 코드를 여기에 추가
                        break;
                    case "6":
                        start(); // 로그아웃
                        break;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
                System.out.println("");
            } else if (login.login() && login.userName.equals("portManager1")) {
                System.out.println("==============================PortManagement System==============================");
                String menu = strInput("메뉴[1.y, 2.t, 3.r, 4.e, 5.w, 6.q E 프로그램종료]");
                System.out.println("==========================================================================");
                switch (menu) {
                    case "E":
                        return; // 종료
                    case "1":
                        // 1번 메뉴 코드를 여기에 추가
                        break;
                    case "2":
                        // 2번 메뉴 코드를 여기에 추가
                        break;
                    case "3":
                        // 3번 메뉴 코드를 여기에 추가
                        break;
                    case "4":
                        // 4번 메뉴 코드를 여기에 추가
                        break;
                    case "5":
                        // 5번 메뉴 코드를 여기에 추가
                        break;
                    case "6":
                        start(); // 로그아웃
                        break;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
                System.out.println("");

            } else {
                System.out.println("Invalid input.");
            }

        }
    }

    // string input
    public String strInput(String input) {
        System.out.print(input + "=");
        return scan.next();
    }

    // int input
    public int intInput(String msg) {
        System.out.print(msg + "=");
        return scan.nextInt();
    }

    // start
    public static void main(String[] args) {
        new managementMain().start();
        System.out.println("시스템이 종료되었습니다.");
    }
}
