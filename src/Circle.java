import java.awt.*;

public class Circle {
    public int x, y;
    private int r;
    public int vx, vy;
    public  boolean isFilled = false;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    public void move(int minX, int minY, int maxX, int maxY) {
        this.x += this.vx;
        this.y += this.vy;

        this.checkCollision(minX, minY, maxX, maxY);
    }

    private void checkCollision(int minX, int minY, int maxX, int maxY) {
        if (x - r < minX) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxX) {
            x = maxX - r;
            vx = -vx;
        }
        if (y - r < minY) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxY) {
            y = maxY - r;
            vy = -vy;
        }
    }

    public boolean contains(Point point) {
        return Math.pow((x - point.x), 2) + Math.pow((y - point.y), 2) <= Math.pow(r, 2);
    }
}
