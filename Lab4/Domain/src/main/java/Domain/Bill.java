package Domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private Integer productId;
    private Integer quantity;
    private Double total;
    private Date date;
    private String clientName;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Bill(Integer productId, Integer quantity, Double total, String clientName) {
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.clientName = clientName;
        this.date = new Date();
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "[" + dateFormat.format(date) + "]" + " Client name: " + clientName + " bought " + quantity + " x " + productId + " ( " + total + ")";
    }
}
