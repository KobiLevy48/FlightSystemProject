package com.example.project.pojo;

public class Customer implements POJO{
    public long customerId;
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNo;
    public String creditCardNo;
    public long userId;

    public Customer(long customerId, String firstName, String lastName, String address, String phoneNo, String creditCardNo, long userId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.creditCardNo = creditCardNo;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", creditCardNo='" + creditCardNo + '\'' +
                ", userId=" + userId +
                '}'+"\n";
    }
}
