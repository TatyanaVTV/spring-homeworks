package ru.vtvhw.contacts.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtvhw.contacts.controller.ContactDto;
import ru.vtvhw.contacts.dao.ContactDao;
import ru.vtvhw.contacts.model.Contact;

import java.util.List;
import java.util.Optional;

@Service
public class ContactFacade {
    private final ContactDao contactDao;

    @Autowired
    public ContactFacade(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public ContactDto createContact(String surname, String firstname, String phoneNumber, String email) {
        var newContact = new Contact();
        newContact.setSurname(surname);
        newContact.setFirstname(firstname);
        newContact.setPhoneNumber(phoneNumber);
        newContact.setEmail(email);
        contactDao.addContact(newContact);
        return new ContactDto(newContact);
    }

    public ContactDto getContact(long contactId) {
        var contact = contactDao.getContact(contactId);
        return new ContactDto(contact);
    }

    public Optional<Contact> findContact(long contactId) {
        return contactDao.findContact(contactId);
    }

    public void updateContact(long contactId, ContactDto contactDto) {
        var modifiedContact = contactDao.getContact(contactId);
        modifiedContact.setSurname(contactDto.getSurname());
        modifiedContact.setFirstname(contactDto.getFirstname());
        modifiedContact.setPhoneNumber(contactDto.getPhoneNumber());
        modifiedContact.setEmail(contactDto.getEmail());
        contactDao.updateContact(modifiedContact);
    }

    public void deleteContact(long contactId) {
        contactDao.deleteContact(contactId);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
