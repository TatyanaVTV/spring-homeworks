package ru.vtvhw.contacts.facade;

import org.junit.jupiter.api.Test;
import ru.vtvhw.contacts.controller.ContactDto;
import ru.vtvhw.contacts.dao.InMemoryContactDao;
import ru.vtvhw.contacts.model.Contact;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ContactFacadeTest {
    private static final Contact IVANOV = new Contact();
    private static final Contact PETROVA = new Contact();
    private static final Contact SIDOROV = new Contact();

    private InMemoryContactDao prepareTestDaoWithThreeContacts() {
        var dao = new InMemoryContactDao();

        IVANOV.setSurname("Ivanov");
        IVANOV.setFirstname("Ivan");
        IVANOV.setPhoneNumber("89031112233");
        IVANOV.setEmail("Ivanov@email.ru");

        PETROVA.setSurname("Petrova");
        PETROVA.setFirstname("Irina");
        PETROVA.setPhoneNumber("89034445566");
        PETROVA.setEmail("Petrova@email.ru");

        SIDOROV.setSurname("Sidorov");
        SIDOROV.setFirstname("Fedor");
        SIDOROV.setPhoneNumber("89037778899");
        SIDOROV.setEmail("Sidorov@email.ru");

        dao.addContact(IVANOV);
        dao.addContact(PETROVA);
        dao.addContact(SIDOROV);

        return dao;
    }

    @Test
    void testCreateContact() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        var newContact = contactFacade.createContact(
                "Novikov",
                "Anton",
                "89073214578",
                "Novikov@mail.ru"
        );
        assertEquals(4, newContact.getId());
        assertEquals("Novikov", newContact.getSurname());
        assertEquals("Anton", newContact.getFirstname());
        assertEquals("89073214578", newContact.getPhoneNumber());
        assertEquals("Novikov@mail.ru", newContact.getEmail());
    }

    @Test
    void testGetContact() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        var testedContactDto = contactFacade.getContact(1);

        assertEquals(IVANOV.getSurname(), testedContactDto.getSurname());
        assertEquals(IVANOV.getFirstname(), testedContactDto.getFirstname());
        assertEquals(IVANOV.getPhoneNumber(), testedContactDto.getPhoneNumber());
        assertEquals(IVANOV.getEmail(), testedContactDto.getEmail());
    }

    @Test
    void testFindContactWithExistingId() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        var testedContactDto = contactFacade.findContact(2);

        assertEquals(PETROVA.getSurname(), testedContactDto.get().getSurname());
        assertEquals(PETROVA.getFirstname(), testedContactDto.get().getFirstname());
        assertEquals(PETROVA.getPhoneNumber(), testedContactDto.get().getPhoneNumber());
        assertEquals(PETROVA.getEmail(), testedContactDto.get().getEmail());
    }

    @Test
    void testFindContactWithNotExistingId() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        assertEquals(Optional.empty(), contactFacade.findContact(999));
    }

    @Test
    void testUpdateContactWithExistingId() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());

        var testContact = new Contact();
        testContact.setSurname("Petrovskaya");
        testContact.setFirstname("Olesja");
        testContact.setPhoneNumber("890131119999");
        testContact.setEmail("Petrovskaya@email.ru");
        var testContactDto = new ContactDto(testContact);
        contactFacade.updateContact(2, testContactDto);

        assertNotEquals(testContactDto, contactFacade.getContact(2));
        assertEquals(testContact.getSurname(), contactFacade.getContact(2).getSurname());
        assertEquals(testContact.getFirstname(), contactFacade.getContact(2).getFirstname());
        assertEquals(testContact.getPhoneNumber(), contactFacade.getContact(2).getPhoneNumber());
        assertEquals(testContact.getEmail(), contactFacade.getContact(2).getEmail());
    }

    @Test
    void testUpdateContactWithNotExistingId() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        assertThrows(IllegalArgumentException.class, () -> contactFacade.updateContact(999, new ContactDto()));
    }

    @Test
    void testDeleteContact() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        contactFacade.deleteContact(2);
        assertEquals(Optional.empty(), contactFacade.findContact(2));
        assertEquals(2, contactFacade.getAllContacts().size());

        contactFacade.deleteContact(7777);
        assertEquals(Optional.empty(), contactFacade.findContact(7777));
        assertEquals(2, contactFacade.getAllContacts().size());
    }

    @Test
    void testGetAllContacts() {
        var contactFacade = new ContactFacade(prepareTestDaoWithThreeContacts());
        assertEquals(3, contactFacade.getAllContacts().size());
    }
}