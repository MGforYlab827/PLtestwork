public class Circle {
    private float x, y;
    private float radius;

    public Circle(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int result(Point point) {
        if (Math.pow((point.getX() - x), 2) + Math.pow((point.getY() - y), 2) < radius * radius) {
            return 1;
        } else if (Math.pow((point.getX() - x), 2) + Math.pow((point.getY() - y), 2) == radius * radius) {
            return 0;
        } else {
            return 2;
        }
    }
}