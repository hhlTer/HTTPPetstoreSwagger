package goit.client.model;

public class Tag {
    public long id;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String name;
}
