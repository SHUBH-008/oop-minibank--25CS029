import java.util.Objects;

public class Fraction {
    private int num;
    private int den;

    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public Fraction(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        if (den < 0) {
            num = -num;
            den = -den;
        }
        int g = gcd(num, den);
        this.num = num / g;
        this.den = den / g;
    }

    public int getNum() {
        return num;
    }

    public int getDen() {
        return den;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return num == fraction.num && den == fraction.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }

    public static void main(String[] args) {
        System.out.println("=== Fraction Reducer & Equality Demo ===");

        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = new Fraction(3, 6);
        Fraction f4 = new Fraction(4, 8);
        Fraction fDiff = new Fraction(3, 4);

        System.out.println("f1 (1/2) prints: " + f1);
        System.out.println("f2 (2/4) prints: " + f2);
        System.out.println("f3 (3/6) prints: " + f3);
        System.out.println("f4 (4/8) prints: " + f4);
        System.out.println("fDiff (3/4) prints: " + fDiff);

        System.out.println("\nEquality checks:");
        System.out.println("f2.equals(f1): " + f2.equals(f1));
        System.out.println("f3.equals(f1): " + f3.equals(f1));
        System.out.println("f4.equals(f2): " + f4.equals(f2));
        System.out.println("fDiff.equals(f1): " + fDiff.equals(f1));

        System.out.println("HashCodes equal for f1 and f2: " + (f1.hashCode() == f2.hashCode()));
    }
}
