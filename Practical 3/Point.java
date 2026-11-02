import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        System.out.println("=== Distinct Points Demo ===");

        Point[] points = {
            new Point(1, 2),
            new Point(3, 4),
            new Point(1, 2),
            new Point(5, 6),
            new Point(3, 4)
        };

        System.out.print("Points array: ");
        for (Point p : points) {
            System.out.print(p + " ");
        }
        System.out.println();

        int distinctCount = 0;
        for (int i = 0; i < points.length; i++) {
            boolean alreadyAppeared = false;
            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    alreadyAppeared = true;
                    break;
                }
            }
            if (!alreadyAppeared) {
                distinctCount++;
            }
        }

        System.out.println("Distinct: " + distinctCount);
    }
}
