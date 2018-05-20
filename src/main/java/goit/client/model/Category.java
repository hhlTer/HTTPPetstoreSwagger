package goit.client.model;

public class Category {
    public long id;
    public String name;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
