package Requests;

import java.io.Serializable;

public class BuyRequest implements Serializable{
    private String clientName;
    private Integer productId;
    private Integer quantity;

    public BuyRequest(String clientName, Integer productId, Integer quantity) {
        this.clientName = clientName;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
