package ru.vtvhw.contacts.dao;

import ru.vtvhw.contacts.model.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryContactDao implements ContactDao {
    private final Map<Long, Contact> contactIdMap;
    private long nextId = 1L;

    public InMemoryContactDao() {
        this.contactIdMap = new HashMap<>();

//        var testContact = new Contact();
//        testContact.setSurname("Ivanov");
//        testContact.setFirstname("Ivan");
//        testContact.setPhoneNumber("89031234567");
//        testContact.setEmail("test@mail.ru");
//        addContact(testContact);
//
//        contactIdMap.entrySet().forEach(System.out::println);
    }

    @Override
    public void addContact(Contact contact) {
        contact.setId(nextId++);
        contactIdMap.put(contact.getId(), contact);
    }

    @Override
    public Optional<Contact> findContact(long contactId) {
        return Optional.ofNullable(contactIdMap.get(contactId));
    }

    @Override
    public Contact getContact(long contactId) {
        return findContact(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found! Id: " + contactId));
    }

    @Override
    public void updateContact(Contact contact) {
        var contactFromStorage = getContact(contact.getId());
        contactFromStorage.setFirstname(contact.getFirstname());
        contactFromStorage.setSurname(contact.getSurname());
        contactFromStorage.setPhoneNumber(contact.getPhoneNumber());
        contactFromStorage.setEmail(contact.getEmail());
    }

    @Override
    public void deleteContact(long contactId) {
        contactIdMap.remove(contactId);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactIdMap.values().stream().toList();
    }
}