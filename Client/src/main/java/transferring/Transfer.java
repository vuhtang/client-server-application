package transferring;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Entity responsible for communication between the client and the server.
 * Sends request and receives responses.
 */
public class Transfer {
    private final String HOST;
    private final int PORT;
    private Token token;
    private Socket socket;

    public Transfer(String host, int port, Token token) {
        this.HOST = host;
        this.PORT = port;
        this.token = token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    private void connect() throws IOException {
        socket = new Socket(HOST, PORT);
        System.out.println("\nConnection established");
    }

    private void send(Request data) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(data);
        byteArrayOutputStream.writeTo(outputStream);
        System.out.println("Request sent");
    }

    private Object receive() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object o = objectInputStream.readObject();
        inputStream.close();
        System.out.println("Response received");
        return o;
    }

    public List<String> transfer(Request data) throws IOException, ClassNotFoundException {
        try {
            connect();
        } catch (IOException e) {
            System.out.println("Server is not available to connect");
            return null;
        }
        data.setToken(token);
        send(data);
        Response response = (Response) receive();
        socket.close();
        return response.getMessage();
    }

    public Token getToken() {
        return token;
    }
}