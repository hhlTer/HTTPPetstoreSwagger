package goit.model;

import goit.model.status.PetStatus;

public class Pet {
    public int id;
    public Category category;
    public String name;
    public String[] photoUrls;
    public Tag[] tags;
    public PetStatus status;
}
