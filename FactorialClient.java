import java.io.*;
import java.net.*;


public class FactorialClient {
    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Enter a number to calculate its factorial:");


            while (true) {
                String number = userInput.readLine();
                byte[] buffer = number.getBytes();


                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length, serverAddress, 1200);
                socket.send(requestPacket);


                byte[] responseBuffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(responsePacket);


                String result = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Factorial: " + result);
               
                System.out.println("Enter another number to calculate its factorial (or type 'exit' to quit):");
                if (number.trim().equalsIgnoreCase("exit")) {
                    break;
                }
            }


            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

