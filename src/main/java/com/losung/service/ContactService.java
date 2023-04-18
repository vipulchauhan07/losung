package com.losung.service;

import com.losung.dto.ContactDTO;
import com.losung.model.Contact;
import com.losung.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    public Contact createContact(ContactDTO data) {
        Contact contact = new Contact();
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setEmail(data.getEmail());
        contact.setPhoneNumber(data.getPhoneNumber());
        return contactRepository.save(contact);
    }

    public Contact updateContact(Integer id, ContactDTO data) {
        Optional<Contact> contact = contactRepository.findByIdAndActive(id, true);
        if (contact.isPresent()) {
            Contact updatedContact = contact.get();
            updatedContact.setFirstName(data.getFirstName());
            updatedContact.setLastName(data.getLastName());
            updatedContact.setEmail(data.getEmail());
            updatedContact.setPhoneNumber(data.getPhoneNumber());
           return contactRepository.save(updatedContact);
        } else {
            return null;
        }
    }

    public boolean deleteContact(Integer id) {
        try {
            Contact contact = contactRepository.findById(id).get();
            contact.setActive(false);
            contactRepository.save(contact);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Contact> getContacts(String firstName, String lastName, String email, Integer id) {

        List<Contact> contacts;

        if (firstName == null && lastName == null && email == null && id == null) {
            // If all parameters are null, fetch all contacts
            contacts = contactRepository.findAllByActive(true);
        } else {
            contacts = contactRepository.findByFirstNameAndLastNameAndEmailAndId(firstName, lastName, email, id);
        }
        return contacts;
    }
}
