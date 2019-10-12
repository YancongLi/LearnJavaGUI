import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {//The controller in MVC

    private Circle[] circles;//The model/data in MVC
    private AlgoFrame frame;//The view in MVC

    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this.circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (R + (Math.random() * (sceneWidth - 2*R)));
            int y = (int) (R + (Math.random() * (sceneHeight - 2*R)));
            int vx = (int)(Math.random()*11 - 5);
            int vy = (int)(Math.random()*11 - 5);
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            this.frame.addKeyListener(new AlgoKeyListener());
            this.frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run(sceneWidth, sceneHeight);
            }).start();
        });
    }

    private void run(int sceneWidth, int sceneHeight) {
        while (true) {
            //render data:
            frame.render(circles);
            AlgoVisHelper.pause(20);
            //update data:
            if (isAnimated) {
                for (Circle circle: circles) {
                    circle.move(0, 0, sceneWidth, sceneHeight);
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            int menuBarHeight = frame.getBounds().height- frame.getCanvasHeight();
            e.translatePoint(0, -menuBarHeight);
//            System.out.println(e.getPoint());
            for (Circle circle: circles) {
                if (circle.contains(e.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
