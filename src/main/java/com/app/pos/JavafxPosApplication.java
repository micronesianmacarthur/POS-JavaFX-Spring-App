package com.app.pos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavafxPosApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        // initialize Spring Boot context
        applicationContext = new SpringApplicationBuilder(PosApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // this method is called after init() and is where the primary JavaFX stage is set up.
        // FxWeaver will be used here to load the initial FXML view.
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        // close Spring Boot context gracefully
        applicationContext.close();
        Platform.exit();
    }

    public static void main(String args) {
        launch(args); // launch JavaFX application
    }

}
