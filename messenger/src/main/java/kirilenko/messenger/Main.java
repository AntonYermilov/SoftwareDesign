package kirilenko.messenger;


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static String[] input_args;

    /**
     * args[0] - имя пользователя
     * args[1] - порт пользователя
     * args[2] - адрес подключения
     * args[3] - порт подключения
     */
    public static void main(String[] args) {
        input_args = args;
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Sender sender;
        try {
            String author = input_args[0];
            int port = Integer.parseInt(input_args[1]);
            String server_addr = input_args[2];
            int server_port = Integer.parseInt(input_args[3]);
            sender = new Sender(author, port, server_addr, server_port);
            sender.create_connection();
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.err.println("Please, provide four argument! \'author port connection_address connection_port\'");
            return;
        }
        UI ui = new UI();
        ui.run(primaryStage, sender);
        Receiver.main(ui);
    }
}
