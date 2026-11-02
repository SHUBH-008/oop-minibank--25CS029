package model;

import model.annotation.Id;
import model.annotation.MaxLength;

public class Customer {
    @Id
    private final String customerId;

    private String name;
    private String email;

    @MaxLength(value = 10, message = "mobile number must not exceed 10 digits")
    private String mobile;

    private static long customerCounter = 100;

    public Customer(String name, String email, String mobile) {
        this.customerId = "CUST" + (++customerCounter);
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return customerId + " | " + name + " | " + email + " | " + mobile;
    }
}
