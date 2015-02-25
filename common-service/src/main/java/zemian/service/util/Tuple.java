/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

import java.util.Objects;

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
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.a);
        hash = 23 * hash + Objects.hashCode(this.b);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuple<?, ?> other = (Tuple<?, ?>) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        return true;
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
