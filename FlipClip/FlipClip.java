package FlipClip;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class FlipClip {
    static DisconnectServer connected;
    static ServerSocket serverSocket;
    static Socket socket;
    static boolean serverRunning = false;
    static boolean continueAcceptingConnections = true;

    // This method returns the IP address of the current machine
    private static String getOwnIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            List<NetworkInterface> interfaceList = Collections.list(interfaces);
            for (NetworkInterface intf : interfaceList) {
                if (!intf.isUp() || intf.isLoopback() || intf.isVirtual()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = intf.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLinkLocalAddress() && !addr.isLoopbackAddress() && !addr.isMulticastAddress()) {
                        return addr.toString().replace("/", "");
                    }
                }
            }
        } catch (Exception e) {
        }
        return (InetAddress.getLoopbackAddress()).toString().replace("/","");
    }

    public static void StartServer() {
        int PORT = 1234;
        System.out.println("Starting server...");
        try {
            //get ipv4 of wifi card
            String SERVER_IP = getOwnIpAddress();
            System.out.println("Your IP address is: " + SERVER_IP);
            System.out.println("Your port is: " + PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        while (continueAcceptingConnections) { // Check the flag before accepting new connections
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("Waiting for connection...");
                socket = serverSocket.accept();
                if (socket.isConnected())
                    connected = new DisconnectServer();
                    connected.setVisible(true);
                    BiDirectional.createFrame.dispose();
                    System.out.println("Connected to the client.");
                serverSocket.close();
                afterConnect(socket);
            } catch (Exception e) {
                System.out.println("Client disconnected.\nRestarting server in 1 second...");
                try {
                    Thread.sleep(1000);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                continue;
            }
        }
    }
    

    public static void closeServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static boolean ableToConnectWithIP(String ip) {
        try {
            Socket socket = new Socket(ip, 1234);
            socket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void CreateClient(String SERVER_IP, int PORT) {
        while (true) {
            try {
                System.out.println("Trying to connect...");
                Socket socket = new Socket(SERVER_IP, PORT);
                System.out.println("Connected to the server.");
                afterConnect(socket);
            } catch (Exception e) {
                System.out.println("Reconnecting in 1 seconds...");
                try {
                    Thread.sleep(1000);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void afterConnect(Socket sockeT) throws Exception {
        try {
            Socket socket = sockeT;
            Sender sender = new Sender(socket.getOutputStream());
            Receiver receiver = new Receiver(socket.getInputStream());

            Thread senderThread = new Thread(sender);
            Thread receiverThread = new Thread(receiver);
            senderThread.start();
            receiverThread.start();

            while (true) {
                if (!senderThread.isAlive() || !receiverThread.isAlive()) {
                    throw new Exception();
                }
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("Connection lost.");
        }
    }
}





