package transferring;

import java.io.*;
import java.net.Socket;

public class Transfer {
    private final String HOST;
    private final int PORT;
    private Socket socket;

    public Transfer(String host, int port) {
        this.HOST = host;
        this.PORT = port;
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

    public void transfer(Request data) throws IOException, ClassNotFoundException {
        try {
            connect();
        } catch (IOException e) {
            System.out.println("Server is not available to connect");
            return;
        }
        send(data);
        Response response = (Response) receive();
        response.printMessage();
        socket.close();
    }
}