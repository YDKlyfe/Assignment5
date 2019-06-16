package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GenerateQRCode extends Application {


    public void start(Stage primaryStage) {
        try {

            Group root = new Group();
            Scene sc = new Scene(root, 512, 512);
            primaryStage.setTitle("QR Code");

            int[][] qr = new int[512][512];
            //Fill the matrix with 1's (whites)
            for(int i = 0; i < qr.length; i++)
                for(int j = 0; j < qr.length; j++)
                    qr[i][j] = 1;

            //TOP LEFT CORNER
            //Lines of the corner
            for(int j = 0; j < 120; j++)
                for(int i = 0; i < 20; i++)
                    qr[i][j] = 0;
            for(int i = 0; i < 120; i++)
                for(int j = 0; j < 20; j++)
                    qr[i][j] = 0;
            for(int i = 0; i < 120; i++)
                for(int j = 100; j < 120; j++)
                    qr[i][j] = 0;
            for(int j = 0; j < 120; j++)
                for(int i = 100; i < 120; i++)
                    qr[i][j] = 0;
            //Black square for top left corner
            for(int i = 40; i < 80; i++)
                for(int j = 40; j < 80; j++)
                    qr[i][j] = 0;


            //TOP RIGHT CORNER
            //Lines of the corner
            for(int j = qr.length-120-1; j < qr.length-1; j++)
                for(int i = 0; i < 20; i++)
                    qr[i][j] = 0;
            for(int i = 0; i < 120; i++)
                for(int j = qr.length-120-1; j < qr.length-100-1; j++)
                    qr[i][j] = 0;
            for(int i = 0; i < 120; i++)
                for(int j = qr.length-20-1; j < qr.length-1; j++)
                    qr[i][j] = 0;
            for(int j = qr.length-120-1; j < qr.length-1; j++)
                for(int i = 100; i < 120; i++)
                    qr[i][j] = 0;
            //Black square for top right corner
            for(int i = 40; i < 80; i++)
                for(int j = qr.length-80-1; j < qr.length-40-1; j++)
                    qr[i][j] = 0;

            //BOTTOM LEFT CORNER
            //Lines of the corner
            for(int j = 0; j < 120; j++)
                for(int i = qr.length-120-1; i < qr.length-100-1; i++)
                    qr[i][j] = 0;
            for(int i = qr.length-120-1; i < qr.length-1; i++)
                for(int j = 0; j < 20; j++)
                    qr[i][j] = 0;
            for(int i = qr.length-120-1; i < qr.length-1; i++)
                for(int j = 100; j < 120; j++)
                    qr[i][j] = 0;
            for(int j = 0; j < 120; j++)
                for(int i = qr.length-20-1; i < qr.length-1; i++)
                    qr[i][j] = 0;
            //Black square for bottom left corner
            for(int i = qr.length-80-1; i < qr.length-40-1; i++)
                for(int j = 40; j < 80; j++)
                    qr[i][j] = 0;

            //Paint the squares we have so far
            for(int i = 0; i < qr.length; i++)
            {
                for(int j = 0; j < qr[i].length; j++)
                {
                    Color c;
                    if(qr[i][j] == 1)
                        c = Color.WHITE;
                    else
                        c = Color.BLACK;

                    //Now, using the color, we draw the square and add it to the scene
                    Rectangle rectangle = new Rectangle(j,i,1,1); //col,row,width,height
                    rectangle.setFill(c);
                    root.getChildren().add(rectangle);
                }
            }

            //Create random squares at most until 512 - size (because the squares would be size * size) and paint them!
            int sizeSq = 30;
            for(int n = 0; n < 400; n++)
            {
                while(true)
                {
                    int x = (int) (Math.random()*(512-sizeSq));
                    int y = (int) (Math.random()*(512-sizeSq));
                    boolean possible = true;
                    //Check for already black boxes
                    if(qr[y][x] != 0 && qr[y+sizeSq][x] != 0 && qr[y][x+sizeSq] != 0 && qr[y+sizeSq][x+sizeSq] != 0 && n > 0)
                        possible = false;
                    //Check for not inside or touching principal corners
                    if(x < 121+sizeSq && y < 121+sizeSq) //top left corner
                        possible = false;
                    if(x > qr.length-121-sizeSq-1 && y < 121+sizeSq)
                        possible = false;
                    if(x < 121+sizeSq && y > qr.length-121-sizeSq-1)
                        possible = false;

                    if(possible)
                    {
                        //Set all corners equal to 0:
                        qr[y][x] = 0;
                        qr[y+sizeSq][x] = 0;
                        qr[y][x+sizeSq] = 0;
                        qr[y+sizeSq][x+sizeSq] = 0;

                        Rectangle rectangle = new Rectangle(x,y,sizeSq ,sizeSq); //col,row,width,height
                        rectangle.setFill(Color.BLACK);
                        root.getChildren().add(rectangle);
                        break;
                    }
                }
            }

            primaryStage.setScene(sc);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}