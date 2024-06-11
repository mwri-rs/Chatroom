import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static HashSet<PrintWriter> outputStreams = new HashSet<>();
    public static void broadcastMessage(String message) {
        for (PrintWriter writer : outputStreams) {
            writer.println(message);
            writer.flush();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Chat server is running on port " + 1234);

            while (true) {
                Socket socket = serverSocket.accept();

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                outputStreams.add(printWriter);

                Thread clientHandler = new Thread(new ClientHandling(socket, printWriter));
                clientHandler.start();

                System.out.println("New client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandling implements Runnable {
    private Socket clientSocket;
    private PrintWriter writer;

    public ClientHandling(Socket clientSocket, PrintWriter writer) {
        this.clientSocket = clientSocket;
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientName = bufferedReader.readLine();
            System.out.println("New client named " + clientName);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String formattedMessage = clientName + ": " + line;
                System.out.println(formattedMessage);
                Server.broadcastMessage(formattedMessage);
            }

            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}