package goit.model;

public class User implements Entity{
    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;

    @Override
    public String getPatch() {
        return null;
    }
}
