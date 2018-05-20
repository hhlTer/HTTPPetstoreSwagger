package goit.client.model;

public class User implements Entity{
    public long id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;

    private String path = "/v2/user";

    @Override
    public String getPatch() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
