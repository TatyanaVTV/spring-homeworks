package ru.vtvhw.contacts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vtvhw.contacts.facade.ContactFacade;
import ru.vtvhw.contacts.model.Contact;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private ContactFacade contactFacade;

    @Autowired
    public ContactController(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactFacade.getAllContacts();
    }

    /** Example. Remained here in learning purposes.
     * curl -X POST -d "{\"surname\": \"Samoilova\", \"firstname\": \"Irina\", \"phoneNumber\": \"89037654999\", \"email\": \"Samoilova@mail.ru\"}" -H "Content-Type:application/json" http://localhost:8080/contacts
     * */
    @PostMapping
    public ContactDto createContact(@RequestBody ContactDto contactDto) {
        return contactFacade.createContact(
                contactDto.getSurname(),
                contactDto.getFirstname(),
                contactDto.getPhoneNumber(),
                contactDto.getEmail()
        );
    }

    /** Example. Remained here in learning purposes.
     * curl http://localhost:8080/contacts/1
     * */
    @GetMapping("/{contactId:\\d+}")
    public ContactDto getContact(@PathVariable(name = "contactId") long contactId) {
        return contactFacade.getContact(contactId);
    }

    /** Example. Remained here in learning purposes.
     * curl -X DELETE http://localhost:8080/contacts/2
     * */
    @DeleteMapping("/{contactId:\\d+}")
    public void deleteContactById(@PathVariable(name = "contactId") long contactId) {
        contactFacade.deleteContact(contactId);
    }

    /** Example. Remained here in learning purposes.
     * curl -X PUT -d "{\"surname\": \"Petrovskiy\", \"firstname\": \"Igor\", \"phoneNumber\": \"89034444444\", \"email\": \"Petrov44@mail.ru\"}" -H "Content-Type:application/json" http://localhost:8080/contacts/1
     * */
    @PutMapping("/{contactId:\\d+}")
    public void updateContact(@PathVariable(name = "contactId") long contactId, @RequestBody ContactDto contactDto) {
        contactFacade.updateContact(contactId, contactDto);
    }
}