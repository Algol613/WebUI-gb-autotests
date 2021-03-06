package lesson4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Triangle {
    private int a;
    private int b;
    private int c;
    private Colour colour = Colour.WHITE;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calculatePerimeter() {
        checkSidesArePositive();
        checkValid();
        return a + b + c;
    }

    private void checkSidesArePositive() {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("The sides must be positive");
        }
    }

    private void checkValid() {
        long max = Math.max(Math.max(a, b), c);
        if (max >= (a + b + c - max)) {
            throw new IllegalArgumentException("The any two sides must be greater than other side");
        }
    }

    public void paint(Colour colour) {
        if (this.colour == colour) {
            throw new IllegalArgumentException("New colour must not be equal to old colour");
        }
        this.colour = colour;
    }

    public void paint(String colour) {
        paint(Colour.valueOf(colour));
    }

    public Triangle createSimilarTriangle(int coefficient) {
        checkSidesArePositive();
        checkValid();
        if (coefficient <= 0) {
            throw new IllegalArgumentException("coefficient must be positive");
        }
        return new Triangle(a * coefficient, b * coefficient, c * coefficient);
    }

    public List<Triangle> createSimilarTriangles(int fromCoefficient, int toCoefficient) {
        checkSidesArePositive();
        checkValid();
        if (fromCoefficient <= 0 || toCoefficient <= 0) {
            throw new IllegalArgumentException("coefficient must be positive");
        }
        if (fromCoefficient > toCoefficient) {
            throw new IllegalArgumentException("toCoefficient must be greater than fromCoefficient");
        }
        List<Triangle> triangleList = new ArrayList<>();
        for (int i = fromCoefficient; i < toCoefficient + 1; i++) {
            triangleList.add(new Triangle(a * i, b * i, c * i));
        }
        return triangleList;
    }

    public static void  createTriangleArea(String[] args)
    {
        double a;

        a = triangleArea(3, 3, 3);
        System.out.println("A triangle with sides 3,3,3 has an area of:" + a);

        a = triangleArea(3, 4, 5);
        System.out.println("A triangle with sides 3,4,5 has an area of:" + a);

        a = triangleArea(9, 9, 9);
        System.out.println("A triangle with sides 9,9,9 has an area of:" + a );

    }

    public static double triangleArea( int a, int b, int c )
    {
        double s=(((a+b+c)/2)*((a+b+c)/2-a)*((a+b+c)/2-b)*((a+b+c)/2-c));
        return Math.sqrt(s);
    }

    public int calculatetriangleArea() {
        double s=(((a+b+c)/2)*((a+b+c)/2-a)*((a+b+c)/2-b)*((a+b+c)/2-c));
        return (int) Math.sqrt(s);
    }
}

