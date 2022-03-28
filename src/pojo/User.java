package pojo;

public class User implements POJO{
    public long userId;
    public String username;
    public String userPassword;
    public String email;
    public int userRole;

    public User(long userId, String username, String userPassword, String email, int userRole) {
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.email = email;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}'+"\n";
    }
}
