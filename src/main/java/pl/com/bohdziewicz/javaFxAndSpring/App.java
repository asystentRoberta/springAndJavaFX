package pl.com.bohdziewicz.javaFxAndSpring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class App extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent parent;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void init() throws Exception{
        springContext = SpringApplication.run(App.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(getClass().getResource("/fxml/sample.fxml"));
        parent = fxmlLoader.load();

        primaryStage.setTitle("Hi, again.");
        Scene scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        springContext.stop();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {

        return new CommandLineRunner() {
            @Override public void run(String... args) throws Exception {

                System.out.println("Hi!");
            }
        };
    }
}
