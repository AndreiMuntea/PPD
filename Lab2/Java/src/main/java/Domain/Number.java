package Domain;

public abstract class Number<T> {

    public abstract T castAsValue(Double value);

    public abstract T multiply(T other);

    public abstract T add(T other);

    public abstract T division(T other);
}
