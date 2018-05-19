package goit.model;

import goit.model.status.OrderStatus;

import java.time.Instant;

public class Order implements Entity{
    public long id;
    public long petId;
    public long quantity;
    public Instant shipDate;
    public OrderStatus status;
    public boolean complete = false;

    @Override
    public String getPatch() {
        return null;
    }
}
