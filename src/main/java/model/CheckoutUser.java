package model;

public class CheckoutUser {

    private String name;
    private String lastName;
    private String zipCode;


    public CheckoutUser setName(String name) {
        this.name = name;
        return this;
    }

    public CheckoutUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CheckoutUser setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipCode() {
        return zipCode;
    }
}
