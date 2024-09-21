package ru.vtvhw.contacts.dao;

import ru.vtvhw.contacts.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactDao {
    void addContact(Contact contact);
    Optional<Contact> findContact(long contactId);
    Contact getContact(long contactId);
    void updateContact(Contact contact);
    void deleteContact(long contactId);

    List<Contact> getAllContacts();
}
