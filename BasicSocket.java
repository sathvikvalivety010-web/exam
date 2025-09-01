import java.io.*;
import java.net.*;

public class BasicSocket {
    public static void main(String[] args) {
        try {
            String hostname = "example.com";
            int port = 80;  // HTTP port

            // Step 1: Resolve hostname
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println("Connecting to " + hostname + " [" + address.getHostAddress() + "] on port " + port);

            // Step 2: Create socket
            Socket socket = new Socket(address, port);
            System.out.println("Connected to server!");

            // Step 3: Send a basic HTTP GET request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET / HTTP/1.1");
            out.println("Host: " + hostname);
            out.println("Connection: close");
            out.println(); // blank line = end of request

            // Step 4: Read server response
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

            // Step 5: Close connection
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
