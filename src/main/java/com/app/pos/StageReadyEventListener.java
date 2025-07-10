package com.app.pos;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

public class StageReadyEventListener implements ApplicationListener<StageReadyEvent> {

    private final ApplicationContext applicationContext;
    private final String applicationTitle;

    public StageReadyEventListener(ApplicationContext applicationContext,
                                   @Value("${app.title:POS JavaFX Spring App}") String applicationTitle) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainController.class); // load main FXML view
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(this.applicationTitle);
        stage.show();
    }

}
