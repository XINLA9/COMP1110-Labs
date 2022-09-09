package comp1110.lab6;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @author XIN
 * @create 2022-08-30 22:45
 */

public class Board extends Application
{
//    方法：查找最近的三角形
    ArrayList<Triangle> boardTriangleList = new ArrayList<>();
    Triangle findNearestTriangle(double x, double y)
    {
        int triangleNum = 0;
        double distinct = boardTriangleList.get(0).distinct(x,y);

        for(int count = 0; count < boardTriangleList.size(); count++)
        {
            if (distinct >= boardTriangleList.get(count).distinct(x, y))
            {
                triangleNum = count;
                distinct = boardTriangleList.get(count).distinct(x, y);
            }
        }
        return boardTriangleList.get(triangleNum);
    }
//    方法：高亮最近的三角形
    Triangle highlighted = null;
    void highlightNearestTriangle(double x, double y)
    {
        if (highlighted != null)
        {
            highlighted.setFill(Color.LIGHTGRAY);
        }
        highlighted = findNearestTriangle(x,y);
        highlighted.setFill(Color.GREEN);
    }

//    内部类:等边三角形
    public class Triangle extends Polygon
    {
//        成员变量：等边三角形
        private double x;
        private double y;
        private double side;
        private double height;

//        方法：距离测量
        public double distinct(double x, double y)
        {
            double distinct = Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
            return distinct;
        }

//        构造函数：等边三角形
        public Triangle(double x, double y, double side)
        {
            this.x = x;
            this.y = y;
            this.side = side;

//          求出等边三角形的高
            this.height = Math.sqrt((side * side) - ((side / 2)*( side / 2 )));

            getPoints().addAll
                    (
                  0.0, -height / 2,
                    side / 2,  height / 2,
                    -side / 2, height / 2
                 );

            setLayoutX(x);

            setLayoutY(y);

            setFill(Color.LIGHTGRAY);
        }
    }

//        内部类：可拖拽的三角形
    public class DraggableTriangle extends Triangle
    {
//        成员变量：可拖拽三角形
        private Board board;
        private double mouseX;
        private double mouseY;

//        构造函数：可拖拽三角形
        public DraggableTriangle(double x, double y, double size, Board board)
        {
            super(x,y,size);
            setFill(Color.RED);
            this.board = board;

            this.setOnMousePressed(event -> {
                setLayoutX(event.getSceneX());
                setLayoutY(event.getSceneY());
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                toFront();
            });

            this.setOnMouseDragged(event -> {
                setLayoutX(event.getSceneX());
                setLayoutY(event.getSceneY());
                this.mouseX = event.getSceneX();
                this.mouseY = event.getSceneY();
                board.highlightNearestTriangle(mouseX, mouseY);
            });

            this.setOnMouseReleased(event ->{
                Triangle nearestTriangle = board.findNearestTriangle(mouseX,mouseY);
                setRotate(nearestTriangle.getRotate());
                setLayoutX(nearestTriangle.getLayoutX());
                setLayoutY(nearestTriangle.getLayoutY());
            });
        }

    }

    @Override
    public void start(Stage stage) throws Exception
    {
//        设置stage
        var sceneWeight = 600;
        var SceneHeight = 519;
        stage.setTitle("Board");
        var root = new Group();
        var scene = new Scene(root,sceneWeight,SceneHeight);
        stage.setScene(scene);

        double x = 100;
        double y = 86.6;

       for(int countY = 0; countY < 3; countY++)
        {
            for(int countX = 0; countX < 5; countX++)
            {
                if (countX % 2 == 0)
                {
                    Triangle tri = new Triangle(x,y,196);
                    root.getChildren().add(tri);
                    boardTriangleList.add(tri);
                }
                else
                {
                    Triangle tri = new Triangle(x,y,196);
                    tri.setRotate(180);
                    root.getChildren().add(tri);boardTriangleList.add(tri);
                    boardTriangleList.add(tri);
                }
                x = x + 100;
            }
            x = 100;
            y = y + 173.2;
        }

//       创建可拖拽三角形
       DraggableTriangle draggableTriangle = new DraggableTriangle(300,260,200,this);
       root.getChildren().add(draggableTriangle);

       stage.show();
    }
}
