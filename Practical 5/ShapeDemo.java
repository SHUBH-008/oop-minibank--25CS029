abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle(r=" + radius + ")";
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle(w=" + width + ", h=" + height + ")";
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0.5 * base * height;
    }

    @Override
    public String toString() {
        return "Triangle(b=" + base + ", h=" + height + ")";
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        System.out.println("=== Polymorphism: Shape Areas Demo ===");

        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 8.0),
            new Circle(3.0),
            new Rectangle(2.0, 5.0)
        };

        double totalArea = 0;
        double largestArea = -1;
        Shape largestShape = null;

        for (Shape s : shapes) {
            double a = s.area();
            totalArea += a;
            System.out.printf("%-20s Area: %10.2f%n", s, a);

            if (a > largestArea) {
                largestArea = a;
                largestShape = s;
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("Total Area:   %10.2f%n", totalArea);
        System.out.printf("Largest Shape: %s with Area %.2f%n", largestShape, largestArea);
    }
}
