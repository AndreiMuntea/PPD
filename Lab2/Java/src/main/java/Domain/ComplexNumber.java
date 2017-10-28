package Domain;

public class ComplexNumber extends Number<ComplexNumber>{

    public ComplexNumber(Double real, Double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(){
        this.real = 0.0;
        this.imaginary = 0.0;
    }

    @Override
    public ComplexNumber add(ComplexNumber other){
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    @Override
    public ComplexNumber multiply(ComplexNumber other){
        Double re = this.real * other.real - this.imaginary * other.imaginary;
        Double im = this.real * other.imaginary + this.imaginary * other.real;

        return new ComplexNumber(re, im);
    }

    @Override
    public ComplexNumber division(ComplexNumber other) {
        ComplexNumber d = this.multiply(other.conjugate());
        ComplexNumber n = other.multiply(other.conjugate());

        return new ComplexNumber(d.real/n.real, d.imaginary/n.real);
    }

    @Override
    public ComplexNumber castAsValue(Double value) {
        return new ComplexNumber(value, 0.0);
    }

    public ComplexNumber conjugate()
    {
        return new ComplexNumber(real, -imaginary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexNumber that = (ComplexNumber) o;

        if (real != null ? !real.equals(that.real) : that.real != null) return false;
        return imaginary != null ? imaginary.equals(that.imaginary) : that.imaginary == null;
    }

    @Override
    public int hashCode() {
        int result = real != null ? real.hashCode() : 0;
        result = 31 * result + (imaginary != null ? imaginary.hashCode() : 0);
        return result;
    }

    private Double real;
    private Double imaginary;
}
