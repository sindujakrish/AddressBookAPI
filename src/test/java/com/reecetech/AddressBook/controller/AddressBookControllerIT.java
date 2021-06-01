package com.reecetech.AddressBook.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.reecetech.AddressBook.AddressBookApplication;
import com.reecetech.AddressBook.model.Contact;
import com.reecetech.AddressBook.service.AddressBookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookControllerIT {
	
	@LocalServerPort
    private int port;

    @Autowired
    private AddressBookService addressBookService;

    TestRestTemplate restTemplate = new TestRestTemplate();
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testAddContact() throws Exception {
        Contact contact = new Contact("Name", 123456789);
        HttpEntity<Contact> entity = new HttpEntity<>(contact);

        ResponseEntity<Contact> response = restTemplate.exchange(
                createURLWithPort("/api/addressBook/test/contact"),
                HttpMethod.POST, entity, Contact.class);
        System.out.println(response.getStatusCode().value());
        Assert.assertTrue(response.getStatusCode().value() == 200);

        addressBookService.removeContact("test", response.getBody().getId());
    }

    @Test
    public void testRemoveContact() throws Exception {
    
        Contact contact = addressBookService.addContact("test", new Contact("Name", 123456789));
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/addressBook/test/contact/"+contact.getId()),
                HttpMethod.DELETE, new HttpEntity<String>(null, null), String.class);

        Assert.assertEquals(response.getStatusCode().value(), 200);

    }

    @Test
    public void testRetrieveAllContactsFromAddressBook() throws Exception {
    	addressBookService.addContact("test", new Contact("Name", 123456789));
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/addressBook/test/contacts"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void testRetrieveAllUniqueContacts() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/contacts"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }

}
