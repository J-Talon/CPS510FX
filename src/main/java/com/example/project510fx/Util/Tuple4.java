package Util;

public class Tuple4<A,B,C,D> {
    A a;
    B b;
    C c;
    D d;

    public Tuple4(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public C getC() {
        return c;
    }

    public D getD() {
        return d;
    }

    public void display() {
        System.out.println(""+a+" "+b+" "+c+" "+d);
    }
}
