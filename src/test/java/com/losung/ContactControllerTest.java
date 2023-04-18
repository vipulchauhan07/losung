package com.losung;

import com.losung.controller.ContactController;
import com.losung.dto.ContactDTO;
import com.losung.model.Contact;
import com.losung.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @Test
    public void testPostContact() {
        // Prepare input data
        ContactDTO contactDTO = new ContactDTO();
        // Set properties of contactDTO

        // Mock the contactService.createContact() method
        when(contactService.createContact(Mockito.any(ContactDTO.class))).thenReturn(new Contact());

        // Call the API
        ResponseEntity<Contact> response = contactController.postContact(contactDTO);

        // Assert the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testGetContact() {
        // Prepare input data
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        Integer id = 1;

        Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        contact.setPhoneNumber("123456432");
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        // Mock the contactService.getContacts() method
        when(contactService.getContacts(firstName, lastName, email, id)).thenReturn(contacts);

        // Call the API
        ResponseEntity<List<Contact>> response = contactController.getContact(firstName, lastName, email, id);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testUpdateContact() {
        // Prepare input data
        Integer id = 1;
        ContactDTO contactDTO = new ContactDTO();
        // Set properties of contactDTO

        // Mock the contactService.updateContact() method
        when(contactService.updateContact(id, contactDTO)).thenReturn(new Contact());

        // Call the API
        ResponseEntity<Contact> response = contactController.updateContact(id, contactDTO);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add more assertions based on your specific implementation
    }

    @Test
    public void testDeleteContact() {
        // Prepare input data
        Integer id = 1;

        // Mock the contactService.deleteContact() method
        when(contactService.deleteContact(id)).thenReturn(true);

        // Call the API
        ResponseEntity<String> response = contactController.deleteContact(id);

        // Assert the response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Add more assertions based on your specific implementation
    }
}
