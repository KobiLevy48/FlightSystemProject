package pojo;

public class AirlineCompany implements POJO{
    public long airlineCompanyId;
    public String airlineCompanyName;
    public int countryId;
    public long userId;

    public AirlineCompany(long airlineCompanyId, String airlineCompanyName, int countryId, long userId) {
        this.airlineCompanyId = airlineCompanyId;
        this.airlineCompanyName = airlineCompanyName;
        this.countryId = countryId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AirlineCompany{" +
                "airlineCompanyId=" + airlineCompanyId +
                ", airlineCompanyName='" + airlineCompanyName + '\'' +
                ", countryId=" + countryId +
                ", userId=" + userId +
                '}'+"\n";
    }
}
