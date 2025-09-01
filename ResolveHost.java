import java.net.*;

public class ResolveHost {
    public static void main(String[] args) {
        try {
            String hostname = "www.google.com";
            
            // Resolve hostname to IP
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println("Hostname: " + hostname);
            System.out.println("IP Address: " + address.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
