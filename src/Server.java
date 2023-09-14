import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedWriter buffWriter;
    private BufferedReader buffReader;
    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;
    private ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(1234);
    }

    public void start(int port) throws IOException {
        String msgFromClient;

        while (true) {
            serverSocket = new ServerSocket(port);
            try {
                System.out.println("Waiting for the user to connect...");
                socket = serverSocket.accept();
                System.out.println("User connected");
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                buffReader = new BufferedReader(inputStreamReader);
                buffWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    msgFromClient = buffReader.readLine();

                    if (msgFromClient.equalsIgnoreCase("exit"))
                        break;
                    System.out.println("Message: " + msgFromClient);
                    buffWriter.newLine();
                    buffWriter.flush();
                }
                stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() throws IOException {
        buffReader.close();
        buffWriter.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        serverSocket.close();
        socket.close();
    }
}