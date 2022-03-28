package token;
/*
This class has the key to get into the various
facades from the anonymous class.
 */
public class LoginToken {
    private  long id;
    private  String name;
    private  String role;// admin,customer,airline.

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
