package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Lab 1");
        Scene scene = new Scene(root, 400, 250);
        scene.setFill(Color.ORANGE);

        int originX = 120;
        int originY = 90;
        int width = 155;
        int height = 45;

        Rectangle rect  = new Rectangle(originX, originY, width, height);
        rect.setFill(Color.AQUA);
        root.getChildren().add(rect);

        int wheel1_center = originX+Math.round(width/4);
        int wheel2_center = originX+Math.round(3*width/4);
        Circle wheel1 = new Circle(wheel1_center, originY+100, 20);
        Circle wheel2 = new Circle(wheel2_center, originY+100, 20);

        Polygon triangle = new Polygon(originX, originY, originX+50, originY, originX, originY-50);
        triangle.setFill(Color.AQUA);

        wheel1.setFill(Color.BLUEVIOLET);
        wheel2.setFill(Color.BLUEVIOLET);

        Line line1 = new Line(wheel1_center, originY+100, wheel2_center, originY+height);
        Line line2 = new Line(wheel2_center, originY+100, wheel1_center, originY+height);
        Line line3 = new Line(originX+width, originY, originX+width+30, originY-30);
        line1.setStrokeWidth(0.5);
        line2.setStrokeWidth(0.5);
        line3.setStrokeWidth(0.7);

        root.getChildren().add(line1);
        root.getChildren().add(line2);
        root.getChildren().add(line3);
        root.getChildren().add(wheel1);
        root.getChildren().add(wheel2);
        root.getChildren().add(triangle);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
