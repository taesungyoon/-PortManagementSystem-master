package Super;

import UserInterface.Users.User;

public class Calculator {
    public static double calculateDistance(String PortNumA, String PortNumB, Vehicle vehicle) {
        User user = new User();

        Port portA = user.searchingPort(PortNumA);
        Port portB = user.searchingPort(PortNumB);

        double lat1 = portA.getLatitude();
        double lon1 = portA.getLongtitude();
        double lat2 = portB.getLatitude();
        double lon2 = portB.getLongtitude();

        double r = 6371; // Default radius

        if (vehicle instanceof Truck) {
            Port currentPort = getCurrentPort(vehicle);
            if (currentPort != null && currentPort.getLandingAbility()) {
                r = 1.8;
            } else {
                // 트럭이 못갈때
                return -1; // 거리 계산 안됌
            }
        } else if (vehicle instanceof Ship) {
            r= 2.34;
        }

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return r * c;
    }

    private static Port getCurrentPort(Vehicle vehicle) {
        // 로직을 구현해서 차량의 현재 포트를 가져옴
        // 현재 포트를 반환하거나 사용할 수 없는 경우 null을 반환합니다
        return null;
    }
}