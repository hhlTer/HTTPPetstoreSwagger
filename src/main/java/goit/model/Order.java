package goit.model;

import goit.model.status.OrderStatus;

import java.time.Instant;

public class Order {
    public int id;
    public int petId;
    public int quantity;
    public Instant shipDate;
    public OrderStatus status;
    public boolean complete = false;
}
