package ru.vtvhw.contacts.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vtvhw.contacts.model.Contact;

public class ContactDto {
    @JsonProperty("contactId")
    private final long id;
    @JsonProperty("surname")
    private final String surname;
    @JsonProperty("firstname")
    private final String firstname;
    @JsonProperty("phoneNumber")
    private final String phoneNumber;
    @JsonProperty("email")
    private final String email;

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.surname = contact.getSurname();
        this.firstname = contact.getFirstname();
        this.phoneNumber = contact.getPhoneNumber();
        this.email = contact.getEmail();
    }

    public ContactDto() {
        id = 0;
        surname = "";
        firstname = "";
        phoneNumber = "";
        email = "";
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
