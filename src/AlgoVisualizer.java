import java.awt.*;


public class AlgoVisualizer {//The controller in MVC
    
    private Circle[] circles;//The model/data in MVC
    private AlgoFrame frame;//The view in MVC

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
            for (Circle circle: circles) {
                circle.move(0, 0, sceneWidth, sceneHeight);
            }
        }
    }
}
