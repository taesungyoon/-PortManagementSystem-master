package member;

import java.lang.reflect.Member;
import java.util.HashMap;

public class    UserDataSet {
    public static HashMap<String, UserVO> UserList = new HashMap<String, UserVO>();
    public UserDataSet() {

    }

    public static void setMemberList() {
        UserList.put("admin", new UserVO("admin", "admin1", "1234"));
        UserList.put("portManager", new UserVO("portManager", "portManager1", "5678"));

    }
}
