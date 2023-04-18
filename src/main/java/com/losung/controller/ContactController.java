package com.losung.controller;


import com.losung.dto.ContactDTO;
import com.losung.model.Contact;
import com.losung.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> postContact(@RequestBody ContactDTO data) {
        Contact newContact = contactService.createContact(data);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContact(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String email, @RequestParam(required = false) Integer id) {
        List<Contact> contacts = contactService.getContacts(firstName, lastName, email, id);
        if (contacts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(contacts);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody ContactDTO data) {
        Contact updatedContact = contactService.updateContact(id, data);
        if (updatedContact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
        boolean deleted = contactService.deleteContact(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
