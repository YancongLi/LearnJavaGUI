import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        int N = 10;
        Circle[] circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (R + (Math.random() * (sceneWidth - 2*R)));
            int y = (int) (R + (Math.random() * (sceneHeight - 2*R)));
            int vx = (int)(Math.random()*11 - 5);
            int vy = (int)(Math.random()*11 - 5);
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                while (true) {
                    //render data:
                    frame.render(circles);
                    AlgoVisHelper.pause(20);
                    //update data:
                    for (Circle circle: circles) {
                        circle.move(0, 0, sceneWidth, sceneHeight);
                    }
                }
            }).start();
        });

    }
}
