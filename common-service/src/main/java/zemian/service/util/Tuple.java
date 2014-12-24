/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

/**
 * A tuple of two elements.
 * 
 * @author zedeng
 */
public class Tuple<A, B> {
    private A a;
    private B b;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Tuple(a=" + a + ", b=" + b + ")";
    }
        
    public static <X, Y> Tuple<X, Y> tuple(X a, Y b) {
        Tuple<X, Y> result = new Tuple<>();
        result.setA(a);
        result.setB(b);
        return result;
    }
}
