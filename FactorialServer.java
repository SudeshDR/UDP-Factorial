import java.net.*;


public class FactorialServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1200);
            byte[] buffer = new byte[1024];


            while (true) {
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(requestPacket);


                String receivedData = new String(requestPacket.getData(), 0, requestPacket.getLength());
                int number = Integer.parseInt(receivedData.trim());
                int factorialResult = factorial(number);


                String result = String.valueOf(factorialResult);
                byte[] responseBuffer = result.getBytes();
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();


                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, clientAddress, clientPort);
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
