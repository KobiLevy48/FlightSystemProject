package pojo;

public class Administrator implements POJO{
    public int administratorId;
    public String firstName;
    public String lastName;
    public long userId;

    public Administrator(int administratorId, String firstName, String lastName, long userId) {
        this.administratorId = administratorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "administratorId=" + administratorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                '}'+"\n";
    }
}
