package comp1110.lab6;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * @author XIN
 * @create 2022-08-30 22:45
 */

public class Board extends Application
{
//          等边三角形的内部类
    public class Triangle extends Polygon
    {
//        三角形中心的x坐标
        private double x;
//        三角形中心的y坐标
        private double y;
//        三角形的边长
        private double side;
//        三角形的高
        private double height;

        public double getHeight()
        {
            return height;
        }
        public double getX()
        {
            return x;
        }
        public double getY()
        {
            return y;
        }
        public double getSide()
        {
            return side;
        }

//        距离测量器方法
        public double distinct(double x, double y)
        {
            double distinct = Math.sqrt(Math.pow(x - this.x,2) + y * y);
            return distinct;
        }
//          等边三角形的构造函数
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

//        可拖拽三角形的内部类
    public class DraggableTriangle extends Triangle
    {
//        添加新的字段board
        private Board board = new Board();
//        添加鼠标
        private double mousex;
        private double mousey;

//        创建构造函数
        public <board> DraggableTriangle(double x, double y, double size, Board board)
        {
            super(x,y,size);
            setFill(Color.RED);
            this.board = board;

            this.setOnMousePressed(event -> {
                mousex = event.getSceneX();
                mousey = event.getSceneY();
                toFront();
            });

            this.setOnMouseDragged(event -> {
                setLayoutX(event.getSceneX());
                setLayoutY(event.getSceneY());
                mousex = this.mousex;
                mousey = this.mousey;
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
                }
                else
                {
                    Triangle tri = new Triangle(x,y,196);
                    tri.setRotate(180);
                    root.getChildren().add(tri);
                }
                x = x + 100;
            }
            x = 100;
            y = y + 173.2;
        }

//       创建可拖拽三角形
       DraggableTriangle draggableTriangle = new DraggableTriangle(300,260,200,this);
       root.getChildren().add(draggableTriangle);

       DraggableTriangle draggableTriangle1 = new DraggableTriangle(100,260,100,this);
       root.getChildren().add(draggableTriangle1);

       stage.show();
    }
}
