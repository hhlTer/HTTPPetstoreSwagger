package goit.client.model;

import goit.client.model.status.PetStatus;

import java.util.Arrays;

public class Pet implements Entity{
    public long id;
    public Category category;
    public String name;
    public String[] photoUrls;
    public Tag[] tags;
    public PetStatus status;
    public String getPatch(){
        return "/v2/pet";
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + Arrays.toString(photoUrls) +
                ", tags=" + Arrays.toString(tags) +
                ", status=" + status +
                '}';
    }
}
