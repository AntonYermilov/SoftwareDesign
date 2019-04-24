package kirilenko.messenger;

import javafx.scene.control.TextArea;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Sender {
    private final String QUEUE_NAME = "QUEUE";

    private String author;
    private String server_addr;
    private int port, server_port;

    private TextArea out;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;


    public Sender(String author, int port, String server_addr, int server_port) {
        this.author = author;
        this.server_addr = server_addr;
        this.port = port;
        this.server_port = port;

        this.factory = new ConnectionFactory();
        this.factory.setHost(server_addr);
    }

    public void create_connection() throws IOException, TimeoutException {
        connection = factory.newConnection();
        channel = connection.createChannel(1);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    }

    public void setOut(TextArea out) {
        this.out = out;
    }

    public void sendMessage(String content) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, content.getBytes());
        System.out.println(" [x] Sent '" + content + "'");
    }

    public void close_connection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

}
