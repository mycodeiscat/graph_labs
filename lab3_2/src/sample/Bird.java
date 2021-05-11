package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bird extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 720);

        scene.setFill(Color.LIGHTBLUE);

        // тіло
        Arc arc = new Arc(100, 100, 50, 50, 0, 180);
        arc.setFill(Color.rgb(216, 31, 45));
        arc.setStroke(Color.rgb(35, 18, 21));
        arc.setStrokeWidth(4);
        arc.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(arc);

        //нижня частина тіла
        Arc arc2 = new Arc(100, 100, 50, 43, 180, 180);
        arc2.setFill(Color.rgb(216, 31, 45));
        arc2.setStroke(Color.rgb(35, 18, 21));
        arc2.setStrokeWidth(4);
        arc2.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(arc2);

        // плями

        Ellipse ell1 = new Ellipse(107, 115, 8, 9);
        Ellipse ell2 = new Ellipse(90, 112, 4, 8);
        Ellipse ell3 = new Ellipse(79, 118, 3, 4);

        ell1.setFill(Color.rgb(155, 10, 13));
        ell2.setFill(Color.rgb(155, 10, 13));
        ell3.setFill(Color.rgb(155, 10, 13));

        root.getChildren().add(ell1);
        root.getChildren().add(ell2);
        root.getChildren().add(ell3);


        // tmp


        QuadCurve quad = new QuadCurve();
        quad.setStartX(70);
        quad.setStartY(135);
        quad.setEndX(127);
        quad.setEndY(135);
        quad.setControlX(100);
        quad.setControlY(110);
        quad.setFill(Color.rgb(232, 209, 193));

        QuadCurve quad2 = new QuadCurve();
        quad2.setStartX(70);
        quad2.setStartY(134);
        quad2.setEndX(127);
        quad2.setEndY(135);
        quad2.setControlX(100);
        quad2.setControlY(150);
        quad2.setFill(Color.rgb(232, 209, 193));

        root.getChildren().add(quad);
        root.getChildren().add(quad2);


        // око
        Rotate rotate = new Rotate();
        Arc arc3 = new Arc(112, 100, 11, 10, 0, 360);
        arc3.setFill(Color.WHITE);
//        arc3.setStroke(Color.BLACK);
//        arc3.setStrokeWidth(1);
//        arc3.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(arc3);

        // око 2
        Arc arc4 = new Arc(125, 100, 10, 10, 0, 360);
        arc4.setFill(Color.WHITE);
//        arc4.setStroke(Color.BLACK);
//        arc4.setStrokeWidth(1);
//        arc4.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(arc4);

        // eyeballs
        Circle eye1 = new Circle(113, 100, 3, Color.BLACK);
        Circle eye2 = new Circle(124, 100, 3, Color.BLACK);
        root.getChildren().add(eye1);
        root.getChildren().add(eye2);
        // eyebrows
        Polygon polygon = new Polygon(88, 88,
                91, 81,
                115, 91,
                142, 81,
                145, 88,
                115, 94);
        polygon.setFill(Color.BLACK);
        root.getChildren().add(polygon);

        // клюв
        Polygon triangle1 = new Polygon(105, 120,
                117, 108,
                135, 120);
        triangle1.setFill(Color.YELLOW);
        triangle1.setStroke(Color.BLACK);
        triangle1.setStrokeWidth(1);
        triangle1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        triangle1.setStrokeLineCap(StrokeLineCap.ROUND);
        triangle1.setStrokeType(StrokeType.OUTSIDE);

        Polygon triangle2 = new Polygon(106, 121,
                115, 131,
                130, 121);
        triangle2.setFill(Color.YELLOW);
        triangle2.setStroke(Color.BLACK);
        triangle2.setStrokeWidth(1);
        triangle1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        triangle2.setStrokeLineCap(StrokeLineCap.ROUND);
        triangle2.setStrokeType(StrokeType.OUTSIDE);

        root.getChildren().add(triangle1);
        root.getChildren().add(triangle2);

        // tail
        Polygon tail = new Polygon(48, 107,
                43, 110,
                40, 105,
                48, 103,
                33, 100,
                38, 93,
                48, 100,
                42, 93,
                44, 87,
                48, 93
        );
        tail.setFill(Color.rgb(35, 18, 21));

        root.getChildren().add(tail);

        // хохолок
        CubicCurve cubicCurve1 = new CubicCurve();

        //Setting properties to cubic curve
        cubicCurve1.setStartX(110);
        cubicCurve1.setStartY(53);
        cubicCurve1.setControlX1(70);
        cubicCurve1.setControlY1(25);
        cubicCurve1.setControlX2(50);
        cubicCurve1.setControlY2(53);
        cubicCurve1.setEndX(120);
        cubicCurve1.setEndY(50);
        cubicCurve1.setFill(Color.rgb(197, 33, 44));
        root.getChildren().add(cubicCurve1);

        CubicCurve cubicCurve2 = new CubicCurve();
        cubicCurve2.setStartX(120);
        cubicCurve2.setStartY(53);
        cubicCurve2.setControlX1(70);
        cubicCurve2.setControlY1(35);
        cubicCurve2.setControlX2(50);
        cubicCurve2.setControlY2(53);
        cubicCurve2.setEndX(130);
        cubicCurve2.setEndY(50);
        cubicCurve2.setFill(Color.rgb(197, 33, 44));
        root.getChildren().add(cubicCurve2);

        // Animation
        int cycleCount = 2;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(0.5);
        scaleTransition.setToY(0.5);


        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);


        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(time), root);
        translateTransition1.setFromX(0);
        translateTransition1.setFromY(500);
        translateTransition1.setToX(500);
        translateTransition1.setToY(0);
        translateTransition1.setCycleCount(cycleCount + 1);



        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                translateTransition1,
                scaleTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();


        primaryStage.setTitle("Angry Bird");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
