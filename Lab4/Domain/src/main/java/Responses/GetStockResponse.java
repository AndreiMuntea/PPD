package Responses;

import Domain.Stock;

import java.io.Serializable;
import java.util.List;

public class GetStockResponse implements Serializable {
    private List<Stock> response;

    public GetStockResponse(List<Stock> response) {
        this.response = response;
    }

    public List<Stock> getResponse() {
        return response;
    }

    public void setResponse(List<Stock> response) {
        this.response = response;
    }
}
