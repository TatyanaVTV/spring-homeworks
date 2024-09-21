package ru.vtvhw.contacts.model;

import java.util.Objects;

public class Contact {
    private long id;
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return String.format("[%d] %s %s, phone: %s, email: %s%n", id, surname, firstname, phoneNumber, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id
                && Objects.equals(firstname, contact.firstname)
                && Objects.equals(surname, contact.surname)
                && Objects.equals(phoneNumber, contact.phoneNumber)
                && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, phoneNumber, email);
    }
}