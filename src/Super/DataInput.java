package Super;

import java.io.IOException;

public class DataInput {
    public static void main(String[] args) {
        int numberOfPorts = 5; // 5개의 포트 데이터를 입력

        String[] portDataArray = new String[numberOfPorts]; // 포트 데이터를 저장하는 배열

        try {
            // 5개의 포트 데이터 입력
            inputData(portDataArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 배열에 저장된 데이터 출력 (나중에 파일로 저장할 수 있음)
        System.out.println("포트 데이터:");
        for (String portData : portDataArray) {
            System.out.println(portData);
        }
    }

    public static void inputData(String[] portDataArray) throws IOException {
        System.out.println("포트 데이터 입력을 시작합니다.");

        // 제공된 포트의 longitude와 latitude 데이터
        double[] longitudes = {105.8542, 108.2022, 103.9840, 107.1839, 106.6819};
        double[] latitudes = {21.0285, 16.0544, 10.2899, 20.9101, 20.8561};
        boolean[] landingAbilities = {true, false, true, false, true};

        // 5개의 포트 데이터 입력
        for (int i = 0; i < portDataArray.length; i++) {
            String portName = "Port" + (i + 1);
            String portNum = "PortNum" + (i + 1);
            double storingCapacity = 1000.0 * (i + 1); // 임의의 저장용량 데이터 (예시: 1000, 2000, ...)
            double longitude = longitudes[i]; // 제공된 longitude 데이터 사용
            double latitude = latitudes[i]; // 제공된 latitude 데이터 사용
            boolean landingAbility = landingAbilities[i];

            // Port 객체 생성
            Port port = new Port(portName, portNum, storingCapacity, longitude, latitude, landingAbility);

            // Port 객체를 문자열로 변환하여 배열에 저장
            portDataArray[i] = port.toString(); // toString() 메서드를 포맷에 맞게 수정
        }

        // 입력 프로세스 완료
        System.out.println("포트 데이터 입력이 완료되었습니다.");
    }
}

//public class DataInput {
//
//    public static void inputData(String[] portDataArray, String[] containerDataArray) throws IOException {
//        System.out.println("데이터 입력을 시작합니다.");
//
//        // 포트 정보 입력
//        String portName = "PortName1";
//        String portNum = "PortNum1";
//        double storingCapacity = 1000.0;
//        double longitude = 123.45;
//        double latitude = 67.89;
//        boolean landingAbility = true;
//
//        String portName2 = "PortName2";
//
//        // Port 객체 생성
//        Port port = new Port(portName, portNum, storingCapacity, longitude, latitude, landingAbility);
//
//        // Port 객체를 문자열로 변환하여 배열에 저장
//        portDataArray[0] = port.toString(); // toString() 메서드를 포맷에 맞게 수정
//
//        // 컨테이너 정보 입력
//        String containerId = "ContainerID1";
//        String containerType = "ContainerType1";
//        double containerWeight = 500.0;
//        String containerPortNum = "PortNum1";
//
//        // ContainerData 객체 생성
//        Container container = new Container(containerId, containerType, containerWeight, containerPortNum);
//
//        // ContainerData 객체를 문자열로 변환하여 배열에 저장
//        containerDataArray[0] = container.toString(); // toString() 메서드를 포맷에 맞게 수정
//
//        // 입력 프로세스 완료
//        System.out.println("데이터 입력이 완료되었습니다.");
//    }

//    public static void main(String[] args) {
//        String[] portDataArray = new String[1]; // 포트 데이터를 저장하는 배열
//        String[] containerDataArray = new String[1]; // 컨테이너 데이터를 저장하는 배열
//
//        try {
//            inputData(portDataArray, containerDataArray);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 배열에 저장된 데이터 출력 (나중에 파일로 저장할 수 있음)
//        System.out.println("포트 데이터: " + portDataArray[0]);
//        System.out.println("컨테이너 데이터: " + containerDataArray[0]);
//    }
//}
