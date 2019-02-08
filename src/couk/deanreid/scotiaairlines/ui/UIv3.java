/*
 Scotia Airlines - HND Computer Science
 Program Version: 3.0 Alpha
 Code Version: 1.0A
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class UIv3 extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }   
    
    @Override
    public void start(Stage primaryStage) throws Exception {
     //   new JMetro(JMetro.Style.LIGHT).applyTheme(root);
        Parent root = FXMLLoader.load(getClass().getResource("UIv3.fxml"));
            primaryStage.setTitle("Scotia Airlines Version: 3.0 (Build 06219-SNAPSHOT)");
            primaryStage.setScene(new Scene(root, 425,400));
            primaryStage.show();
    }

}
