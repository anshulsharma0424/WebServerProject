//package SingleThreaded; // Omitting this

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException, IOException {
        int port = 8010; // Ensure it matches server
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        try (PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send message to server
            toSocket.println("Hello World from Client " + socket.getLocalSocketAddress());

            // Read response from server
            String response = fromSocket.readLine();
            System.out.println("Received from Server: " + response);
        }

        socket.close(); // Close connection
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
