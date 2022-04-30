package com.example.project.pojo;

public class Country implements POJO{
    public int countryId;
    public String countryName;

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }
    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}'+"\n";
    }
}
