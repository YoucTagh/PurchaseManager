package com.youctagh.purchasemanager.frontend.application;

import com.youctagh.purchasemanager.frontend.controller.GeneralController;
import com.youctagh.purchasemanager.frontend.model.GeneralModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/youctagh/purchasemanager/frontend/view/general/GeneralView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Purchase Manager");
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        GeneralController controller = new GeneralController()
                .setGeneralModel(new GeneralModel())
                .setGeneralView(loader.getController());

        controller.init();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
