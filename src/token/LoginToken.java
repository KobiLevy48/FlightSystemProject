package token;

public class LoginToken {
    private  long id;
    private  String name;
    private  String role;

    public LoginToken(long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
