package pl.edu.pjatk.pamo.skrawek.rest.model.accounts;

import java.util.UUID;

/**
 * Model class - used when calling REST API
 */
public class Child {
    private UUID id;
    private String name;
    private String surname;
    private String postalCode;
    private String city;
    private String streetNumber;
    private String pesel;
    private Gender gender;
    private String startDate;
    private String endDate;
    private String dateOfBirth;

    public Child() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", pesel='" + pesel + '\'' +
                ", gender=" + gender +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
