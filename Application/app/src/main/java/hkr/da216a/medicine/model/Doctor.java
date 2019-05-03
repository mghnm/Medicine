package hkr.da216a.medicine.model;

public class Doctor {

    private String socialSecurityNumber;
    private String name;
    private String lastName;

    public Doctor() {
    }

    public Doctor(String socialSecurityNumber, String name, String lastName) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
