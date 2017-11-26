package Domain;

import java.io.Serializable;

public class Product implements Serializable{
    private String name;
    private String measureUnit;
    private Double price;
    private Integer id;

    public Product(String name, String measureUnit, Double price, Integer id) {
        this.name = name;
        this.measureUnit = measureUnit;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + measureUnit + "," + price;
    }
}
