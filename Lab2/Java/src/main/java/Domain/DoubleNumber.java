package Domain;

public class DoubleNumber extends Number<DoubleNumber> {

    public DoubleNumber(Double value){
        this.data = value;
    }

    public DoubleNumber() {
        this.data = 0.0;
    }

    @Override
    public DoubleNumber multiply(DoubleNumber other) {
        return new DoubleNumber(data * other.data);
    }

    @Override
    public DoubleNumber add(DoubleNumber other) {
        return new DoubleNumber(data + other.data);
    }

    @Override
    public DoubleNumber division(DoubleNumber other) {
        return new DoubleNumber(this.data / other.data);
    }

    @Override
    public DoubleNumber castAsValue(Double value) {
        return new DoubleNumber(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleNumber that = (DoubleNumber) o;

        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    private Double data;
}
