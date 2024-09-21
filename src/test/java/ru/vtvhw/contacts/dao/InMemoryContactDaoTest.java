package ru.vtvhw.contacts.dao;

import org.junit.jupiter.api.Test;
import ru.vtvhw.contacts.model.Contact;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryContactDaoTest {
    private List<Contact> prepareTestContacts() {
        var contact1 = new Contact();
        contact1.setSurname("Ivanov");
        contact1.setFirstname("Ivan");
        contact1.setPhoneNumber("89031112233");
        contact1.setEmail("Ivanov@email.ru");

        var contact2 = new Contact();
        contact2.setSurname("Petrova");
        contact2.setFirstname("Irina");
        contact2.setPhoneNumber("89034445566");
        contact2.setEmail("Petrova@email.ru");

        var contact3 = new Contact();
        contact3.setSurname("Sidorov");
        contact3.setFirstname("Fedor");
        contact3.setPhoneNumber("89037778899");
        contact3.setEmail("Sidorov@email.ru");

        return List.of(contact1, contact2, contact3);
    }

    @Test
    void testAddContact() {
        var dao = new InMemoryContactDao();
        assertEquals(0, dao.getAllContacts().size());
        dao.addContact(new Contact());
        assertEquals(1, dao.getAllContacts().size());
        assertEquals(1, dao.getAllContacts().get(0).getId());

        prepareTestContacts().forEach(dao::addContact);
        assertEquals(4, dao.getAllContacts().size());
    }

    @Test
    void testFindExistingContact() {
        var testContact = new Contact();
        testContact.setSurname("Ivanov");
        testContact.setFirstname("Ivan");
        testContact.setPhoneNumber("890131112233");
        testContact.setEmail("Ivanov@email.ru");

        var dao = new InMemoryContactDao();
        dao.addContact(testContact);

        assertSame(dao.findContact(1).get(), testContact);
        assertEquals(dao.findContact(1).get(), testContact);
    }

    @Test
    void testFindNotExistingContact() {
        var dao = new InMemoryContactDao();
        assertEquals(Optional.empty(), dao.findContact(1));
    }

    @Test
    void testGetContactWithExistingId() {
        var testContact = new Contact();
        testContact.setSurname("Ivanov");
        testContact.setFirstname("Ivan");
        testContact.setPhoneNumber("890131112233");
        testContact.setEmail("Ivanov@email.ru");

        var dao = new InMemoryContactDao();
        dao.addContact(testContact);

        testContact.setId(1);

        assertEquals(dao.getContact(1L), testContact);
    }

    @Test
    void testGetContactWithNotExistingId() {
        var dao = new InMemoryContactDao();
        assertThrows(IllegalArgumentException.class, () -> dao.getContact(1));
    }

    @Test
    void testUpdateContactWithExistingId() {
        var dao = new InMemoryContactDao();
        assertEquals(0, dao.getAllContacts().size());
        prepareTestContacts().forEach(dao::addContact);
        assertEquals(3, dao.getAllContacts().size());

        var testContact = new Contact();
        testContact.setId(2);
        testContact.setSurname("Ivanov");
        testContact.setFirstname("Ivan");
        testContact.setPhoneNumber("890131112233");
        testContact.setEmail("Ivanov@email.ru");

        dao.updateContact(testContact);
        assertNotSame(testContact, dao.getContact(2));
        assertEquals(testContact, dao.getContact(2));
    }

    @Test
    void testUpdateContactWithNotExistingId() {
        var dao = new InMemoryContactDao();
        var testContact = new Contact();
        testContact.setId(999);
        assertThrows(IllegalArgumentException.class, () -> dao.updateContact(testContact));
    }

    @Test
    void testDeleteContact() {
        var dao = new InMemoryContactDao();
        assertEquals(0, dao.getAllContacts().size());
        prepareTestContacts().forEach(dao::addContact);
        assertEquals(3, dao.getAllContacts().size());

        dao.deleteContact(2);
        assertEquals(2, dao.getAllContacts().size());
        assertEquals(Optional.empty(), dao.findContact(2));
    }

    @Test
    void testGetAllContacts() {
        var dao = new InMemoryContactDao();
        var testContactsList = prepareTestContacts();

        testContactsList.forEach(dao::addContact);
        assertEquals(3, dao.getAllContacts().size());
        assertTrue(dao.getAllContacts().containsAll(testContactsList));
    }
}