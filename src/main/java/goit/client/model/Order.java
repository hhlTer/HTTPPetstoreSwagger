package goit.client.model;

import goit.client.model.status.OrderStatus;

import java.time.Instant;

public class Order implements Entity{
    public long id;
    public long petId;
    public long quantity;
    public Instant shipDate;
    public OrderStatus status;
    public boolean complete = false;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate=" + shipDate +
                ", status=" + status +
                ", complete=" + complete +
                '}';
    }

    @Override
    public String getPatch() {
        return "/v2/store/order";
    }
}
