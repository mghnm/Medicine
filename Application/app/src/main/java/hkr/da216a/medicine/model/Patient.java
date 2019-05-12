package hkr.da216a.medicine.model;

import java.io.Serializable;

public class Patient implements Serializable {
    public final static String TABLE_NAME = "patient";
    public final static String PERSONAL_NUMBER_COLUMN = "personal_number";
    public final static String NAME_COLUMN = "name";
    public final static String LAST_NAME_COLUMN = "last_name";
    public final static String AGE_COLUMN = "age";
    public final static String MARITAL_STATUS_COLUMN = "marital_status";
    public final static String GENDER_OOLUMN = "gender";
    public final static String WEIGHT_COLUMN = "weight";
    public final static String ADDRESS_COLUMN = "address";
    public final static String PHONE_NUMBER_COLUMN = "phone_number";
    public final static String EMAIL_COLUM = "email";

    private String personalNumber;
    private String name;
    private String lastName;
    private int age;
    private int martialStatus;
    private String gender;
    private double weight;
    private String address;
    private String phoneNumber;
    private String email;

    public Patient() {
    }

    public Patient(String personalNumber, String name, String lastName, int age, int martialStatus, String gender, double weight, String address, String phoneNumber, String email) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.martialStatus = martialStatus;
        this.gender = gender;
        this.weight = weight;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(int martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "personalNumber='" + personalNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", martialStatus=" + martialStatus +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
