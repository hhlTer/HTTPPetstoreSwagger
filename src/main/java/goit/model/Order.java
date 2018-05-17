package goit.model;

import goit.model.status.OrderStatus;

import java.time.Instant;

public class Order implements Entity{
    public int id;
    public int petId;
    public int quantity;
    public Instant shipDate;
    public OrderStatus status;
    public boolean complete = false;

    @Override
    public String getPatch() {
        return null;
    }
}
