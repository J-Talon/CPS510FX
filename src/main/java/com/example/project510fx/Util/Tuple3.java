package Util;

public class Tuple3<A,B,C> {
    A a;
    B b;
    C c;

    public Tuple3(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
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


    public void display() {
        System.out.println(a+" "+b+" "+c);
    }

}
