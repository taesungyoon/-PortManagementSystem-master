import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class DistanceCalculator implements OperationProcessor {
    @Override
    public double performOperation(Port departurePort, Port arrivalPort) {
        double lat1 = departurePort.latitude();
        double lon1 = departurePort.longitude();
        double lat2 = arrivalPort.latitude();
        double lon2 = arrivalPort.longitude();

        // calculator logic
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // Convert distance to kilometers

        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}

class Calculator {
    public static void main(String[] args) {
        PortManager control = new PortManager();
        control.start();
        control.closeScanner();
    }
}
